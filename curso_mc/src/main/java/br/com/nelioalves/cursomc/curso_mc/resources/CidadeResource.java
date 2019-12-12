package br.com.nelioalves.cursomc.curso_mc.resources;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.nelioalves.cursomc.curso_mc.domain.Cidade;
import br.com.nelioalves.cursomc.curso_mc.services.CidadeService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {

	@Autowired
	private CidadeService cidadeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Cidade find(@PathVariable Integer id) throws ObjectNotFoundException {
		return cidadeService.buscaPorId(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
	}

	@RequestMapping(method = RequestMethod.GET)
	public Collection<Cidade> findAll() {
		return cidadeService.buscarTodos();
	}

}
