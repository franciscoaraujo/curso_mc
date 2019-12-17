package br.com.nelioalves.cursomc.curso_mc.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.nelioalves.cursomc.curso_mc.domain.Categoria;
import br.com.nelioalves.cursomc.curso_mc.domain.Produto;
import br.com.nelioalves.cursomc.curso_mc.repositories.CategoriaRepository;
import br.com.nelioalves.cursomc.curso_mc.repositories.ProdutoRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProdutoService implements IService<Produto> {

	@Autowired
	private ProdutoRepository repo;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public Produto buscaPorId(Integer id) throws ObjectNotFoundException {
		return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	@Override
	public Produto cadastrar(Produto pedido) {
		return repo.save(pedido);
	}

	@Override
	public Collection<Produto> buscarTodos() {
		return repo.findAll();
	}

	@Override
	public Produto alterar(Produto pedido) {
		return repo.save(pedido);
	}

	@Override
	public void excluir(Integer t) throws ObjectNotFoundException {
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);

	}

}
