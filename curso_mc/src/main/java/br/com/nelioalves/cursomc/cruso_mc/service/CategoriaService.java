package br.com.nelioalves.cursomc.cruso_mc.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nelioalves.cursomc.cruso_mc.domain.Categoria;
import br.com.nelioalves.cursomc.cruso_mc.repsitories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	public Categoria buscar(Integer id) {
		Categoria categoria = categoriaRepository.getOne(id);
		return categoria;
	}

	public Categoria cadastrar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Collection<Categoria> buscarTodos() {
		return categoriaRepository.findAll();
	}

	public Categoria alterar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public void excluir(Categoria categoria) {
		categoriaRepository.delete(categoria);
	}
}
