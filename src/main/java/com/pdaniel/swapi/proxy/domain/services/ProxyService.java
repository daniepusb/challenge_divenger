package com.pdaniel.swapi.proxy.domain.services;

import lombok.NoArgsConstructor;

import com.pdaniel.swapi.proxy.infrastructure.client.SWClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@NoArgsConstructor
public class ProxyService {

    @Autowired
    SWClient client;

    public Object getPersonInfo(String name) {

        var object = client.searchPeopleByName(name);

        return object;
    }
}