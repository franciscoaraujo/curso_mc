package br.com.nelioalves.cursomc.curso_mc.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nelioalves.cursomc.curso_mc.domain.Estado;
import br.com.nelioalves.cursomc.curso_mc.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;

	public Collection<Estado> findAll() {
		Collection<Estado> estados = repo.findAllByOrderByNome();
		return estados;
	}
}
