package com.pdaniel.swapi.proxy.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PersonDto implements Serializable {

    private static final long serialVersionUID = 6960929755180322552L;

    private String name;
    private String birthYear;
    private String gender;
    private String planetName;
    private String fastestVehicleDriven;
    private List<FilmDto> films;

}