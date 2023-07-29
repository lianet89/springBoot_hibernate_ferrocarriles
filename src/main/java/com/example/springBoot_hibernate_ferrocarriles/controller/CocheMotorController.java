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

import com.example.springBoot_hibernate_ferrocarriles.model.CocheMotor;
import com.example.springBoot_hibernate_ferrocarriles.service.CocheMotorService;

@RestController
public class CocheMotorController {
	@Autowired
	CocheMotorService cocheMotorService;
	
	@GetMapping("/coches-motor")
    private List<CocheMotor> getAllCocheSMotor(){
    	return cocheMotorService.getAllCocheMotor();
    }
    
    @GetMapping("/coches-motor/{id}")
    private CocheMotor getCocheMotorById(@PathVariable("id")Long id) {
    	return cocheMotorService.getCocheMotorById(id);
    }    
        
    @PostMapping("/coches-motor")
    private int addCocheMotor(@RequestBody CocheMotor cocheMotor) {
    	cocheMotorService.addOrUpdateCocheMotor(cocheMotor);
    	return cocheMotor.getNumeroIdentificacion();
    }
    
    @PutMapping("/coches-motor")
    private CocheMotor updateCocheMotor(@RequestBody CocheMotor cocheMotor) {
    	return cocheMotorService.addOrUpdateCocheMotor(cocheMotor);
    }
	
    @DeleteMapping("/coches-motor/{id}")
    private void deleteCocheMotor(@PathVariable("id")Long id) {
    	cocheMotorService.deleteCocheMotor(id);
    }
	

}
