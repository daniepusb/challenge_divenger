package com.pdaniel.swapi.proxy.domain.services;

import com.pdaniel.swapi.proxy.domain.dto.FilmDto;
import com.pdaniel.swapi.proxy.domain.dto.PersonDto;
import com.pdaniel.swapi.proxy.domain.dto.ResponseDto;
import com.pdaniel.swapi.proxy.util.Util;
import lombok.NoArgsConstructor;

import com.pdaniel.swapi.proxy.infrastructure.client.SWClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class ProxyService {

    @Autowired
    SWClient client;

    @Autowired
    FilmService filmService;

    @Autowired
    VehicleService vehiclesService;

    public ResponseDto getPersonInfo(String name) {

        List<PersonDto> personsList = new ArrayList<>();

        ResponseDto response = client.searchPeopleByName(name);

        for (PersonDto person : response.getResults()) {
            List<FilmDto> films = filmService.completeFilms(person.getFilms());
            person.setFilms(films);
            person.setPlanetName(getPlanetName(person.getHomeworld()));
            person.setFastestVehicleDriven(vehiclesService.getFastestVehicleName(person.getVehicles(), person.getStarships()));

            personsList.add(person);
        }

        response.setResults(personsList);

        return response;
    }

    private String getPlanetName(String url) {
        return client.getPlanetsById(Util.getID(url)).getName();
    }
}