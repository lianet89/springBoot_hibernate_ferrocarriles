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
       
    @GetMapping("/itinerarios")
    private List<Itinerario> getAllItinerarios(){
    	return itinerarioService.getAllItinerarios();
    }
    
    @GetMapping("/itinerarios/{id}")
    private Itinerario getItinerarioById(@PathVariable("id")Long id) {
    	return itinerarioService.getItinerarioById(id);
    }   
        
    @PostMapping("/itinerarios")
    private Itinerario addItinerario(@RequestBody Itinerario itinerario) {
    	return itinerarioService.addOrUpdateItinerario(itinerario);    	
    }
    
    @PutMapping("/itinerarios")
    private Itinerario updateItinerario(@RequestBody Itinerario itinerario) {
    	return itinerarioService.addOrUpdateItinerario(itinerario);
    }
    
    @DeleteMapping("/itinerarios/{id}")
    private void deleteItinerario(@PathVariable("id")Long id) {
    	itinerarioService.deleteItinerario(id);
    }
    
}
