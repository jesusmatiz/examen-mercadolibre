package com.mercadolibre.app.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * DTO para la solicitud del ADN Humano
 * @author Jesus Matiz <jesusmatiz35@gmail.com>
 *
 * @date 13/04/2021 5:18:44 p. m.
 */
@Data
public class HumanAdn implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "dna", required = true)
	private List<String> dna;

}
