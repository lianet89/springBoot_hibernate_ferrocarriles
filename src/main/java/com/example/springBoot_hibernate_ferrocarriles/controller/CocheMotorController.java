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
    private List<MotorCarDto> getAllCocheSMotor() throws Exception {
    	return cocheMotorService.getAllCocheMotor().stream().map(motorCar -> modelMapper.map(motorCar, MotorCarDto.class)).collect(Collectors.toList());
    }
    
    @GetMapping("/motor-cars/{id}")
    private ResponseEntity<MotorCarDto> getCocheMotorById(@PathVariable("id")Long id) throws Exception {
    	MotorCarDto motorCarResponse = modelMapper.map(cocheMotorService.getCocheMotorById(id), MotorCarDto.class);
    	return ResponseEntity.ok().body(motorCarResponse);
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
    	CocheMotor motorCarRequest = modelMapper.map(motorCarDto, CocheMotor.class);
    	CocheMotor motorCar = cocheMotorService.updateCocheMotor(id, motorCarRequest);
    	MotorCarDto motorCarResponse = modelMapper.map(motorCar, MotorCarDto.class);
    	return ResponseEntity.ok().body(motorCarResponse);
    }
	
    @DeleteMapping("/motor-cars/{id}")
    private ResponseEntity<String> deleteCocheMotor(@PathVariable("id")Long id) throws Exception {
    	cocheMotorService.deleteCocheMotor(id);
    	String stringResponse = "Motor car deleted successfully";
    	return ResponseEntity.ok(stringResponse); 
    }
	

}
