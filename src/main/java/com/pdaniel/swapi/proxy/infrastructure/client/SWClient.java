package com.pdaniel.swapi.proxy.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "star-wars", url = "https://swapi.dev/api")
public interface SWClient {

    @GetMapping("/people/")
    Object searchPeopleByName(@RequestParam("search") String name);

}
