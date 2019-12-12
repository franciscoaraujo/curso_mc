package br.com.nelioalves.cursomc.curso_mc.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nelioalves.cursomc.curso_mc.domain.Estado;
import br.com.nelioalves.cursomc.curso_mc.repositories.EstadoRepository;

@Service
public class EstadoService implements IService<Estado>{

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Override
	public Optional<Estado> buscaPorId(Integer id) {
		return estadoRepository.findById(id);
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
