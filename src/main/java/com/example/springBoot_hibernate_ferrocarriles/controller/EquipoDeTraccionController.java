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
	public List<TractionEquipmentDto> getAllEquipoDeTraccion() throws Exception {
		return equipoDeTraccionService.getAllEquipoDeTraccion().stream().map(tractionEquipment -> modelMapper.map(tractionEquipment, TractionEquipmentDto.class)).collect(Collectors.toList());
	}
	
	@GetMapping("/traction-equipments/{id}")
	public ResponseEntity<TractionEquipmentDto> getEquipoDeTraccionById(@PathVariable("id") Long id) throws Exception {
		TractionEquipmentDto tractionEquipmentResponse = modelMapper.map(equipoDeTraccionService.getEquipoDeTraccionById(id), TractionEquipmentDto.class);
		return ResponseEntity.ok().body(tractionEquipmentResponse);
	}
	
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
	
	@DeleteMapping("/traction-equipments/{id}")
	public ResponseEntity<String> deleteEquipoDeTraccion(@PathVariable("id") Long id) throws Exception {
		equipoDeTraccionService.deleteEquipoDeTraccion(id);
		String stringResponse = "Traction equipment deleted successfully";
    	return ResponseEntity.ok(stringResponse); 
	}

}
