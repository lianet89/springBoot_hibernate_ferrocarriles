package com.example.springBoot_hibernate_ferrocarriles.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBoot_hibernate_ferrocarriles.dto.ItineraryDto;
import com.example.springBoot_hibernate_ferrocarriles.model.Itinerario;
import com.example.springBoot_hibernate_ferrocarriles.service.ItinerarioService;

@RestController
public class ItinerarioController {	   
    @Autowired
    ItinerarioService itinerarioService;   
    
    @Autowired
	ModelMapper modelMapper;
       
    @GetMapping("/itineraries")
    private List<ItineraryDto> getAllItinerarios() throws Exception {
    	return itinerarioService.getAllItinerarios().stream().map(itineraties -> modelMapper.map(itineraties, ItineraryDto.class)).collect(Collectors.toList());
    }
    
    @GetMapping("/itineraries/{id}")
    private ItineraryDto getItinerarioById(@PathVariable("id")Long id) throws Exception {
    	return modelMapper.map(itinerarioService.getItinerarioById(id), ItineraryDto.class);
    }   
        
    @PostMapping("/itineraries")
    private ItineraryDto addItinerario(@RequestBody ItineraryDto itineraryDto) throws Exception {
    	Itinerario itineraryRequest = modelMapper.map(itineraryDto, Itinerario.class);
    	Itinerario itinerary = itinerarioService.addOrUpdateItinerario(itineraryRequest);
    	ItineraryDto itineraryResponse = modelMapper.map(itinerary, ItineraryDto.class);
    	return itineraryResponse; 	
    }
    
    @PutMapping("/itineraries")
    private ItineraryDto updateItinerario(@RequestBody ItineraryDto itineraryDto) throws Exception {
    	Itinerario itineraryRequest = modelMapper.map(itineraryDto, Itinerario.class);
    	Itinerario itinerary = itinerarioService.addOrUpdateItinerario(itineraryRequest);
    	ItineraryDto itineraryResponse = modelMapper.map(itinerary, ItineraryDto.class);
    	return itineraryResponse;
    }
    
    @DeleteMapping("/itineraries/{id}")
    private void deleteItinerario(@PathVariable("id")Long id) throws Exception {
    	itinerarioService.deleteItinerario(id);
    }
    
}
