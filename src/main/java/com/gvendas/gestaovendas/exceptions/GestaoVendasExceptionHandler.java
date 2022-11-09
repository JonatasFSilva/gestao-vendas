package com.gvendas.gestaovendas.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GestaoVendasExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		gerarListErrors(ex.getBindingResult());

		return super.handleMethodArgumentNotValid(ex, headers, status, request);
	}

	private List<Error> gerarListErrors(BindingResult bindingResult) {

		List<Error> errors = new ArrayList<>();
		bindingResult.getFieldErrors().forEach(fieldError -> {
			String msgUser = handleMessageUser(fieldError);
			String msgDev = fieldError.toString();
			errors.add(new Error(msgUser, msgDev));
		});

		return errors;

	}

	private String handleMessageUser(FieldError fieldError) {
		if(fieldError.getCode().equals(fieldError))
		return null;
	}

}
