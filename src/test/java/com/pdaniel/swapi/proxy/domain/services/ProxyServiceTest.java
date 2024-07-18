package com.pdaniel.swapi.proxy.domain.services;

import com.pdaniel.swapi.proxy.config.CachingConfig;
import com.pdaniel.swapi.proxy.domain.dto.*;
import com.pdaniel.swapi.proxy.infrastructure.client.SWClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest//(classes = CachingConfig.class)
class ProxyServiceTest {
    @Autowired
    CacheManager cacheManager;

    @Mock
    private SWClient swClient;

    @Mock
    private VehicleService vehicleService;

    @InjectMocks
    private ProxyService proxyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPersonInfo_Cacheable() {
        ResponseDto mockResponse = new ResponseDto();

        PersonDto mockPerson = new PersonDto();
        mockPerson.setName("Luke Skywalker");
        mockPerson.setHomeworld("https://swapi.co/api/planets/1/");
        mockPerson.setBirthYear("19BBY");
        mockPerson.setGender("male");
        mockPerson.setHomeworld("https://swapi.co/api/planets/1/");

        PlanetDto mockPlanet = new PlanetDto();
        mockPlanet.setName("Tatooine");

        VehicleDto mockVehicle = new VehicleDto();
        mockVehicle.setUrl("https://swapi.co/api/vehicles/14/");
        mockVehicle.setMaxAtmospheringSpeed("950");
        mockVehicle.setName("X-Wing");

        StarshipDto mockStarship = new StarshipDto();
        mockStarship.setUrl("https://swapi.co/api/starships/12/");
        mockStarship.setMaxAtmospheringSpeed("50");
        mockStarship.setName("X-Wing");

        mockResponse.setResults(Collections.singletonList(mockPerson));

        // When searching for "Luke Skywalker"
        when(swClient.searchPeopleByName("Luke Skywalker")).thenReturn(mockResponse);
        when(swClient.getPlanetsById("1")).thenReturn(mockPlanet);
        when(swClient.getVehiclesById("14")).thenReturn(mockVehicle);
        when(swClient.getStarshipById("12")).thenReturn(mockStarship);
        when(vehicleService.getFastestVehicleName(  Collections.singletonList(mockVehicle),
                                                    Collections.singletonList(mockStarship)))
            .thenReturn("X-Wing");

        // Call the method the first time, should invoke the SWClient
        ResponseDto response1 = proxyService.getPersonInfo("Luke Skywalker");
        assertNotNull(response1);
        assertEquals(1, response1.getResults().size());

        // Call the method the second time, should return cached result
        ResponseDto response2 = proxyService.getPersonInfo("Luke Skywalker");
        assertNotNull(response2);
        assertEquals(1, response2.getResults().size());

        //assertEquals(response2, getCachedPersonInfo("personInfo"));
        // Verify SWClient was only called once
        verify(swClient, times(1)).searchPeopleByName("Luke Skywalker");
    }

    private Optional<ResponseDto> getCachedPersonInfo(String title) {
        return ofNullable(cacheManager.getCache("personInfo")).map(c -> c.get(title, ResponseDto.class));
    }


/*
    @Test
    public void testCacheEviction() {
        assertNotNull(cacheManager);

        // Llamar al método que debería almacenar en caché datos
        proxyService.getPersonInfo("Luke Skywalker");
        assertNotNull(cacheManager.getCache("personInfo").get("Luke Skywalker"));

        // Llamar al método que debería invalidar la caché
        proxyService.clearPersonInfoCache("Luke Skywalker");
        assertNull(cacheManager.getCache("personInfo").get("Luke Skywalker"));
    }
    */


}
