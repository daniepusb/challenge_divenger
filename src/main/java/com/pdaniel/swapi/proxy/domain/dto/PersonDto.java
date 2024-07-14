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

    private static final long serialVersionUID = 6227517122969162848L;

    private String name;
    private String birth_year;
    private String gender;
    private String planet_name;
    private String fastest_vehicle_driven;
    private List<String> films;

}