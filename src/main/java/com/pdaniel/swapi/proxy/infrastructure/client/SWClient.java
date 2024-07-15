package com.pdaniel.swapi.proxy.infrastructure.client;

import com.pdaniel.swapi.proxy.domain.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "star-wars", url = "https://swapi.dev/api")
public interface SWClient {

    @GetMapping("/people/")
    ResponseDto searchPeopleByName(@RequestParam("search") String name);

    @GetMapping("/films/{id}")
    FilmDto getFilmById(@PathVariable("id") String id);

    @GetMapping("/planets/{id}")
    PlanetDto getPlanetsById(@PathVariable("id") String id);

    @GetMapping("/starships/{id}")
    StarshipDto getStarshipById(@PathVariable("id") String id);

    @GetMapping("/vehicles/{id}")
    VehicleDto getVehiclesById(@PathVariable("id") String id);
}
