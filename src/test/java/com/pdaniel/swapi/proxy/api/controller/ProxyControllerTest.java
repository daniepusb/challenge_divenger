package com.pdaniel.swapi.proxy.api.controller;

import com.pdaniel.swapi.proxy.domain.dto.ResponseDto;
import com.pdaniel.swapi.proxy.domain.services.ProxyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.Mockito.when;

@WebMvcTest(ProxyController.class)
class ProxyControllerTest {

    @MockBean
    private ProxyService proxyService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSearchPersonByName_givenNoName_ReturnsNotFound() throws Exception {

        ResponseDto response = new ResponseDto();
        response.setResults(Collections.emptyList());

        when(proxyService.getPersonInfo("noSWname"))
                .thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.get("/swapi-proxy/person-info")
                .param("name", "noSWname")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}