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
	
	@GetMapping("/locomotoras")
	public List<Locomotora> getAllLocomotoras(){
		return locomotoraService.getAllLocomotora();
	}
	
	@GetMapping("/locomotoras/{id}")
	public Locomotora getLocomotoraById(@PathVariable("id") Long id) {
		return locomotoraService.getLocomotoraById(id);
	}
	
	@PostMapping("/locomotoras")
	public Locomotora addLocomotora(@RequestBody Locomotora locomotora){
		return locomotoraService.addOrUpdateLocomotora(locomotora);
	}
	
	@PutMapping("/locomotoras")
	public Locomotora updateLocomotora(@RequestBody Locomotora locomotora){
		return locomotoraService.addOrUpdateLocomotora(locomotora);
	}
	
	@DeleteMapping("/locomotoras/{id}")
	public void deleteLocomotora(@PathVariable("id") Long id) {
		locomotoraService.deleteLocomotora(id);
	}

}
