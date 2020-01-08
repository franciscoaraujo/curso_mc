package br.com.nelioalves.cursomc.curso_mc.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nelioalves.cursomc.curso_mc.domain.Cidade;
import br.com.nelioalves.cursomc.curso_mc.domain.Estado;
import br.com.nelioalves.cursomc.curso_mc.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;

	public Collection<Cidade> findByCidade(Long estado_id) {
		Collection<Cidade> cidades = repo.findCidade(estado_id);
		return cidades;
	}
	
}
