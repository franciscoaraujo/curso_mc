package br.com.nelioalves.cursomc.curso_mc.resources;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.nelioalves.cursomc.curso_mc.domain.Estado;
import br.com.nelioalves.cursomc.curso_mc.services.EstadoService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	private EstadoService estadoService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Estado find(@PathVariable Integer id) throws ObjectNotFoundException {
		return estadoService.buscaPorId(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Collection<Estado> findAll() {
		return estadoService.buscarTodos();

	}

}
