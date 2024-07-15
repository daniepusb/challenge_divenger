package com.pdaniel.swapi.proxy.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PlanetDto implements Serializable{

	private static final long serialVersionUID = -8752806050244495786L;
	
	private String name;

}
