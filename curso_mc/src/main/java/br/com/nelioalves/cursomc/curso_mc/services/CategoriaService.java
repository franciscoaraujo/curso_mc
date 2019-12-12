package br.com.nelioalves.cursomc.curso_mc.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nelioalves.cursomc.curso_mc.domain.Categoria;
import br.com.nelioalves.cursomc.curso_mc.repositories.CategoriaRepository;

@Service
public class CategoriaService implements IService<Categoria> {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public Optional<Categoria> buscaPorId(Integer id) {
		return categoriaRepository.findById(id);
	}

	@Override
	public Categoria cadastrar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@Override
	public Collection<Categoria> buscarTodos() {
		return categoriaRepository.findAll();
	}

	@Override
	public Categoria alterar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@Override
	public void excluir(Categoria categoria) {
		categoriaRepository.delete(categoria);
	}

}
