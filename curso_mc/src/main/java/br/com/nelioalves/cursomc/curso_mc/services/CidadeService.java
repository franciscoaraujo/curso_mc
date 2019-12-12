package br.com.nelioalves.cursomc.curso_mc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nelioalves.cursomc.curso_mc.domain.Categoria;
import br.com.nelioalves.cursomc.curso_mc.domain.Cidade;
import br.com.nelioalves.cursomc.curso_mc.repositories.CidadeRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CidadeService implements IService<Cidade> {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public Cidade buscaPorId(Integer t) throws ObjectNotFoundException {
		return cidadeRepository.findById(t).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + t + ", Tipo: " + Categoria.class.getName()));
	}

	@Override
	public Cidade cadastrar(Cidade t) {
		return cidadeRepository.save(t);
	}

	@Override
	public List<Cidade> buscarTodos() {
		return cidadeRepository.findAll();
	}

	@Override
	public Cidade alterar(Cidade t) {
		return cidadeRepository.save(t);
	}

	@Override
	public void excluir(Cidade t) {
		cidadeRepository.delete(t);
	}
	
	
}
