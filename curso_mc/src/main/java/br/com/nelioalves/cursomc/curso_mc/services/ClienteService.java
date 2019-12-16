package br.com.nelioalves.cursomc.curso_mc.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.nelioalves.cursomc.curso_mc.domain.Categoria;
import br.com.nelioalves.cursomc.curso_mc.domain.Cliente;
import br.com.nelioalves.cursomc.curso_mc.dto.ClienteDTO;
import br.com.nelioalves.cursomc.curso_mc.repositories.ClienteRepository;
import br.com.nelioalves.cursomc.curso_mc.services.exception.DataIntegrityException;
import br.com.nelioalves.cursomc.curso_mc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService implements IService<Cliente> {

	@Autowired
	private ClienteRepository repo;

	@Override
	public Cliente cadastrar(Cliente t) {
		// TODO Auto-generated method stub
		return null;
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

	private void updateDate(Cliente newObj, Cliente t) {
		newObj.setNome(t.getNome());
		newObj.setEmail(t.getEmail());
	}
}
