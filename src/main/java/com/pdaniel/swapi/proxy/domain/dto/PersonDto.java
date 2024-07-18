package com.pdaniel.swapi.proxy.domain.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
        "name",
        "birth_year",
        "gender",
        "planet_name",
        "fastest_vehicle_driven",
        "films"
})
public class PersonDto implements Serializable {

    private static final long serialVersionUID = 6960929755180322552L;

    private String name;

    @JsonProperty("birth_year")
    private String birthYear;

    private String gender;

    @JsonProperty("planet_name")
    private String planetName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("fastest_vehicle_driven")
    private String fastestVehicleDriven;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String homeworld;

    private List<FilmDto> films;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<VehicleDto> vehicles;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<StarshipDto> starships;

}