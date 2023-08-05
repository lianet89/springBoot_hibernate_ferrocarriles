package com.example.springBoot_hibernate_ferrocarriles.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.springBoot_hibernate_ferrocarriles.service.EquipoDeTraccionService;

import jakarta.validation.Valid;

<<<<<<< Updated upstream
=======
import com.example.springBoot_hibernate_ferrocarriles.dto.MotorCarDto;
>>>>>>> Stashed changes
import com.example.springBoot_hibernate_ferrocarriles.dto.TractionEquipmentDto;
import com.example.springBoot_hibernate_ferrocarriles.model.EquipoDeTraccion;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EquipoDeTraccionController {
	@Autowired
	EquipoDeTraccionService equipoDeTraccionService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("/traction-equipments")
	public ResponseEntity<List<TractionEquipmentDto>> getAllEquipoDeTraccion() throws Exception {
		List<TractionEquipmentDto> listResponse = equipoDeTraccionService.getAllEquipoDeTraccion().stream().map(tractionEquipment -> modelMapper.map(tractionEquipment, TractionEquipmentDto.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listResponse);
	}
	
	@GetMapping("/traction-equipments/{id}")
	public ResponseEntity<TractionEquipmentDto> getEquipoDeTraccionById(@PathVariable("id") Long id) throws Exception {
<<<<<<< Updated upstream
		TractionEquipmentDto tractionEquipmentResponse = modelMapper.map(equipoDeTraccionService.getEquipoDeTraccionById(id), TractionEquipmentDto.class);
		return ResponseEntity.ok().body(tractionEquipmentResponse);
=======
		EquipoDeTraccion tractionEquipmentRequest = equipoDeTraccionService.getEquipoDeTraccionById(id);
		TractionEquipmentDto tractionEquipmentResponse = modelMapper.map(tractionEquipmentRequest, TractionEquipmentDto.class);
		if(tractionEquipmentRequest.getNumeroIdentificacion() != id) {
    		return new ResponseEntity<TractionEquipmentDto>( tractionEquipmentResponse, HttpStatus.NOT_FOUND);
    	} else {     		
    		return ResponseEntity.ok().body(tractionEquipmentResponse);
    	}		
>>>>>>> Stashed changes
	}
	/*
	@PostMapping("/traction-equipments")
	public ResponseEntity<TractionEquipmentDto> addEquipoDeTraccion(@Valid @RequestBody TractionEquipmentDto tractionEquipmentDto) throws Exception {
		EquipoDeTraccion tractionEquipmentRequest = modelMapper.map(tractionEquipmentDto, EquipoDeTraccion.class);
		EquipoDeTraccion tractionEquipment = equipoDeTraccionService.addEquipoDeTraccion(tractionEquipmentRequest);
		TractionEquipmentDto tractionEquipmentResponse = modelMapper.map(tractionEquipment, TractionEquipmentDto.class);
    	return new ResponseEntity<TractionEquipmentDto>(tractionEquipmentResponse, HttpStatus.CREATED);
    }
	
	@PutMapping("/traction-equipments/{id}")
	public ResponseEntity<TractionEquipmentDto> updateEquipoDeTraccion(@Valid @PathVariable("id") Long id, @RequestBody TractionEquipmentDto tractionEquipmentDto) throws Exception {
		EquipoDeTraccion tractionEquipmentRequest = modelMapper.map(tractionEquipmentDto, EquipoDeTraccion.class);
		EquipoDeTraccion tractionEquipment = equipoDeTraccionService.updateEquipoDeTraccion(id, tractionEquipmentRequest);
		TractionEquipmentDto tractionEquipmentResponse = modelMapper.map(tractionEquipment, TractionEquipmentDto.class);
    	return ResponseEntity.ok().body(tractionEquipmentResponse);
	}
	*/
	@DeleteMapping("/traction-equipments/{id}")
	public ResponseEntity<String> deleteEquipoDeTraccion(@PathVariable("id") Long id) throws Exception {
<<<<<<< Updated upstream
		equipoDeTraccionService.deleteEquipoDeTraccion(id);
		String stringResponse = "Traction equipment deleted successfully";
    	return ResponseEntity.ok(stringResponse); 
=======
		EquipoDeTraccion tractionEquipment = equipoDeTraccionService.getEquipoDeTraccionById(id);
		if(tractionEquipment.getNumeroIdentificacion() != id) {
    		return new ResponseEntity<String>("Traction equipment not found.", HttpStatus.NOT_FOUND);
    	} else {
    		equipoDeTraccionService.deleteEquipoDeTraccion(id);
	    	return new ResponseEntity<String>("Traction equipment deleted successfully", HttpStatus.OK);
    	}
		
>>>>>>> Stashed changes
	}

}
