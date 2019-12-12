package br.com.nelioalves.cursomc.curso_mc.services;

import java.util.List;
import java.util.Optional;

import br.com.nelioalves.cursomc.curso_mc.domain.Estado;

public interface IService<T> {

	public Optional<T> buscaPorId(Integer t); 

	public T cadastrar(T t);

	public List<T> buscarTodos();

	public T alterar(T t); 

	public void excluir( T t); 
	
}
