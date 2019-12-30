package br.com.nelioalves.cursomc.curso_mc.services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.nelioalves.cursomc.curso_mc.domain.ItemPedido;
import br.com.nelioalves.cursomc.curso_mc.domain.PagamentoComBoleto;
import br.com.nelioalves.cursomc.curso_mc.domain.Pedido;
import br.com.nelioalves.cursomc.curso_mc.domain.enums.EstadoPagamento;
import br.com.nelioalves.cursomc.curso_mc.repositories.ItemPedidoRepository;
import br.com.nelioalves.cursomc.curso_mc.repositories.PagamentoRepository;
import br.com.nelioalves.cursomc.curso_mc.repositories.PedidoRepository;
import br.com.nelioalves.cursomc.curso_mc.services.email.EmailService;
import br.com.nelioalves.cursomc.curso_mc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService implements IService<Pedido> {

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	ItemPedidoRepository ItemPedidoRepository;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EmailService emailService;

	@Override
	public Pedido buscaPorId(Integer id) throws ObjectNotFoundException {
		return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	@Override
	@Transactional
	public Pedido cadastrar(Pedido obj) throws ObjectNotFoundException {
		obj.setId(null);
		obj.setInstantDate(new Date());
		obj.setCliente(clienteService.buscaPorId(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);

		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstantDate());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto((produtoService.buscaPorId(ip.getProduto().getId())));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		ItemPedidoRepository.saveAll(obj.getItens());
		
		//emailService.sendOrderConfirmationEmail(obj);
		
		emailService.sendOrderConfirmationHtmlEmail(obj);
		
		return obj;

	}

	@Override
	public Collection<Pedido> buscarTodos() {
		return repo.findAll();
	}

	@Override
	public Pedido alterar(Pedido pedido) {
		return repo.save(pedido);
	}

	@Override
	public void excluir(Integer t) throws ObjectNotFoundException {
		// TODO Auto-generated method stub

	}

}
