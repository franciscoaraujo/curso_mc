package br.com.nelioalves.cursomc.curso_mc.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.nelioalves.cursomc.curso_mc.domain.Categoria;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	
	public CategoriaDTO(Categoria categoria) {
		id = categoria.getId();
		nome = categoria.getNome();
	}

	public CategoriaDTO() {
		// TODO Auto-generated constructor stub
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

	public static Collection<CategoriaDTO> converterToDTO(Collection<Categoria> list) {
		Collection<CategoriaDTO> listCatDTO = new ArrayList<CategoriaDTO>();
		for (Categoria categoria : list) {
			CategoriaDTO cat = new CategoriaDTO();
			cat.setId(categoria.getId());
			cat.setNome(categoria.getNome());
			listCatDTO.add(cat);
		}
		return listCatDTO;

	}
	
	public static Page<CategoriaDTO> converterToDTO(Page<Categoria> categoriaPage){
		Page<CategoriaDTO> listDTO = categoriaPage.map(obj -> new CategoriaDTO(obj));	
		return listDTO;
	}

}
