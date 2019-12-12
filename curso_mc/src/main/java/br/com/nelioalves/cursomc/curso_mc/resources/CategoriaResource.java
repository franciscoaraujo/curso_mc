package br.com.nelioalves.cursomc.curso_mc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

//	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> save(@RequestBody Categoria categoria) throws ObjectNotFoundException {
//		categoria = service.cadastrar(categoria);
//		return new ResponseEntity<>(categoria, HttpStatus.CREATED);
//	}
//
//	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Collection<Categoria>> findAll() {
//		Collection<Categoria> categoriaCollection = service.buscarTodos();
//		return ResponseEntity.ok(categoriaCollection);
//	}
//
//	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Categoria> alteraCategoria(@RequestBody Categoria categoria) {
//		// fazer a verificacao para ver se a cateforia existe
//		Categoria categoriaReturn = service.alterar(categoria);
//		return new ResponseEntity<>(categoriaReturn, HttpStatus.CREATED);
//	}

}
