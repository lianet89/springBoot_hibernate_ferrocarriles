package com.example.springBoot_hibernate_ferrocarriles.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBoot_hibernate_ferrocarriles.dto.MotorCarDto;
import com.example.springBoot_hibernate_ferrocarriles.model.CocheMotor;
import com.example.springBoot_hibernate_ferrocarriles.service.CocheMotorService;

import jakarta.validation.Valid;

@RestController
public class CocheMotorController {
	@Autowired
	CocheMotorService cocheMotorService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("/motor-cars")
    private ResponseEntity<List<MotorCarDto>> getAllCocheSMotor() throws Exception {
		List<MotorCarDto> listResponse = cocheMotorService.getAllCocheMotor().stream().map(motorCar -> modelMapper.map(motorCar, MotorCarDto.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listResponse);
    }
    
    @GetMapping("/motor-cars/{id}")
    private ResponseEntity<MotorCarDto> getCocheMotorById(@PathVariable("id")Long id) throws Exception {
<<<<<<< Updated upstream
    	MotorCarDto motorCarResponse = modelMapper.map(cocheMotorService.getCocheMotorById(id), MotorCarDto.class);
    	return ResponseEntity.ok().body(motorCarResponse);
=======
    	CocheMotor motorCar = cocheMotorService.getCocheMotorById(id);
    	MotorCarDto motorCarResponse = modelMapper.map(motorCar, MotorCarDto.class);
    	if(motorCar.getNumeroIdentificacion() != id) {
    		return new ResponseEntity<MotorCarDto>(motorCarResponse, HttpStatus.NOT_FOUND);
    	} else {     		
    		return ResponseEntity.ok().body(motorCarResponse);
    	}
>>>>>>> Stashed changes
    }    
        
    @PostMapping("/motor-cars")
    private ResponseEntity<MotorCarDto> addCocheMotor(@Valid @RequestBody MotorCarDto motorCarDto) throws Exception {
    	CocheMotor motorCarRequest = modelMapper.map(motorCarDto, CocheMotor.class);
    	CocheMotor motorCar = cocheMotorService.addCocheMotor(motorCarRequest);
    	MotorCarDto motorCarResponseDto = modelMapper.map(motorCar, MotorCarDto.class);
    	return new ResponseEntity<MotorCarDto>(motorCarResponseDto, HttpStatus.CREATED);
    }
    
    @PutMapping("/motor-cars/{id}")
    private ResponseEntity<MotorCarDto> updateCocheMotor(@Valid @PathVariable("id") Long id, @RequestBody MotorCarDto motorCarDto) throws Exception {
<<<<<<< Updated upstream
    	CocheMotor motorCarRequest = modelMapper.map(motorCarDto, CocheMotor.class);
    	CocheMotor motorCar = cocheMotorService.updateCocheMotor(id, motorCarRequest);
    	MotorCarDto motorCarResponse = modelMapper.map(motorCar, MotorCarDto.class);
    	return ResponseEntity.ok().body(motorCarResponse);
=======
    	CocheMotor motorCar = cocheMotorService.getCocheMotorById(id);
    	if(motorCar.getNumeroIdentificacion() != id) {
    		return new ResponseEntity<MotorCarDto>(modelMapper.map(motorCar, MotorCarDto.class), HttpStatus.NOT_FOUND);
    	} else {    	
	    	CocheMotor motorCarRequest = modelMapper.map(motorCarDto, CocheMotor.class);
	    	CocheMotor motorCarUpdated = cocheMotorService.updateCocheMotor(id, motorCarRequest);
	    	MotorCarDto motorCarResponse = modelMapper.map(motorCarUpdated, MotorCarDto.class);
	    	return ResponseEntity.ok().body(motorCarResponse);
    	}
>>>>>>> Stashed changes
    }
	
    @DeleteMapping("/motor-cars/{id}")
    private ResponseEntity<String> deleteCocheMotor(@PathVariable("id")Long id) throws Exception {
<<<<<<< Updated upstream
    	cocheMotorService.deleteCocheMotor(id);
    	String stringResponse = "Motor car deleted successfully";
    	return ResponseEntity.ok(stringResponse); 
=======
    	CocheMotor coche = cocheMotorService.getCocheMotorById(id);
    	if(coche.getNumeroIdentificacion() != id) {
    		return new ResponseEntity<String>("Motor car not found.", HttpStatus.NOT_FOUND);
    	} else {
	    	cocheMotorService.deleteCocheMotor(id);
	    	return new ResponseEntity<String>("Motor car deleted successfully", HttpStatus.OK);
    	}
>>>>>>> Stashed changes
    }
	

}
