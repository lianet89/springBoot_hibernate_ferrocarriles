package com.example.springBoot_hibernate_ferrocarriles.controller;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBoot_hibernate_ferrocarriles.service.EquipoDeTraccionService;
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
	public TractionEquipmentDto getEquipoDeTraccionById(@PathVariable("id")Long id) throws Exception {
		return modelMapper.map(equipoDeTraccionService.getEquipoDeTraccionById(id), TractionEquipmentDto.class);
	}
	
	@PostMapping("/traction-equipments")
	public TractionEquipmentDto addEquipoDeTraccion(@RequestBody TractionEquipmentDto tractionEquipmentDto) throws Exception {
		EquipoDeTraccion tractionEquipmentRequest = modelMapper.map(tractionEquipmentDto, EquipoDeTraccion.class);
		EquipoDeTraccion tractionEquipment = equipoDeTraccionService.addOrUpdateEquipoDeTraccion(tractionEquipmentRequest);
		TractionEquipmentDto tractionEquipmentResponse = modelMapper.map(tractionEquipment, TractionEquipmentDto.class);
    	return tractionEquipmentResponse;
    }
	
	@PutMapping("/traction-equipments")
	public TractionEquipmentDto updateEquipoDeTraccion(@RequestBody TractionEquipmentDto tractionEquipmentDto) throws Exception {
		EquipoDeTraccion tractionEquipmentRequest = modelMapper.map(tractionEquipmentDto, EquipoDeTraccion.class);
		EquipoDeTraccion tractionEquipment = equipoDeTraccionService.addOrUpdateEquipoDeTraccion(tractionEquipmentRequest);
		TractionEquipmentDto tractionEquipmentResponse = modelMapper.map(tractionEquipment, TractionEquipmentDto.class);
    	return tractionEquipmentResponse;
	}
	
	@DeleteMapping("/traction-equipments/{id}")
	public void deleteEquipoDeTraccion(@PathVariable("id") Long id) throws Exception {
		equipoDeTraccionService.deleteEquipoDeTraccion(id);
	}

}
