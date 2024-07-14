package com.pdaniel.swapi.proxy.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ResponseDto implements Serializable {

    private static final long serialVersionUID = 6227517122969162848L;

    private List<PersonDto> results;

}
