package br.com.nelioalves.cursomc.curso_mc.dto;

import java.io.Serializable;

import br.com.nelioalves.cursomc.curso_mc.domain.Cidade;

public class CidadeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;

	public CidadeDTO() {
		this.id = null;
		this.nome = null;
	}
	
	public CidadeDTO(Cidade obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
