package br.com.nelioalves.cursomc.curso_mc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

import br.com.nelioalves.cursomc.curso_mc.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComBoleto")//Parte da configuracao para instaciar essa class quando o Json informar (olhar a classe Pai)
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVenciemento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;

	public PagamentoComBoleto() {
		// TODO Auto-generated constructor stub
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVenciemento,
			Date dataPagamento) {
		super(id, estado, pedido);
		// TODO Auto-generated constructor stub
		this.dataVenciemento = dataVenciemento;
		this.dataPagamento = dataPagamento;

	}
	

	public Date getDataVenciemento() {
		return dataVenciemento;
	}

	public void setDataVenciemento(Date dataVenciemento) {
		this.dataVenciemento = dataVenciemento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
