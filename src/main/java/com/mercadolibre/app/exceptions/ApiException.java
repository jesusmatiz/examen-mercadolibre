package com.mercadolibre.app.exceptions;


import com.mercadolibre.app.enums.ErrorEnum;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiException extends RuntimeException {

	private ErrorEnum error;

	private String message;

	public ApiException(ErrorEnum error) {
		super();
		this.error = error;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
