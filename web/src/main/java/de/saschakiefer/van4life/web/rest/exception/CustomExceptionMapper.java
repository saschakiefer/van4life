package de.saschakiefer.van4life.web.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import de.saschakiefer.van4life.web.rest.dto.ErrorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionMapper {
	@ExceptionHandler
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception, WebRequest request) {
		return convertToResponseEntity(exception, HttpStatus.BAD_REQUEST, request);
	}

	private ResponseEntity<Object> convertToResponseEntity(Exception exception, HttpStatus status, WebRequest request) {
		var message = exception.getLocalizedMessage();
		var error = new ErrorDto(((ServletWebRequest) request).getRequest().getRequestURI(), status.value(), message);

		return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(error);
	}
}
