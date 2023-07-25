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

import com.example.springBoot_hibernate_ferrocarriles.model.Locomotora;
import com.example.springBoot_hibernate_ferrocarriles.service.LocomotoraService;

@RestController
public class LocomotoraController {
	@Autowired
	LocomotoraService locomotoraService;

	public LocomotoraController() {
		super();
	}
	
	@GetMapping("/locomotora")
	public List<Locomotora> getAllLocomotora(){
		return locomotoraService.getAllLocomotora();
	}
	
	@GetMapping("/locomotora/{locomotoraId}")
	public Locomotora getLocomotoraById(@PathVariable("locomotoraId")Long locomotoraId) {
		return locomotoraService.getLocomotoraById(locomotoraId);
	}
	
	@PostMapping("/locomotora")
	public int addLocomotora(@RequestBody Locomotora locomotora){
		locomotoraService.addOrUpdateLocomotora(locomotora);
		return locomotora.getNumeroIdentificacion();		
	}
	
	@PutMapping("/locomotora")
	public Locomotora updateLocomotora(@RequestBody Locomotora locomotora){
		locomotoraService.addOrUpdateLocomotora(locomotora);
		return locomotora;
	}
	
	@DeleteMapping("/locomotora/{locomotoraId}")
	public void deleteLocomotora(@PathVariable("locomotoraId") Long locomotoraId) {
		locomotoraService.deleteLocomotora(locomotoraId);
	}

}
