package br.com.nelioalves.cursomc.curso_mc.resources.exception;

import java.util.ArrayList;
import java.util.Collection;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	private Collection<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public Collection<FieldMessage> getErrors() {
		return errors;
	}

	public void setList(Collection<FieldMessage> errors) {
		this.errors = errors;
	}

	public void addError(String fieldName, String messagem) {
		errors.add(new FieldMessage(fieldName, messagem));
	}

}
