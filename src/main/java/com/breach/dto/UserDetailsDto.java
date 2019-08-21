package com.breach.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsDto {
	private Integer userId;
	private String userType;
	private String message;
	private Integer statusCode;

}
