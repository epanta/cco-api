package br.com.sabesp.cco.exceptions;

public class NotFoundException extends RuntimeException {

	public NotFoundException(String mensagem) {
		super(mensagem);
	}

}
