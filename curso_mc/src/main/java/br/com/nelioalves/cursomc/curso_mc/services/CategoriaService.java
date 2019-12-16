package br.com.nelioalves.cursomc.curso_mc.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.nelioalves.cursomc.curso_mc.domain.Categoria;
import br.com.nelioalves.cursomc.curso_mc.dto.CategoriaDTO;
import br.com.nelioalves.cursomc.curso_mc.repositories.CategoriaRepository;
import br.com.nelioalves.cursomc.curso_mc.services.exception.DataIntegrityException;
import br.com.nelioalves.cursomc.curso_mc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService implements IService<Categoria> {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public Categoria buscaPorId(Integer id) throws ObjectNotFoundException {
		return categoriaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	@Override
	public Categoria cadastrar(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}

	@Override
	public Collection<Categoria> buscarTodos() {
		return categoriaRepository.findAll();
	}

	@Override
	public Categoria alterar(Categoria categoria) throws ObjectNotFoundException {
		buscaPorId(categoria.getId());
		return categoriaRepository.save(categoria);
	}

	@Override
	public void excluir(Integer id) throws ObjectNotFoundException {
		buscaPorId(id);
		try {
			categoriaRepository.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}

	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy,String direction){
		PageRequest pageRequest =  PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
}
