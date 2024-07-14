package com.pdaniel.swapi.proxy.domain.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmDto implements Serializable {

    private static final long serialVersionUID = 6434831205993287292L;

    private String name;
    private String releaseDate;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String title;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String url;

    public FilmDto(String url) {
        this.url = url;
    }

    public FilmDto(String name, String releaseDate) {
        this.name = name;
        this.releaseDate = releaseDate;
    }


}