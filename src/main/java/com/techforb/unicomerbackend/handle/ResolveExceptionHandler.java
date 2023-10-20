package com.techforb.unicomerbackend.handle;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.techforb.unicomerbackend.exception.TransferException;
import com.techforb.unicomerbackend.exception.ValidateException;

public class ResolveExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ResponseMessageError> handleValidation(ValidationException e, HttpServletRequest request) {
		return popularResponseMessageError(e, HttpStatus.BAD_REQUEST.value(), "Erro de validação: " + e.getMessage(),
				request);
	}

	@ExceptionHandler(ValidateException.class)
	public ResponseEntity<ResponseMessageError> handleValidacaoException(ValidateException e,
			HttpServletRequest request) {
		return popularResponseMessageError(e, HttpStatus.BAD_REQUEST.value(), e.getMessage(), request);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseMessageError> handleValidateException(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		return popularResponseMessageError(e, HttpStatus.BAD_REQUEST.value(), e.getMessage(), request);
	}
	
	@ExceptionHandler(TransferException.class)
	public ResponseEntity<ResponseMessageError> handleTransferException(TransferException e,
			HttpServletRequest request) {
		return popularResponseMessageError(e, HttpStatus.BAD_REQUEST.value(), e.getMessage(), request);
	}
	

	private static ResponseEntity<ResponseMessageError> popularResponseMessageError(Exception e, Integer status,
			String titulo, HttpServletRequest request) {
		ResponseMessageError err = new ResponseMessageError();
		err.setTimestamp(Instant.now());
		err.setStatus(status);
		err.setError(titulo);
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
