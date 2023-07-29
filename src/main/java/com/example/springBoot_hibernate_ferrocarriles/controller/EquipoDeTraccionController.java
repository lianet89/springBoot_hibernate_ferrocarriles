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
import com.example.springBoot_hibernate_ferrocarriles.model.EquipoDeTraccion;


import java.util.List;

@RestController
public class EquipoDeTraccionController {
	@Autowired
	EquipoDeTraccionService equipoDeTraccionService;
	
	@GetMapping("/equipos-de-traccion")
	public List<EquipoDeTraccion> getAllEquipoDeTraccion(){
		return equipoDeTraccionService.getAllEquipoDeTraccion();
	}
	
	@GetMapping("/equipos-de-traccion/{id}")
	public EquipoDeTraccion getEquipoDeTraccionById(@PathVariable("id")Long id){
		return equipoDeTraccionService.getEquipoDeTraccionById(id);
	}
	
	@PostMapping("/equipos-de-traccion")
	public EquipoDeTraccion addEquipoDeTraccion(@RequestBody EquipoDeTraccion equipoDeTraccion){
		return equipoDeTraccionService.addOrUpdateEquipoDeTraccion(equipoDeTraccion);
	}
	
	@PutMapping("/equipos-de-traccion")
	public EquipoDeTraccion updateEquipoDeTraccion(@RequestBody EquipoDeTraccion equipoDeTraccion){
		return equipoDeTraccionService.addOrUpdateEquipoDeTraccion(equipoDeTraccion);	
	}
	
	@DeleteMapping("/equipos-de-traccion/{id}")
	public void deleteEquipoDeTraccion(@PathVariable("id") Long id){
		equipoDeTraccionService.deleteEquipoDeTraccion(id);
	}

}
