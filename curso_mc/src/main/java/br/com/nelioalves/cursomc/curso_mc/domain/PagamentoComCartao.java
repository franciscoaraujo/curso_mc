package br.com.nelioalves.cursomc.curso_mc.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import br.com.nelioalves.cursomc.curso_mc.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComCartao")//Parte da configuracao para instaciar essa class quando o Json informar (olhar a classe Pai)
public class PagamentoComCartao extends Pagamento {

	private static final long serialVersionUID = 1L;

	private Integer numeroDeParcelas;

	public PagamentoComCartao() {
		// TODO Auto-generated constructor stub
	}

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		// TODO Auto-generated constructor stub
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

	@Override
	public String toString() {
		return "PagamentoComCartao [numeroDeParcelas=" + numeroDeParcelas + "]";
	}

}
