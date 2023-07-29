package com.example.springBoot_hibernate_ferrocarriles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBoot_hibernate_ferrocarriles.service.ViajeService;
import com.example.springBoot_hibernate_ferrocarriles.service.EquipoDeTraccionService;
import com.example.springBoot_hibernate_ferrocarriles.model.Itinerario;
import com.example.springBoot_hibernate_ferrocarriles.model.Viaje;

import java.util.List;

@RestController
public class ViajeController {
	@Autowired
	ViajeService viajeService;
	@Autowired
	EquipoDeTraccionService equipoDeTraccionService;

	@GetMapping("/travels")
	public List<Viaje> getAllTravels(){
		return viajeService.getAllViajes();
	}
	
	@GetMapping("/travels/{id}")
	public Viaje getTravelById(@PathVariable("id")Long id){
		return viajeService.getViajeById(id);
	}
	
	@PostMapping("/travels")
	public Viaje addTravel(@RequestBody Itinerario itinerario){	
		return viajeService.addViaje(itinerario);
	}	
	
	@PutMapping("/travels")
	public Viaje updateTravel(@RequestBody Viaje viaje){		
		return viajeService.addOrUpdateViaje(viaje);	
	}
	
	@DeleteMapping("/travels/{id}")
	public void deleteTravel(@PathVariable("id") Long id){
		viajeService.deleteViaje(id);
	}

}
