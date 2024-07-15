package com.pdaniel.swapi.proxy.api.controller;

import com.pdaniel.swapi.proxy.domain.dto.ResponseDto;
import com.pdaniel.swapi.proxy.domain.exception.PersonNotFoundException;
import com.pdaniel.swapi.proxy.domain.services.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/swapi-proxy")
public class ProxyController {

    @Autowired
    private ProxyService service;

    @GetMapping("/person-info")
    public ResponseEntity<ResponseDto> searchPersonByName(@RequestParam("name") String name) {
        log.info("Start");
        if (name == null || name.trim().isEmpty()) {
            throw new PersonNotFoundException("Name cannot be empty");
        }
        ResponseDto result = service.getPersonInfo(name);
        log.info("End");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
