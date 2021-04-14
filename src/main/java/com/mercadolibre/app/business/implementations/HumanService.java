package com.mercadolibre.app.business.implementations;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.mutable.MutableBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercadolibre.app.business.interfaces.IHumanService;
import com.mercadolibre.app.data.entities.MutanStatistics;
import com.mercadolibre.app.data.repositories.MutanStatisticsRepository;
import com.mercadolibre.app.dto.HumanAdn;
import com.mercadolibre.app.dto.StatisticsResponse;
import com.mercadolibre.app.utils.CustomMD5;
import com.mercadolibre.app.utils.GsonUtils;

/**
 * Implementación de la interface de validación del adn humano
 * 
 * @author Jesus Matiz <jesusmatiz35@gmail.com>
 *
 * @date 13/04/2021 5:24:26 p. m.
 */
@Component
public class HumanService implements IHumanService {

	@Autowired
	private MutanStatisticsRepository repository;

	@Override
	public boolean isMutant(HumanAdn adn) {

		boolean result = false;
		int isMutantResult = 0;

		List<String> adns = adn.getDna();

		if (validationOfHorizontalDna(adns)) {

			isMutantResult++;
		}

		if (validationOfObliqueDna(adns)) {

			isMutantResult++;
		}

		if (validationDnaVertical(adns)) {

			isMutantResult++;
		}

		if (isMutantResult > 1) {
			result = true;
		}

		MutanStatistics mutans = new MutanStatistics();

		String json = GsonUtils.serialize(adns);

		mutans.setAdn(json);

		mutans.setChecksum(CustomMD5.getMd5(json));

		mutans.setIsMutant(result);

		this.repository.save(mutans);

		return result;
	}

	/**
	 * Valiación obliqua del adn
	 * 
	 * @param adn
	 * @return
	 */
	private boolean validationOfObliqueDna(List<String> adn) {

		boolean isMutantResult = false;

		if (verifyData(adn)) {
			isMutantResult = true;
		}

		if (!isMutantResult) {

			Collections.reverse(adn);

			if (verifyData(adn)) {
				isMutantResult = true;
			}
		}

		return isMutantResult;
	}

	/**
	 * Verifica la cadena del ADN horizontalmente return True si alguna de las
	 * cadenas cumple con la secuencia
	 * 
	 * @param adn
	 * @return
	 */
	private boolean validationOfHorizontalDna(List<String> adn) {

		Boolean isMutantResult = false;

		String[] next = { "" };
		int[] count = { 0 };

		MutableBoolean ongoing = new MutableBoolean(true);

		adn.stream().takeWhile(t -> ongoing.booleanValue()).forEach(s -> {

			count[0] = 1;

			for (char c : s.toCharArray()) {

				if (!next[0].equals("") && String.valueOf(c).equalsIgnoreCase(next[0])) {
					count[0] += 1;
					if (count[0] >= 4) {
						break;
					}
				} else {
					count[0] = 1;
				}

				next[0] = String.valueOf(c);
			}

			if (count[0] >= 4) {
				ongoing.setValue(false);
			}
		});

		if (count[0] >= 4) {
			isMutantResult = true;
		}

		return isMutantResult;
	}

	/**
	 * Verifica el adn verticalmente en busca del adn mutante
	 * 
	 * @param adn
	 * @return
	 */
	private boolean validationDnaVertical(List<String> adn) {

		List<Integer> target = new ArrayList<>();

		for (int i = 0; i < adn.get(0).length(); i++) {
			target.add(0);
		}

		for (int i = 0; i < adn.size() - 1; i++) {

			target = compareToData(target, adn.get(i), adn.get(i + 1));
		}

		long t = target.stream().filter(x -> x >= 4).count();

		return t > 0;
	}

	/**
	 * Compara los datos verticalmente
	 * 
	 * @param target
	 * @param x
	 * @param y
	 * @return
	 */
	private List<Integer> compareToData(List<Integer> target, String x, String y) {

		char[] cx = x.toCharArray();
		char[] cy = y.toCharArray();

		for (int i = 0; i < cx.length; i++) {

			if (cx[i] == cy[i]) {
				int newValue = target.get(i) + 1;
				target.remove(i);
				target.add(i, newValue);
			}
		}

		return target;
	}

	/**
	 * comparación de datos
	 * 
	 * @param adn
	 * @return
	 */
	private boolean verifyData(List<String> adn) {

		int countTotal = 1;

		for (int i = 0; i < adn.size() - 1; i++) {

			char[] ca = adn.get(i).toCharArray();
			char[] cb = adn.get(i + 1).toCharArray();

			if (ca[i] == cb[i + 1]) {
				countTotal++;
			}
		}

		boolean existsData = false;

		if (countTotal >= 4) {
			existsData = true;
		} else {

			countTotal = 1;

			List<String> reverseAdn = new ArrayList<>();

			adn.forEach(t -> {

				StringBuilder builder = new StringBuilder(t);

				reverseAdn.add(builder.reverse().toString());
			});

			for (int i = 0; i < reverseAdn.size() - 1; i++) {

				char[] ca = reverseAdn.get(i).toCharArray();
				char[] cb = reverseAdn.get(i + 1).toCharArray();

				if (ca[i] == cb[i + 1]) {
					countTotal++;
				}
			}

			if (countTotal >= 4) {
				existsData = true;
			}
		}

		return existsData;
	}

	/**
	 * retorna los datos de las estadisticas
	 */
	@Override
	public StatisticsResponse statistics() {

		Iterable<MutanStatistics> mutants = this.repository.findAll();

		StatisticsResponse response = new StatisticsResponse();

		float totalMutants = 0;
		float totalHumans = 0;

		for (MutanStatistics m : mutants) {
			if (m.getIsMutant()) {
				totalMutants++;
			} else {
				totalHumans++;
			}
		}

		response.setCountMutanDna(totalMutants);
		response.setCountHumanDna(totalHumans);

		float rat = ((totalMutants + totalHumans) - totalHumans) / 100;

		DecimalFormat df = new DecimalFormat("####.##");
		String rt = df.format(rat);

		response.setRatio(rt);

		return response;
	}

	/**
	 * Verifica si el adn ya fue registrado en el sistema
	 */
	@Override
	public Boolean existsAdn(String adn) {
		
		String checksum = CustomMD5.getMd5(adn);

		return this.repository.existsByChecksum(checksum);
	}

}
