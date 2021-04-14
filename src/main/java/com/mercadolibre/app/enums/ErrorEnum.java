package com.mercadolibre.app.enums;

import org.springframework.http.HttpStatus;

/**
 * Lista de mensajes de errores que se mostraran en la aplicación
 * @author Jesus Matiz <jesusmatiz35@gmail.com>
 *
 * @date 13/04/2021 5:33:14 p. m.
 */
public enum ErrorEnum {
	
	HUMAN_ADN_NOT_MUTANT("001", "403-Forbidden", HttpStatus.FORBIDDEN),
	REQUEST_ADN_ERROR("002", "El campo adn es obligatorio", HttpStatus.UNPROCESSABLE_ENTITY),
	REQUEST_ERROR("003", "El json no tiene un formato valido", HttpStatus.INTERNAL_SERVER_ERROR),
	HUMAN_ADN_ERROR("004", "Uno o mas nitrogenos es invalido", HttpStatus.UNPROCESSABLE_ENTITY),
	HUMAN_ADN_STRING_ERROR("005", "Todas las cadenas del adn deben tener la misma longitud", HttpStatus.UNPROCESSABLE_ENTITY),
	ADN_EXISTS_ERROR("006", "El ADN ya se encuentra registrado", HttpStatus.UNPROCESSABLE_ENTITY),
	MD5_ERROR("007", "Error al tratar de generar el checksum", HttpStatus.UNPROCESSABLE_ENTITY),

	;

	private String code;
	private String description;
	private HttpStatus httpCode;

	private ErrorEnum(String code, String description, HttpStatus httpCode) {
		this.code = code;
		this.description = description;
		this.httpCode = httpCode;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public HttpStatus getHttpCode() {
		return httpCode;
	}

}
