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

	public CocheMotorController() {
		super();
	}
	
	@GetMapping("/cocheMotor")
    private List<CocheMotor> getAllItinerarios(){
    	return cocheMotorService.getAllCocheMotor();
    }
    
    @GetMapping("/cocheMotor/{cocheMotorId}")
    private CocheMotor getCocheMotorById(@PathVariable("cocheMotorId")Long cocheMotorId) {
    	return cocheMotorService.getCocheMotorById(cocheMotorId);
    }    
        
    @PostMapping("/cocheMotor")
    private int addCocheMotor(@RequestBody CocheMotor cocheMotor) {
    	cocheMotorService.addOrUpdateCocheMotor(cocheMotor);
    	return cocheMotor.getNumeroIdentificacion();
    }
    
    @PutMapping("/cocheMotor")
    private CocheMotor updateCocheMotor(@RequestBody CocheMotor cocheMotor) {
    	cocheMotorService.addOrUpdateCocheMotor(cocheMotor);
    	return cocheMotor;
    }
	
    @DeleteMapping("/cocheMotor/{cocheMotorId}")
    private void deleteCocheMotor(@PathVariable("cocheMotorId")Long cocheMotorId) {
    	cocheMotorService.deleteCocheMotor(cocheMotorId);
    }
	

}
