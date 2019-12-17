package br.com.nelioalves.cursomc.curso_mc.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.domain.Page;

import br.com.nelioalves.cursomc.curso_mc.domain.Categoria;
import br.com.nelioalves.cursomc.curso_mc.domain.Produto;

public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Double preco;

	public ProdutoDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProdutoDTO(Produto obj) {
		// TODO Auto-generated constructor stub
		id = obj.getId();
		nome = obj.getNome();
		preco = obj.getPreco();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public static Collection<ProdutoDTO> converterToDTO(Collection<Produto> list) {
		Collection<ProdutoDTO> listProDTO = new ArrayList<ProdutoDTO>();
		for (Produto produto : list) {
			ProdutoDTO produtoDTO = new ProdutoDTO();
			produtoDTO.setId(produto.getId());
			produtoDTO.setNome(produto.getNome());
			produtoDTO.setPreco(produto.getPreco());
			listProDTO.add(produtoDTO);
		}
		return listProDTO;
	}
	
	public static Page<ProdutoDTO> converterToDTO(Page<Produto> produtoPage){
		Page<ProdutoDTO> listDTO = produtoPage.map(obj -> new ProdutoDTO(obj));	
		return listDTO;
	}
}
