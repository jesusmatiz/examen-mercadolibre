package com.mercadolibre.app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mercadolibre.app.business.interfaces.IHumanService;
import com.mercadolibre.app.dto.HumanAdn;
import com.mercadolibre.app.utils.GsonUtils;

@SpringBootTest
class ExamenMercadoLibreApplicationTests {

	@Autowired
	private IHumanService humanService;

	/**
	 * Metodo para verificar el adn muntante
	 */
	@Test
	public void muntanTest() {

		HumanAdn adn = new HumanAdn();

		List<String> adnList = new ArrayList<>();

		adnList.add("ATGCGA");
		adnList.add("CAGTGC");
		adnList.add("TTATGT");
		adnList.add("AGAAGG");
		adnList.add("CCCCTA");
		adnList.add("TCACTG");

		adn.setDna(adnList);

		assertTrue(this.humanService.isMutant(adn), "El adn indicado no es mutante");
	}

	/**
	 * Metodo para validar si es adn humano
	 */
	@Test
	public void humanTest() {

		HumanAdn adn = new HumanAdn();

		List<String> adnList = new ArrayList<>();

		adnList.add("ATGCGA");
		adnList.add("CAGTGC");
		adnList.add("TTGTGT");
		adnList.add("AGAAGG");
		adnList.add("CCTCTA");
		adnList.add("TCACTG");

		adn.setDna(adnList);

		assertFalse(this.humanService.isMutant(adn), "El adn indicado no es human");
	}

	/**
	 * Valida que la respuesta sea una estadistica
	 */
	@Test
	public void statisticsTest() {

		assertFalse(Objects.isNull(this.humanService.statistics()), "No hay datos registrados");
	}

	/**
	 * Valida la existencia del adn en el sistema
	 */
	@Test
	public void existsAdnTest() {

		HumanAdn adn = new HumanAdn();

		List<String> adnList = new ArrayList<>();

		adnList.add("ATGCGA");
		adnList.add("CAGTGC");
		adnList.add("TTGTGT");
		adnList.add("AGAAGG");
		adnList.add("CCTCTA");
		adnList.add("TCACTG");

		adn.setDna(adnList);
		
		String json = GsonUtils.serialize(adnList);
		
		assertFalse(this.humanService.existsAdn(json), "El adn ya se encuentra registrado");
	}

}
