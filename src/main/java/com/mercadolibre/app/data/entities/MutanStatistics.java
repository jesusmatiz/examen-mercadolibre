package com.mercadolibre.app.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * Entidad para el almacenamiento de datos
 * @author Jesus Matiz <jesusmatiz35@gmail.com>
 *
 * @date 14/04/2021 7:39:13 a. m.
 */
@Data
@Entity
@Table(name = "mutan_statistics")
public class MutanStatistics implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mutan_id")
	private int mutanId;
	@Column(name = "is_mutant")
	private Boolean isMutant;
	private String adn;
	private String checksum;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@PrePersist
	public void prePrersist() {
		this.createdAt = new Date();
	}

}
