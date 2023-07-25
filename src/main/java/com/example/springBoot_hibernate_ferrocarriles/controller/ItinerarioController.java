package com.example.springBoot_hibernate_ferrocarriles.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBoot_hibernate_ferrocarriles.model.Itinerario;
import com.example.springBoot_hibernate_ferrocarriles.service.ItinerarioService;

@RestController
public class ItinerarioController {	   
    @Autowired
    ItinerarioService itinerarioService;   
       
    public ItinerarioController() {
		super();
	}

	@GetMapping("/itinerario")
    private List<Itinerario> getAllItinerarios(){
    	return itinerarioService.getAllItinerarios();
    }
    
    @GetMapping("/itinerario/{itinerarioId}")
    private Itinerario getItinerarioById(@PathVariable("itinerarioId")Long itinerarioId) {
    	return itinerarioService.getItinerarioById(itinerarioId);
    }   
        
    @PostMapping("/itinerario")
    private int addItinerario(@RequestBody Itinerario itinerario) {
    	itinerarioService.addOrUpdateItinerario(itinerario);
    	return itinerario.getNumeroIdentificacion();
    }
    
    @PutMapping("/itinerario")
    private Itinerario updateItinerario(@RequestBody Itinerario itinerario) {
    	itinerarioService.addOrUpdateItinerario(itinerario);
    	return itinerario;
    }
    
    @DeleteMapping("/itinerario/{itinerarioId}")
    private void deleteItinerario(@PathVariable("itinerarioId")Long itinerarioId) {
    	itinerarioService.deleteItinerario(itinerarioId);
    }
    
}
