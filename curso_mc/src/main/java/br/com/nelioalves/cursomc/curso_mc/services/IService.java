package br.com.nelioalves.cursomc.curso_mc.services;

import java.util.Collection;
import java.util.Optional;

import javassist.tools.rmi.ObjectNotFoundException;

public interface IService<T> {

	public T buscaPorId(Integer t) throws ObjectNotFoundException; 

	public T cadastrar(T t);

	public Collection<T> buscarTodos();

	public T alterar(T t) throws ObjectNotFoundException; 

	public void excluir( T t); 
	
}
