package com.breach.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Breach {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer breachId;
	private Integer franchiseId;
	private Integer businessId;
	private Integer categoryId;
	private String risk;
	private String status;
	private Integer userId;
	private LocalDateTime createdDate;
	
	
	
	
	
	



}
