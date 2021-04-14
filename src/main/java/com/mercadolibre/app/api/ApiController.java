package com.mercadolibre.app.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.app.business.interfaces.IHumanService;
import com.mercadolibre.app.dto.HumanAdn;
import com.mercadolibre.app.validator.HumanValidator;

/**
 * Controlador principal de la aplicación
 * @author Jesus Matiz <jesusmatiz35@gmail.com>
 *
 * @date 13/04/2021 5:15:13 p. m.
 */
@RestController
@RequestMapping(path = "")
public class ApiController {
	
	@Autowired
	private HumanValidator validator;
	@Autowired
	private IHumanService humanService;
	
	/**
	 * Metodo que obtiene los datos para validar el ADN mutante
	 * @return
	 */
	@PostMapping(path = "mutant", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> verifyMutant(@RequestBody HumanAdn dna) {
		
		this.validator.validate(dna);	
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * Devuelve las estadisticas de los mutantes encontrados
	 * @return
	 */
	@GetMapping(path = "stats", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> mutantStatistics() {
		
		return ResponseEntity.ok(this.humanService.statistics());
	}

}
