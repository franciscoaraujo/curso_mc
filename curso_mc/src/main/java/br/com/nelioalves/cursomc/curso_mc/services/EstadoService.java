package br.com.nelioalves.cursomc.curso_mc.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nelioalves.cursomc.curso_mc.domain.Categoria;
import br.com.nelioalves.cursomc.curso_mc.domain.Estado;
import br.com.nelioalves.cursomc.curso_mc.repositories.EstadoRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class EstadoService implements IService<Estado>{

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Override
	public Estado buscaPorId(Integer id) throws ObjectNotFoundException {
		return estadoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	@Override
	public Estado cadastrar(Estado estado) {
		return estadoRepository.save(estado);
	}

	@Override
	public Collection<Estado> buscarTodos() {
		return estadoRepository.findAll();
	}

	@Override
	public Estado alterar(Estado estado) {
		return estadoRepository.save(estado);
	}

	@Override
	public void excluir(Estado estado) {
		estadoRepository.delete(estado);
	}
	
	
}
