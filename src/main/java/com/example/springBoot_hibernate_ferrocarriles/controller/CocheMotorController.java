package com.example.springBoot_hibernate_ferrocarriles.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private MotorCarDto getCocheMotorById(@PathVariable("id")Long id) throws Exception {
    	return modelMapper.map(cocheMotorService.getCocheMotorById(id), MotorCarDto.class);
    }    
        
    @PostMapping("/motor-cars")
    private MotorCarDto addCocheMotor(@Valid @RequestBody MotorCarDto motorCarDto) throws Exception {
    	CocheMotor motorCarRequest = modelMapper.map(motorCarDto, CocheMotor.class);
    	CocheMotor motorCar = cocheMotorService.addOrUpdateCocheMotor(motorCarRequest);
    	MotorCarDto motorCarResponse = modelMapper.map(motorCar, MotorCarDto.class);
    	return motorCarResponse;
    }
    
    @PutMapping("/motor-cars")
    private MotorCarDto updateCocheMotor(@RequestBody MotorCarDto motorCarDto) throws Exception {
    	CocheMotor motorCarRequest = modelMapper.map(motorCarDto, CocheMotor.class);
    	CocheMotor motorCar = cocheMotorService.addOrUpdateCocheMotor(motorCarRequest);
    	MotorCarDto motorCarResponse = modelMapper.map(motorCar, MotorCarDto.class);
    	return motorCarResponse;
    }
	
    @DeleteMapping("/motor-cars/{id}")
    private void deleteCocheMotor(@PathVariable("id")Long id) throws Exception {
    	cocheMotorService.deleteCocheMotor(id);
    }
	

}
