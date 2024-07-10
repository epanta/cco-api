package br.com.sabesp.cco.exceptions;

public class NegocioException extends RuntimeException {

	public NegocioException(String mensagem) {
		super(mensagem);
	}

	public NegocioException(String mensagem, Throwable exception) {
		super(mensagem, exception);
	}

}
