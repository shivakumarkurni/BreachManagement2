package com.breach.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RiskDto {
	
	private int breachId;
	private String franchise;
	private String business;
	private String category;

}
