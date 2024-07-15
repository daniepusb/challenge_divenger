package com.pdaniel.swapi.proxy.domain.services;

import com.pdaniel.swapi.proxy.domain.dto.FilmDto;
import com.pdaniel.swapi.proxy.domain.dto.PersonDto;
import com.pdaniel.swapi.proxy.domain.dto.ResponseDto;
import com.pdaniel.swapi.proxy.domain.exception.PersonNotFoundException;
import com.pdaniel.swapi.proxy.domain.exception.VehicleNotFoundException;
import com.pdaniel.swapi.proxy.infrastructure.client.SWClient;
import com.pdaniel.swapi.proxy.util.Util;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
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
        log.info("getPersonInfo: " + name);

        ResponseDto response = null;
        try {
            response = client.searchPeopleByName(name);
            log.info("client.searchPeopleByName()");

            if (response == null || response.getResults() == null || response.getResults().isEmpty()) {
                throw new PersonNotFoundException("Person not found with the given name");
            }
        } catch (PersonNotFoundException e) {
            log.error(e.getMessage());
            throw new PersonNotFoundException("Person not found with the given name");
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        List<PersonDto> personsList = new ArrayList<>();

        for (PersonDto person : response.getResults()) {
            try {
                log.info("filmService.completeFilms()");
                List<FilmDto> films = filmService.completeFilms(person.getFilms());
                person.setFilms(films);
            } catch (Exception e) {
                log.error("Films not found: "+e.getMessage());
            }

            log.info("getPlanetName()");
            person.setPlanetName(getPlanetName(person.getHomeworld()));

            try {
                log.info("setFastestVehicleDriven()");
                person.setFastestVehicleDriven(vehiclesService.getFastestVehicleName(person.getVehicles(), person.getStarships()));
            } catch (VehicleNotFoundException e) {
                log.error(e.getMessage());
            }

            personsList.add(person);
        }

        response.setResults(personsList);

        return response;
    }

    private String getPlanetName(String url) {
        return client.getPlanetsById(Util.getID(url)).getName();
    }
}