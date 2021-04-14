package com.mercadolibre.app.business.interfaces;

import org.springframework.stereotype.Service;

import com.mercadolibre.app.dto.HumanAdn;
import com.mercadolibre.app.dto.StatisticsResponse;

/**
 * Interface para la validación de del ADN Mutante en humanos
 * @author Jesus Matiz <jesusmatiz35@gmail.com>
 *
 * @date 13/04/2021 5:22:47 p. m.
 */
@Service
public interface IHumanService {
	
	/**
	 * Metodo para la validación del adn humano
	 * @param adn
	 * @return
	 */
	boolean isMutant(HumanAdn adn);
	
	/**
	 * Devuelve las estadisticas de los mutantes versus humanos
	 * @return
	 */
	StatisticsResponse statistics();
	
	/**
	 * Verifica la existencia de un adn en el sistema
	 * @param adn
	 * @return
	 */
	Boolean existsAdn(String adn);

}
