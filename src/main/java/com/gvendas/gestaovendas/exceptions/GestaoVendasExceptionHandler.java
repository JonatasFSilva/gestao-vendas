package com.gvendas.gestaovendas.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GestaoVendasExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String CONSTANT_VALIDATION_LENGTH = "Length";
	private static final String CONSTANT_VALIDATION_NOT_NULL = "NotNull";
	private static final String CONSTANT_VALIDATION_NOT_BLANK = "NotBlank";
	private static final String CONSTANT_VALIDATION_NOT_PATTERN = "Pattern";

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Error> errors = gerarListErrors(ex.getBindingResult());

		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		String msgUser = "Rescurso não encontrado.";
		String msgDev = ex.toString();
		List<Error> errors = Arrays.asList(new Error(msgUser, msgDev));
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

	}

	@ExceptionHandler(RuleBusinessException.class)
	public ResponseEntity<Object> handleRuleBusinessException(RuleBusinessException ex, WebRequest request) {
		String msgUser = ex.getMessage();
		String msgDev = ex.getMessage();
		List<Error> errors = Arrays.asList(new Error(msgUser, msgDev));
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
			WebRequest request) {
		String msgUser = "Rescurso não encontrado.";
		String msgDev = ex.toString();
		List<Error> errors = Arrays.asList(new Error(msgUser, msgDev));
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

	}

	// METODOS INTERNOS DE EXECUCAO E TRATAMENTOS
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
		if (fieldError.getCode().equals(CONSTANT_VALIDATION_NOT_BLANK)) {
			return fieldError.getDefaultMessage().concat(" é obrigatório.");
		}
		if (fieldError.getCode().equals(CONSTANT_VALIDATION_NOT_NULL)) {
			return fieldError.getDefaultMessage().concat(" é obrigatório.");
		}
		if (fieldError.getCode().equals(CONSTANT_VALIDATION_LENGTH)) {
			return fieldError.getDefaultMessage().concat(String.format(" deve ter entre %s e %s caracteres.",
					fieldError.getArguments()[2], fieldError.getArguments()[1]));
		}
		if (fieldError.getCode().equals(CONSTANT_VALIDATION_NOT_PATTERN)) {
			return fieldError.getDefaultMessage().concat(" formato invalido.");
		}
		return fieldError.toString();
	}

}
