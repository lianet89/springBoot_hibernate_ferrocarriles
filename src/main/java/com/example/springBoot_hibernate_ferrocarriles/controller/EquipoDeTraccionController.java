package com.example.springBoot_hibernate_ferrocarriles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBoot_hibernate_ferrocarriles.service.EquipoDeTraccionService;
import com.example.springBoot_hibernate_ferrocarriles.service.ViajeService;
import com.example.springBoot_hibernate_ferrocarriles.model.EquipoDeTraccion;
import com.example.springBoot_hibernate_ferrocarriles.model.Viaje;

import java.util.List;

@RestController
public class EquipoDeTraccionController {
	@Autowired
	EquipoDeTraccionService equipoDeTraccionService;

	public EquipoDeTraccionController() {
		super();
	}
	
	@GetMapping("/equipoDeTraccion")
	public List<EquipoDeTraccion> getAllEquipoDeTraccion(){
		return equipoDeTraccionService.getAllEquipoDeTraccion();
	}
	
	@GetMapping("/equipoDeTraccion/{idEquipoDeTraccion}")
	public EquipoDeTraccion getEquipoDeTraccionById(@PathVariable("idEquipoDeTraccion")Long idEquipoDeTraccion){
		return equipoDeTraccionService.getEquipoDeTraccionById(idEquipoDeTraccion);
	}
	
	@PostMapping("/equipoDeTraccion")
	public int addEquipoDeTraccion(@RequestBody EquipoDeTraccion equipoDeTraccion){
		equipoDeTraccionService.addOrUpdateEquipoDeTraccion(equipoDeTraccion);
		return equipoDeTraccion.getNumeroIdentificacion();
	}
	
	@PutMapping("/equipoDeTraccion")
	public EquipoDeTraccion updateEquipoDeTraccion(@RequestBody EquipoDeTraccion equipoDeTraccion){
		equipoDeTraccionService.addOrUpdateEquipoDeTraccion(equipoDeTraccion);
		return equipoDeTraccion;		
	}
	
	@DeleteMapping("/equipoDeTraccion/{idEquipoDeTraccion}")
	public void deleteEquipoDeTraccion(@PathVariable("idEquipoDeTraccion") Long idEquipoDeTraccion){
		equipoDeTraccionService.deleteEquipoDeTraccion(idEquipoDeTraccion);
	}

}
