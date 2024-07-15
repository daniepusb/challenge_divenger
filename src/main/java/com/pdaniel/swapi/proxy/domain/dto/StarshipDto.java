package com.pdaniel.swapi.proxy.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StarshipDto implements Serializable {

	private static final long serialVersionUID = 8309010770571897499L;

	private String name;
	@JsonProperty("max_atmosphering_speed")
	private String maxAtmospheringSpeed;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String url;

	public StarshipDto(String url) {
		this.url = url;
	}

	public int getMaxAtmospheringSpeedToInt() {
		return Integer.parseInt(maxAtmospheringSpeed);
	}

}
