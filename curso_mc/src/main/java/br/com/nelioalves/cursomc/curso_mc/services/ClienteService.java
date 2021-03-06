package br.com.nelioalves.cursomc.curso_mc.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.nelioalves.cursomc.curso_mc.domain.Categoria;
import br.com.nelioalves.cursomc.curso_mc.domain.Cidade;
import br.com.nelioalves.cursomc.curso_mc.domain.Cliente;
import br.com.nelioalves.cursomc.curso_mc.domain.Endereco;
import br.com.nelioalves.cursomc.curso_mc.domain.enums.Perfil;
import br.com.nelioalves.cursomc.curso_mc.domain.enums.TipoCliente;
import br.com.nelioalves.cursomc.curso_mc.dto.ClienteDTO;
import br.com.nelioalves.cursomc.curso_mc.dto.ClienteNewDTO;
import br.com.nelioalves.cursomc.curso_mc.repositories.ClienteRepository;
import br.com.nelioalves.cursomc.curso_mc.repositories.EnderecoRepository;
import br.com.nelioalves.cursomc.curso_mc.security.UserSS;
import br.com.nelioalves.cursomc.curso_mc.services.exception.AuthorizationException;
import br.com.nelioalves.cursomc.curso_mc.services.exception.DataIntegrityException;
import br.com.nelioalves.cursomc.curso_mc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService implements IService<Cliente> {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private S3Service s2Service;

	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.client.profile}")
	private String prefix;

	@Value("${img.profile.size}")
	private int size;

	@Transactional
	@Override
	public Cliente cadastrar(Cliente cliente) {
		cliente.setId(null);
		cliente = repo.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}

	@Override
	public Cliente buscaPorId(Long id) throws ObjectNotFoundException {

		UserSS user = UserService.authenticated();

		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	@Override
	public Collection<Cliente> buscarTodos() {
		return repo.findAll();
	}

	public Cliente findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		Cliente obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return obj;
	}

	@Override
	public Cliente alterar(Cliente t) throws ObjectNotFoundException {
		Cliente newObj = buscaPorId(t.getId());
		updateDate(newObj, t);
		return repo.save(newObj);
	}

	@Override
	public void excluir(Long id) throws ObjectNotFoundException {
		buscaPorId(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há pedidos relacionados");
		}
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), objDto.getCnpjOuCpf(), null, null);
	}

	public Cliente fromDTO(ClienteNewDTO objNewDto) {
		Cliente clienteNew = new Cliente(null, objNewDto.getNome(), objNewDto.getEmail(), objNewDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objNewDto.getTipo()), pe.encode(objNewDto.getSenha()));
		Cidade cidadeNew = new Cidade(objNewDto.getCidadeId(), null);

		Endereco enderecoNew = new Endereco(null, objNewDto.getLogradouro(), objNewDto.getNumero(),
				objNewDto.getComplemento(), objNewDto.getBairro(), objNewDto.getCep(), clienteNew, cidadeNew);

		clienteNew.getEnderecos().add(enderecoNew);

		clienteNew.getTelefones().add(objNewDto.getTelefone1());

		if (objNewDto.getTelefone2() != null) {
			clienteNew.getTelefones().add(objNewDto.getTelefone2());
		}
		if (objNewDto.getTelefone3() != null) {
			clienteNew.getTelefones().add(objNewDto.getTelefone3());
		}
		return clienteNew;
	}

	private void updateDate(Cliente newObj, Cliente t) {
		newObj.setNome(t.getNome());
		newObj.setEmail(t.getEmail());
	}

	/* upload de fotos do profile */
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);

		String fileName = prefix + user.getId() + ".jpg";
		return s2Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
}
