package br.com.nelioalves.cursomc.curso_mc.resources;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.nelioalves.cursomc.curso_mc.domain.Cidade;
import br.com.nelioalves.cursomc.curso_mc.domain.Estado;
import br.com.nelioalves.cursomc.curso_mc.dto.CidadeDTO;
import br.com.nelioalves.cursomc.curso_mc.dto.EstadoDTO;
import br.com.nelioalves.cursomc.curso_mc.services.CidadeService;
import br.com.nelioalves.cursomc.curso_mc.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private CidadeService cidadeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<EstadoDTO>> findAllEstados() {
		Collection<Estado> list = estadoService.findAll();
		Collection<EstadoDTO> listDTO = list.stream().map(x -> new EstadoDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value = "/{estadoId}/cidades", method = RequestMethod.GET)
	public ResponseEntity<Collection<CidadeDTO>> findCidades(@PathVariable Long estadoId) {
		Collection<Cidade> list = cidadeService.findByCidade(estadoId);
		Collection<CidadeDTO> listDTO = list.stream().map(x -> new CidadeDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}
