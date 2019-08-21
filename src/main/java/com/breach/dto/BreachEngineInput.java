package com.breach.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class BreachEngineInput implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private Integer franchiseId;
	private Integer bussinessId;
	private Integer categoryId;
	private Integer identifiedBreachId;
	private Integer userId;



}
