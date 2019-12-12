package br.com.nelioalves.cursomc.curso_mc.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nelioalves.cursomc.curso_mc.domain.Categoria;
import br.com.nelioalves.cursomc.curso_mc.domain.Cliente;
import br.com.nelioalves.cursomc.curso_mc.repositories.ClienteRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService  implements IService<Cliente> {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public Cliente buscaPorId(Integer t) throws ObjectNotFoundException {
		return clienteRepository.findById(t).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + t + ", Tipo: " + Categoria.class.getName()));
	}
	
	@Override
	public Cliente cadastrar(Cliente t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Cliente> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente alterar(Cliente t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Cliente t) {
		// TODO Auto-generated method stub
		
	}
	
	
}
