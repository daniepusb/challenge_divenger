package com.pdaniel.swapi.proxy.infrastructure.client;

import com.pdaniel.swapi.proxy.domain.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "star-wars", url = "${swapi-service.url}")
public interface SWClient {

    @GetMapping("${swapi-service.endpoint.people}")
    ResponseDto searchPeopleByName(@RequestParam("search") String name);

    @GetMapping("${swapi-service.endpoint.film}/{id}")
    FilmDto getFilmById(@PathVariable("id") String id);

    @GetMapping("${swapi-service.endpoint.planet}/{id}")
    PlanetDto getPlanetsById(@PathVariable("id") String id);

    @GetMapping("${swapi-service.endpoint.starship}/{id}")
    StarshipDto getStarshipById(@PathVariable("id") String id);

    @GetMapping("${swapi-service.endpoint.vehicle}/{id}")
    VehicleDto getVehiclesById(@PathVariable("id") String id);
}
