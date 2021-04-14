package com.mercadolibre.app.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

/**
 * Clase que controla la respuesta de las excepciones
 */
@ControllerAdvice
@AllArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GlobalControllerAdvice {

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<List<ApiErrorResponse>> handleApiException(ApiException ex) {

		String message = ex.getMessage();

		List<ApiErrorResponse> r = new ArrayList<>();
		r.add(new ApiErrorResponse(ex.getError().getCode(), ex.getError().getDescription()));

		if (ex.getMessage() != null) {
			r.add(new ApiErrorResponse("", message));
		}

		return new ResponseEntity<>(r, ex.getError().getHttpCode());
	}

}
