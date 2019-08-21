package com.breach.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class IdentifiedBeach {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer identifiedBeachId;
	private String identifiedBeachName;

	

}
