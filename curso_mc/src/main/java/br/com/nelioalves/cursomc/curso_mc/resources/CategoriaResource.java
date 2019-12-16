package br.com.nelioalves.cursomc.curso_mc.resources;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nelioalves.cursomc.curso_mc.domain.Categoria;
import br.com.nelioalves.cursomc.curso_mc.dto.CategoriaDTO;
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
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestBody Categoria categoria, @PathVariable Integer id)
			throws ObjectNotFoundException {
		service.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Categoria> alteraCategoria(@RequestBody Categoria categoria, @PathVariable Integer id)
			throws ObjectNotFoundException {
		service.alterar(categoria);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<CategoriaDTO>> findAll() throws ObjectNotFoundException {
		Collection<CategoriaDTO> listCategoriaDTO = CategoriaDTO.converterToDTO(service.buscarTodos());
		return ResponseEntity.ok().body(listCategoriaDTO);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> getFindPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) throws ObjectNotFoundException {

		Page<Categoria> pageCategoria = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDTO = CategoriaDTO.converterToDTO(pageCategoria);
		return ResponseEntity.ok().body(listDTO);
	}

}
