package com.pdaniel.swapi.proxy.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponseErrorDto {
	
	private String code;
	private String description;

}
