package com.example.springBoot_hibernate_ferrocarriles.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBoot_hibernate_ferrocarriles.service.ViajeService;

import jakarta.validation.Valid;

import com.example.springBoot_hibernate_ferrocarriles.service.EquipoDeTraccionService;
import com.example.springBoot_hibernate_ferrocarriles.dto.ItineraryDto;
import com.example.springBoot_hibernate_ferrocarriles.dto.TravelDto;
import com.example.springBoot_hibernate_ferrocarriles.model.Itinerario;
import com.example.springBoot_hibernate_ferrocarriles.model.Viaje;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ViajeController {
	@Autowired
	ViajeService viajeService;
	
	@Autowired
	EquipoDeTraccionService equipoDeTraccionService;
	
	@Autowired
	ModelMapper modelMapper;

	@GetMapping("/travels")
	public List<TravelDto> getAllTravels() throws Exception {
		return viajeService.getAllViajes().stream().map(travel -> modelMapper.map(travel, TravelDto.class)).collect(Collectors.toList());
	}
	
	@GetMapping("/travels/{id}")
	public TravelDto getTravelById(@PathVariable("id")Long id) throws Exception {
		return modelMapper.map(viajeService.getViajeById(id), TravelDto.class);
	}
	
	@PostMapping("/travels")
	public TravelDto addTravel(@Valid @RequestBody ItineraryDto itineraryDto) throws Exception {	
		Itinerario itineraryRequest = modelMapper.map(itineraryDto, Itinerario.class);
		Viaje travel = viajeService.addViaje(itineraryRequest);
		TravelDto travelResponse = modelMapper.map(travel, TravelDto.class);
    	return travelResponse;
	}	
	
	@PutMapping("/travels")
	public TravelDto updateTravel(@RequestBody TravelDto travelDto) throws Exception {
		Viaje travelRequest = modelMapper.map(travelDto, Viaje.class);
		Viaje travel = viajeService.addOrUpdateViaje(travelRequest);
		TravelDto travelResponse = modelMapper.map(travel, TravelDto.class);
    	return travelResponse;
	}
	
	@DeleteMapping("/travels/{id}")
	public void deleteTravel(@PathVariable("id") Long id) throws Exception {
		viajeService.deleteViaje(id);
	}

}
