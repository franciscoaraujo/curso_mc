package br.com.nelioalves.cursomc.curso_mc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nelioalves.cursomc.curso_mc.domain.Categoria;
import br.com.nelioalves.cursomc.curso_mc.services.CategoriaService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Categoria find(@PathVariable Integer id) throws ObjectNotFoundException {
		return service.buscaPorId(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) throws ObjectNotFoundException {
		categoria = service.cadastrar(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(categoria.getId())
				.toUri();
		
		return  ResponseEntity.created(uri).build();
	}
//
//	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Collection<Categoria>> findAll() {
//		Collection<Categoria> categoriaCollection = service.buscarTodos();
//		return ResponseEntity.ok(categoriaCollection);
//	}
//
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Categoria> alteraCategoria(@RequestBody Categoria categoria, @PathVariable Integer id) throws ObjectNotFoundException {
		service.alterar(categoria);
		return ResponseEntity.noContent().build();
	}

}
