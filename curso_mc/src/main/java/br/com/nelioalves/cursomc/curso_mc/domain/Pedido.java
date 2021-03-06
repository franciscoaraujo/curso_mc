package br.com.nelioalves.cursomc.curso_mc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy : HH:mm")
	private Date instantDate;

	/* Mapeamento bidirecional 1 pra 1 */
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido") // Evitando erro de entidade transiente quando for salvar o pedido e o pagamento
	private Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "endereco_de_entrega_id")
	private Endereco enderecoDeEntraga;

	@OneToMany(mappedBy = "id.pedido") // informando quem mapeou do outro lado que foi o objeto id.pedido - Aqui tem que ser OnetoMany
	private Set<ItemPedido> itens = new HashSet<>();

	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	public Double getValorTotal() {
		Double soma = 0.0;
		for (ItemPedido itemPedido : itens) {
			soma += itemPedido.getSubTotal();
		}
		return soma;
	}

	public Pedido(Long id, Date instantDate, Cliente cliente, Endereco enderecoDeEntraga) {
		super();
		this.id = id;
		this.instantDate = instantDate;
		this.cliente = cliente;
		this.enderecoDeEntraga = enderecoDeEntraga;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInstantDate() {
		return instantDate;
	}

	public void setInstantDate(Date instantDate) {
		this.instantDate = instantDate;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoDeEntraga() {
		return enderecoDeEntraga;
	}

	public void setEnderecoDeEntraga(Endereco enderecoDeEntraga) {
		this.enderecoDeEntraga = enderecoDeEntraga;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	
	

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat dfm = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
		
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido Numero")
		.append(getId())
		.append(", Instante: ")
		.append(dfm.format(getInstantDate()))
		.append(", Cliente: ")
		.append(getCliente().getNome())
		.append(", Situacao do pagamento")
		.append(getPagamento().getEstado().getDescricao())
		.append("\nDetalhes:\n");
		
		for(ItemPedido ip: getItens()) {
			builder.append(ip.toString());
		}
		builder.append("Valor total").append(nf.format(getValorTotal()));
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
