package br.com.sabesp.cco.exceptions.handler;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
@RestController
public class GlobalHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleArgumentNotValid(MethodArgumentNotValidException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getBindingResult().getAllErrors().toString());
		return ResponseEntity.badRequest().body(apiError);
	}

	@ExceptionHandler({ FileSizeLimitExceededException.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

		final String ERRO_INTERNO = "Erro interno de servidor";
		ApiError apiError = null;
		
		if (ex instanceof MaxUploadSizeExceededException) {
			apiError = new ApiError(HttpStatus.LENGTH_REQUIRED, 
									"Excedido o tamanho máximo de 1M para arquivo.",
									ex.getMessage());
		} else {
			apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ERRO_INTERNO);
		}
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

}
