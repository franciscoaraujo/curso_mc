package br.com.nelioalves.cursomc.curso_mc.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import br.com.nelioalves.cursomc.curso_mc.domain.Categoria;
import br.com.nelioalves.cursomc.curso_mc.domain.Cidade;
import br.com.nelioalves.cursomc.curso_mc.domain.Cliente;
import br.com.nelioalves.cursomc.curso_mc.domain.Endereco;
import br.com.nelioalves.cursomc.curso_mc.domain.enums.TipoCliente;
import br.com.nelioalves.cursomc.curso_mc.dto.ClienteDTO;
import br.com.nelioalves.cursomc.curso_mc.dto.ClienteNewDTO;
import br.com.nelioalves.cursomc.curso_mc.repositories.ClienteRepository;
import br.com.nelioalves.cursomc.curso_mc.repositories.EnderecoRepository;
import br.com.nelioalves.cursomc.curso_mc.services.exception.DataIntegrityException;
import br.com.nelioalves.cursomc.curso_mc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService implements IService<Cliente> {

	@Autowired
	private ClienteRepository repo;
	@Autowired
	EnderecoRepository enderecoRepository;

	@Transactional
	@Override
	public Cliente cadastrar(Cliente cliente) {
		cliente.setId(null);
		cliente = repo.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}

	@Override
	public Cliente buscaPorId(Integer t) throws ObjectNotFoundException {
		return repo.findById(t).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + t + ", Tipo: " + Categoria.class.getName()));
	}

	@Override
	public Collection<Cliente> buscarTodos() {
		return repo.findAll();
	}

	@Override
	public Cliente alterar(Cliente t) throws ObjectNotFoundException {
		Cliente newObj = buscaPorId(t.getId());
		updateDate(newObj, t);
		return repo.save(newObj);
	}

	@Override
	public void excluir(Integer id) throws ObjectNotFoundException {
		buscaPorId(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque ha entidades relacionadas");
		}
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), objDto.getCnpjOuCpf(), null);
	}

	public Cliente fromDTO(ClienteNewDTO objNewDto) {
		Cliente clienteNew = new Cliente(null, objNewDto.getNome(), objNewDto.getEmail(), objNewDto.getCpfOuCnpj(),	TipoCliente.toEnum(objNewDto.getTipo()));
		Cidade cidadeNew = new Cidade(objNewDto.getCidadeId(), null);
		
		Endereco enderecoNew = new Endereco(null, objNewDto.getLogradouro(), objNewDto.getNumero(),objNewDto.getComplemento(), objNewDto.getBairro(), objNewDto.getCep(), clienteNew, cidadeNew);
		
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
}
