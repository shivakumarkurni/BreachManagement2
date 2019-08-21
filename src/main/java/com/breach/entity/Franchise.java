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
public class Franchise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer franchiseId;
	private String franchise;

}
