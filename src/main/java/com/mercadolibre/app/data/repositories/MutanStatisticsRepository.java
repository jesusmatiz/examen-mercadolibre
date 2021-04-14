package com.mercadolibre.app.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mercadolibre.app.data.entities.MutanStatistics;

/**
 * Clase para el almacenamiento de datos
 * @author Jesus Matiz <jesusmatiz35@gmail.com>
 *
 * @date 14/04/2021 7:42:57 a. m.
 */
@Repository
public interface MutanStatisticsRepository extends CrudRepository<MutanStatistics, Integer> {
	
	@Transactional(readOnly = true)
	Boolean existsByChecksum(String checksum);

}
