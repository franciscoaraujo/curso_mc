package br.com.nelioalves.cursomc.curso_mc.services;

import java.util.Collection;
import java.util.Optional;

public interface IService<T> {

	public Optional<T> buscaPorId(Integer t); 

	public T cadastrar(T t);

	public Collection<T> buscarTodos();

	public T alterar(T t); 

	public void excluir( T t); 
	
}
