package br.com.nelioalves.cursomc.curso_mc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nelioalves.cursomc.curso_mc.domain.Categoria;
import br.com.nelioalves.cursomc.curso_mc.domain.Cidade;
import br.com.nelioalves.cursomc.curso_mc.domain.Cliente;
import br.com.nelioalves.cursomc.curso_mc.domain.Endereco;
import br.com.nelioalves.cursomc.curso_mc.domain.Estado;
import br.com.nelioalves.cursomc.curso_mc.domain.ItemPedido;
import br.com.nelioalves.cursomc.curso_mc.domain.Pagamento;
import br.com.nelioalves.cursomc.curso_mc.domain.PagamentoComBoleto;
import br.com.nelioalves.cursomc.curso_mc.domain.PagamentoComCartao;
import br.com.nelioalves.cursomc.curso_mc.domain.Pedido;
import br.com.nelioalves.cursomc.curso_mc.domain.Produto;
import br.com.nelioalves.cursomc.curso_mc.domain.enums.EstadoPagamento;
import br.com.nelioalves.cursomc.curso_mc.domain.enums.TipoCliente;
import br.com.nelioalves.cursomc.curso_mc.repositories.CategoriaRepository;
import br.com.nelioalves.cursomc.curso_mc.repositories.CidadeRepository;
import br.com.nelioalves.cursomc.curso_mc.repositories.ClienteRepository;
import br.com.nelioalves.cursomc.curso_mc.repositories.EnderecoRepository;
import br.com.nelioalves.cursomc.curso_mc.repositories.EstadoRepository;
import br.com.nelioalves.cursomc.curso_mc.repositories.ItemPedidoRepository;
import br.com.nelioalves.cursomc.curso_mc.repositories.PagamentoRepository;
import br.com.nelioalves.cursomc.curso_mc.repositories.PedidoRepository;
import br.com.nelioalves.cursomc.curso_mc.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public void instatiateTestDatabase() throws ParseException {
		/* Criando categoria */
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardim");
		Categoria cat6 = new Categoria(null, "Perfumaria");
		Categoria cat7 = new Categoria(null, "Decoração");
		
		
		/* Criando produto */
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de Escritorio", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true Color LG", 1300.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 10.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);

		/* Associando categoria e produto */
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		/* Associando produto a categoria */
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		/* Armazenando categoria e produto */
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		/* Criando cidade e estado e associando ambos */
		Estado saoPaulo = new Estado(null, "Sao Paulo");
		Estado minasGerais = new Estado(null, "Minas Gerais");
		Cidade cidadeRibeiraoPreto = new Cidade(null, "Ribeirao Preto");
		saoPaulo.getCidades().add(cidadeRibeiraoPreto);
		cidadeRibeiraoPreto.setEstado(saoPaulo);
		Cidade cidadePassos = new Cidade(null, "Passos");
		minasGerais.getCidades().add(cidadePassos);
		cidadePassos.setEstado(minasGerais);
		/* Aramazenando cidade e estado */
		estadoRepository.saveAll(Arrays.asList(saoPaulo, minasGerais));
		cidadeRepository.saveAll(Arrays.asList(cidadeRibeiraoPreto, cidadePassos));

		/* Criando cliente e associando endereco */
		Cliente cli = new Cliente(null, "Maria Silval", "chicoaraujo1063@gmail.com", "1923194843", TipoCliente.PESSOAFISICA);
		cli.getTelefones().addAll(Arrays.asList("112222", "222333"));
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "14058002", cli,
				cidadeRibeiraoPreto);
		Endereco e2 = new Endereco(null, "Ave. Matos", "100", "Apto 250", "Auroo", "14052025", cli, cidadePassos);
		cli.getEnderecos().addAll(Arrays.asList(e1, e2));

		/* Armazenando enderecos e cliente no banco */
		clienteRepository.saveAll(Arrays.asList(cli));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		/* Criando pedido, pagamento com cartao e associando a cliente e endereco */
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2019 9:32"), cli, e1);
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		/* Criando pedido, pagamento com boleto e associando a cliente e endereco */
		Pedido ped2 = new Pedido(null, sdf.parse("31/09/2019 10:00"), cli, e2);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2019 9:00"),
				null);
		ped2.setPagamento(pagto2);
		cli.getPedidos().addAll(Arrays.asList(ped1, ped2));

		/* Armazanendo pedidos no banco e pagamentos */
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		/* Criando lista de pedidos e associando a pedido 1 */
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p2, 100.00, 1, 800.00);
		ItemPedido ip3 = new ItemPedido(ped2, p3, 0.00, 2, 80.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip2));
		p3.getItens().addAll(Arrays.asList(ip3));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
