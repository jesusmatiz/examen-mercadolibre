package com.mercadolibre.app.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

/**
 * Datos para retornar las estadisticas
 * @author Jesus Matiz <jesusmatiz35@gmail.com>
 *
 * @date 13/04/2021 11:53:11 p. m.
 */
@Data
public class StatisticsResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "count_mutant_dna")
	private float countMutanDna;
	@JsonProperty(value = "count_human_dna")
	private float countHumanDna;
	@JsonProperty(value = "ratio")
	private String ratio;

}
