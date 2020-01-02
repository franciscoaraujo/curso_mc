package br.com.nelioalves.cursomc.curso_mc.services;

import java.util.Collection;
import java.util.Optional;

import javassist.tools.rmi.ObjectNotFoundException;

public interface IService<T> {

	public T buscaPorId(Long t) throws ObjectNotFoundException; 

	public T cadastrar(T t) throws ObjectNotFoundException;

	public Collection<T> buscarTodos();

	public T alterar(T t) throws ObjectNotFoundException; 

	
	public void excluir( Long  t) throws ObjectNotFoundException; 
	
}
