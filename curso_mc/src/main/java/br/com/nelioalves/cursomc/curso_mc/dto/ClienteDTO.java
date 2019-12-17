package br.com.nelioalves.cursomc.curso_mc.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import br.com.nelioalves.cursomc.curso_mc.domain.Cliente;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preechimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;

	@NotEmpty(message = "Preechimento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	
	private String cnpjOuCpf;

	private Integer tipo;

	public ClienteDTO() {
		// TODO Auto-generated constructor stub
	}

	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
		cnpjOuCpf = obj.getCpfOuCnpj();
		tipo = obj.getTipo().getCodigo();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnpjOuCpf() {
		return cnpjOuCpf;
	}

	public void setCnpjOuCpf(String cnpjOuCpf) {
		this.cnpjOuCpf = cnpjOuCpf;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public static Collection<ClienteDTO> converterToDTO(Collection<Cliente> list) {
		Collection<ClienteDTO> listCliDTO = new ArrayList<ClienteDTO>();
		for (Cliente cliente : list) {
			ClienteDTO cli = new ClienteDTO();
			cli.setId(cliente.getId());
			cli.setNome(cliente.getNome());
			cli.setEmail(cliente.getEmail());
			listCliDTO.add(cli);
		}
		return listCliDTO;

	}

	public static Page<ClienteDTO> converterToDTO(Page<Cliente> clientePage) {
		Page<ClienteDTO> listDTO = clientePage.map(obj -> new ClienteDTO(obj));
		return listDTO;
	}
}
