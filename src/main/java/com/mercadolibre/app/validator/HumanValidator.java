package com.mercadolibre.app.validator;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercadolibre.app.business.interfaces.IHumanService;
import com.mercadolibre.app.dto.HumanAdn;
import com.mercadolibre.app.enums.ErrorEnum;
import com.mercadolibre.app.exceptions.ApiException;
import com.mercadolibre.app.utils.GsonUtils;

/**
 * Valida los campos del request
 * 
 * @author Jesus Matiz <jesusmatiz35@gmail.com>
 *
 * @date 13/04/2021 5:39:41 p. m.
 */
@Component
public class HumanValidator {

	@Autowired
	private IHumanService humanService;

	/**
	 * Metodo que valida los datos del request
	 * 
	 * @param request
	 */
	public void validate(HumanAdn request) {

		if (Objects.isNull(request)) {
			error(ErrorEnum.REQUEST_ERROR);
		}

		if (request.getDna().isEmpty()) {
			error(ErrorEnum.REQUEST_ADN_ERROR);
		}
		
		if (!sizeADN(request.getDna())) {
			error(ErrorEnum.HUMAN_ADN_STRING_ERROR);
		}

		request.getDna().forEach(adn -> {

			if (!validateChainOfAdn(adn)) {
				error(ErrorEnum.HUMAN_ADN_ERROR);
			}
		});
		
		if (this.humanService.existsAdn(GsonUtils.serialize(request.getDna()))) {
			error(ErrorEnum.ADN_EXISTS_ERROR);
		}

		if (!this.humanService.isMutant(request)) {
			error(ErrorEnum.HUMAN_ADN_NOT_MUTANT);
		}

	}

	/**
	 * Muestra el mensaje de error
	 * @param errors
	 */
	private void error(ErrorEnum errors) {

		throw new ApiException(errors);
	}

	/**
	 * Metodo que valida que la cadena del adn sea valido
	 * @param adn
	 * @return
	 */
	private boolean validateChainOfAdn(String adn) {

		String regex = "(A|T|C|G){4,}";
		
		boolean isValidate = true;
		
		if (adn.length() < 4 || !adn.toUpperCase().matches(regex)) {
			isValidate = false;
		}

		return isValidate;
	}
	
	/**
	 * Verifica que todas las cadenas de adn tenga el mismo tamaño
	 * @param adns
	 * @return
	 */
	private boolean sizeADN(List<String> adns) {
		
		int sizeInit = adns.get(0).length();		
		long adnSize = adns.stream().filter(f -> f.length() == sizeInit).count();
		
		return adns.size() == adnSize;
	}
	

}
