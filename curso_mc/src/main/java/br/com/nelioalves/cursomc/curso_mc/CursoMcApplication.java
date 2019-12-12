package br.com.nelioalves.cursomc.curso_mc;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.nelioalves.cursomc.curso_mc.domain.Categoria;
import br.com.nelioalves.cursomc.curso_mc.domain.Cidade;
import br.com.nelioalves.cursomc.curso_mc.domain.Estado;
import br.com.nelioalves.cursomc.curso_mc.domain.Produto;
import br.com.nelioalves.cursomc.curso_mc.repositories.CategoriaRepository;
import br.com.nelioalves.cursomc.curso_mc.repositories.CidadeRepository;
import br.com.nelioalves.cursomc.curso_mc.repositories.EstadoRepository;
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
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
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
		produtoRepository.saveAll(Arrays.asList(informP1,informP2,informP3));
		
		Estado saoPaulo = new Estado(null, "Sao Paulo");
		Estado minasGerais = new Estado(null, "Minas Gerais");
		
		Cidade cidadeRibeiraoPreto = new Cidade(null, "Ribeirao Preto");
		saoPaulo.getCidades().add(cidadeRibeiraoPreto);
		cidadeRibeiraoPreto.setEstado(saoPaulo);
		
		Cidade cidadePassos = new Cidade(null, "Passos");
		minasGerais.getCidades().add(cidadePassos);
		cidadePassos.setEstado(minasGerais);
		
		estadoRepository.saveAll(Arrays.asList(saoPaulo,minasGerais));
		cidadeRepository.saveAll(Arrays.asList(cidadeRibeiraoPreto, cidadePassos));
		
		
	}
	
	

}
