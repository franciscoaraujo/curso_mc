package br.com.nelioalves.cursomc.curso_mc.resources.exception;

import java.io.Serializable;

public class FieldMessage  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fielName;
	private String message;
	
	public FieldMessage() {
		// TODO Auto-generated constructor stub
	}

	public FieldMessage(String fielName, String message) {
		super();
		this.fielName = fielName;
		this.message = message;
	}

	public String getFieldName() {
		return fielName;
	}

	
	public String getMessage() {
		return message;
	}

	
	
}
