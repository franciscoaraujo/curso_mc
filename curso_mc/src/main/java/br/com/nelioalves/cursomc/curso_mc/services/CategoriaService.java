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
import br.com.nelioalves.cursomc.curso_mc.dto.CategoriaDTO;
import br.com.nelioalves.cursomc.curso_mc.repositories.CategoriaRepository;
import br.com.nelioalves.cursomc.curso_mc.services.exception.DataIntegrityException;
import br.com.nelioalves.cursomc.curso_mc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService implements IService<Categoria> {

	@Autowired
	private CategoriaRepository repo;

	@Override
	public Categoria buscaPorId(Long id) throws ObjectNotFoundException {
		return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	@Override
	public Categoria cadastrar(Categoria categoria) {
		categoria.setId(null);
		return repo.save(categoria);
	}

	@Override
	public Categoria alterar(Categoria t) throws ObjectNotFoundException {
		Categoria newObj = buscaPorId(t.getId());
		updateDate(newObj, t);
		return repo.save(newObj);
	}

	@Override
	public void excluir(Long id) throws ObjectNotFoundException {
		buscaPorId(id);
		try {
			repo.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
	}

	@Override
	public Collection<Categoria> buscarTodos() {
		return repo.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
	private void updateDate(Categoria newObj, Categoria t) {
		newObj.setNome(t.getNome());
	}
}
