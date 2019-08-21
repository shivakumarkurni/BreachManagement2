package com.breach.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Categories {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  categoriesId;
	private String categoriesName;

}
