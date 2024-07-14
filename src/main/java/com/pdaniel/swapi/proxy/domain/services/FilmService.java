package com.pdaniel.swapi.proxy.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdaniel.swapi.proxy.domain.dto.FilmDto;
import com.pdaniel.swapi.proxy.infrastructure.client.SWClient;
import com.pdaniel.swapi.proxy.util.Util;

@Service
public class FilmService {

    @Autowired
    SWClient client;

    public List<FilmDto> completeFilms(List<FilmDto> films){

        return films.stream().map(filmUrl ->{
                    var film = client.searchFilmById(Util.getID(filmUrl.getUrl()));
                    FilmDto f = new FilmDto();
                    f.setName(film.getTitle());
                    f.setReleaseDate(film.getReleaseDate());
                    return f;
                })
                .collect(Collectors.toList());

    }

}