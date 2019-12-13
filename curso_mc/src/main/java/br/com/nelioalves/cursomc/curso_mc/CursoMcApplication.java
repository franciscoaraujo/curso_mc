package br.com.nelioalves.cursomc.curso_mc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.nelioalves.cursomc.curso_mc.domain.Categoria;
import br.com.nelioalves.cursomc.curso_mc.domain.Cidade;
import br.com.nelioalves.cursomc.curso_mc.domain.Cliente;
import br.com.nelioalves.cursomc.curso_mc.domain.Endereco;
import br.com.nelioalves.cursomc.curso_mc.domain.Estado;
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
import br.com.nelioalves.cursomc.curso_mc.repositories.PagamentoRepository;
import br.com.nelioalves.cursomc.curso_mc.repositories.PedidoRepository;
import br.com.nelioalves.cursomc.curso_mc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner {

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
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto informP1 = new Produto(null, "Computador", 2000.00);
		Produto informP2 = new Produto(null, "Impressora", 800.00);
		Produto informP3 = new Produto(null, "Mouse", 80.00);

		Produto escriP1 = new Produto(null, "Mesa Escritorio", 250.00);
		Produto escriP2 = new Produto(null, "Cadeira Giratorio", 120.00);

		cat1.getProdutos().addAll(Arrays.asList(informP1, informP2, informP3));
		cat2.getProdutos().addAll(Arrays.asList(escriP1, escriP2));

		informP1.getCategorias().addAll(Arrays.asList(cat1));
		informP2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		informP3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(informP1, informP2, informP3));

		Estado saoPaulo = new Estado(null, "Sao Paulo");
		Estado minasGerais = new Estado(null, "Minas Gerais");

		Cidade cidadeRibeiraoPreto = new Cidade(null, "Ribeirao Preto");
		saoPaulo.getCidades().add(cidadeRibeiraoPreto);
		cidadeRibeiraoPreto.setEstado(saoPaulo);

		Cidade cidadePassos = new Cidade(null, "Passos");
		minasGerais.getCidades().add(cidadePassos);
		cidadePassos.setEstado(minasGerais);

		estadoRepository.saveAll(Arrays.asList(saoPaulo, minasGerais));
		cidadeRepository.saveAll(Arrays.asList(cidadeRibeiraoPreto, cidadePassos));

		Cliente cli = new Cliente(null, "Maria Silval", "silva@gmail.com", "1923194843", TipoCliente.PESSOAFISICA);
		cli.getTelefones().addAll(Arrays.asList("112222", "222333"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "14058002", cli,
				cidadeRibeiraoPreto);
		Endereco e2 = new Endereco(null, "Ave. Matos", "100", "Apto 250", "Auroo", "14052025", cli, cidadePassos);

		clienteRepository.saveAll(Arrays.asList(cli));

		cli.getEnderecos().addAll(Arrays.asList(e1, e2));

		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new  Pedido(null, sdf.parse("30/09/2019 9:32"),  cli, e1);
		Pedido ped2 = new  Pedido(null, sdf.parse("31/09/2019 10:00"),  cli, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,sdf.parse("20/10/2019 9:00"), null);
		ped2.setPagamento(pagto2);
		
		cli.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		
		
	}

}
