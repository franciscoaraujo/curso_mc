package br.com.nelioalves.cursomc.curso_mc.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nelioalves.cursomc.curso_mc.domain.Pedido;
import br.com.nelioalves.cursomc.curso_mc.repositories.PedidoRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class PedidoService implements IService<Pedido> {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
	public Pedido buscaPorId(Integer id) throws ObjectNotFoundException {
		return pedidoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	@Override
	public Pedido cadastrar(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	@Override
	public Collection<Pedido> buscarTodos() {
		return pedidoRepository.findAll();
	}

	@Override
	public Pedido alterar(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	@Override
	public void excluir(Pedido pedido) {
		pedidoRepository.delete(pedido);
	}

}
