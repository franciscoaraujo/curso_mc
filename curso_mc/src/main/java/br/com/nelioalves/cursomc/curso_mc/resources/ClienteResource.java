package br.com.nelioalves.cursomc.curso_mc.resources;

import java.net.URI;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nelioalves.cursomc.curso_mc.domain.Cliente;
import br.com.nelioalves.cursomc.curso_mc.dto.ClienteDTO;
import br.com.nelioalves.cursomc.curso_mc.dto.ClienteNewDTO;
import br.com.nelioalves.cursomc.curso_mc.services.ClienteService;
import br.com.nelioalves.cursomc.curso_mc.services.exception.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Long id) {
		return ResponseEntity.ok(service.buscaPorId(id));
	}

	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@RequestParam(value = "value") String email) {
		Cliente obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Cliente> save(@Valid @RequestBody ClienteNewDTO clienteNewDTO)
			throws ObjectNotFoundException {
		Cliente obj = service.fromDTO(clienteNewDTO);
		obj = service.cadastrar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> alteraCliente(@Valid @RequestBody ClienteDTO categoriaDTO, @PathVariable Long id)
			throws ObjectNotFoundException {
		Cliente obj = service.fromDTO(categoriaDTO);
		obj.setId(id);
		service.alterar(obj);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) throws ObjectNotFoundException {
		service.excluir(id);
		return ResponseEntity.noContent().build();
	}

	/* Lista todos os clientes */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<ClienteDTO>> findAll() throws ObjectNotFoundException {
		Collection<ClienteDTO> listClienteDTO = ClienteDTO.converterToDTO(service.buscarTodos());
		return ResponseEntity.ok().body(listClienteDTO);
	}

	/* Lista todos os clientes paginados */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> getFindPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) throws ObjectNotFoundException {

		Page<Cliente> pageCliente = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDTO = ClienteDTO.converterToDTO(pageCliente);
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/picture", method = RequestMethod.POST)
	public ResponseEntity<Cliente> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file)
			throws ObjectNotFoundException {
		URI uri = service.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}

}
