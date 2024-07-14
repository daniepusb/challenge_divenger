package com.pdaniel.swapi.proxy.api.controller;

import com.pdaniel.swapi.proxy.domain.services.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swapi-proxy")
public class ProxyController {

    @Autowired
    private ProxyService service;

    @GetMapping("/person-info")
    public ResponseEntity<Object> searchPersonByName(@RequestParam("name") String name) {
        var result = service.getPersonInfo(name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
