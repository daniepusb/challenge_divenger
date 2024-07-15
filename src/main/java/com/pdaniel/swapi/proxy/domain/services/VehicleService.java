package com.pdaniel.swapi.proxy.domain.services;

import com.pdaniel.swapi.proxy.domain.dto.StarshipDto;
import com.pdaniel.swapi.proxy.domain.dto.VehicleDto;
import com.pdaniel.swapi.proxy.domain.exception.VehicleNotFoundException;
import com.pdaniel.swapi.proxy.infrastructure.client.SWClient;
import com.pdaniel.swapi.proxy.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VehicleService {
	
	@Autowired
	SWClient client;

	public List<VehicleDto>  completeVehicles(List<VehicleDto> vehicles){

		return vehicles.stream().map(vechicleUrl ->{
				VehicleDto v = new VehicleDto();
				var vehicle = client.getVehiclesById(Util.getID(vechicleUrl.getUrl()));
				v.setName(vehicle.getName());
				v.setMaxAtmospheringSpeed(vehicle.getMaxAtmospheringSpeed());
				return v;
			}).toList();
	}

	public List<StarshipDto> completeStarships(List<StarshipDto> starships){

		return starships.stream().map(starshipUrl ->{
					StarshipDto v = new StarshipDto();
					var vehicle = client.getStarshipById(Util.getID(starshipUrl.getUrl()));
					v.setName(vehicle.getName());
					v.setMaxAtmospheringSpeed(vehicle.getMaxAtmospheringSpeed());
					return v;
				}).collect(Collectors.toList());
	}


	public String getFastestVehicleName(List<VehicleDto> vehicles, List<StarshipDto> starships) {

		Optional<VehicleDto> vehicleFastest = Optional.empty();
		Optional<StarshipDto> starshipFastest = Optional.empty();

		if (!vehicles.isEmpty()) {
			log.info("getFastestVehicleOrStarship: " + vehicles.get(0).getUrl());
			var vehiclesCompletes = completeVehicles(vehicles);
			vehicleFastest = vehiclesCompletes.stream().max(Comparator.comparing(VehicleDto::getMaxAtmospheringSpeedToInt));
		}
		if (!starships.isEmpty()) {
			log.info("getFastestVehicleOrStarship: " + starships.get(0).getUrl());
			var starshipsCompletes = completeStarships(starships);
			starshipFastest = starshipsCompletes.stream().max(Comparator.comparing(StarshipDto::getMaxAtmospheringSpeedToInt));
		}

		if (vehicleFastest.isPresent() && starshipFastest.isPresent()) {
			return vehicleFastest.get().getMaxAtmospheringSpeedToInt() > starshipFastest.get().getMaxAtmospheringSpeedToInt()
					? vehicleFastest.get().getName() : starshipFastest.get().getName();
		} else if (vehicleFastest.isPresent()) {
			return vehicleFastest.get().getName();
		} else if (starshipFastest.isPresent()) {
			return starshipFastest.get().getName();
		} else {
			return "";
		}

	}

}
