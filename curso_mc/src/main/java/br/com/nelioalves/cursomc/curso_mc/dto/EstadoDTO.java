package br.com.nelioalves.cursomc.curso_mc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import br.com.nelioalves.cursomc.curso_mc.domain.Estado;

public class EstadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "Preencimento Obrigatório")
	private String nome;

	public EstadoDTO() {
		// TODO Auto-generated constructor stub
	}

	public EstadoDTO(Estado obj) {
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
