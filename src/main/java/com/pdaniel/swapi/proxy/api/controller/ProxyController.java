package com.pdaniel.swapi.proxy.api.controller;

import com.pdaniel.swapi.proxy.domain.dto.ResponseDto;
import com.pdaniel.swapi.proxy.domain.exception.PersonNotFoundException;
import com.pdaniel.swapi.proxy.domain.services.ProxyService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;

@Slf4j
@RestController
@RequestMapping("/swapi-proxy")
public class ProxyController {

    @Autowired
    private ProxyService service;

    @Operation(summary = "Get an person of Star Wars API given a name",
        description = "Get an existing person by Name. The response is information about Person of Star Wars." +
            "Should work for any of the character names included in swapi. " +
            "If the name is incorrect, it should respond with a JSON error and a 404 code.\n" +
            "The fastest_vehicle_driven attribute should be filled with the name of the fastest \"vehicle\" or \"starship\"" +
            " (with highest max_atmosphering_speed) driven by the character.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the person"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    @GetMapping("/person-info")
    public ResponseEntity<ResponseDto> searchPersonByName(@RequestParam("name") String name) {
        log.info("Start");
        if (name == null || name.trim().isEmpty()) {
            throw new PersonNotFoundException("Name cannot be empty");
        }
        ResponseDto result = service.getPersonInfo(name);
        if (result == null || result.getResults().isEmpty()) {
            throw new PersonNotFoundException("Person not found with name: " + name);
        }
        log.info("End");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
