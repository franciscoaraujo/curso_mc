package br.com.nelioalves.cursomc.curso_mc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.nelioalves.cursomc.curso_mc.domain.Pedido;
import br.com.nelioalves.cursomc.curso_mc.services.PedidoService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) throws ObjectNotFoundException {
		return ResponseEntity.ok().body(service.buscaPorId(id));

	}

//	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> save(@RequestBody Pedido Pedido) throws ObjectNotFoundException {
//		Pedido = service.cadastrar(Pedido);
//		return new ResponseEntity<>(Pedido, HttpStatus.CREATED);
//	}
//
//	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Collection<Pedido>> findAll() {
//		Collection<Pedido> PedidoCollection = service.buscarTodos();
//		return ResponseEntity.ok(PedidoCollection);
//	}
//
//	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Pedido> alteraPedido(@RequestBody Pedido Pedido) {
//		// fazer a verificacao para ver se a cateforia existe
//		Pedido PedidoReturn = service.alterar(Pedido);
//		return new ResponseEntity<>(PedidoReturn, HttpStatus.CREATED);
//	}

}
