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

import com.example.springBoot_hibernate_ferrocarriles.dto.LocomotiveDto;
import com.example.springBoot_hibernate_ferrocarriles.model.Locomotora;
import com.example.springBoot_hibernate_ferrocarriles.service.LocomotoraService;

import jakarta.validation.Valid;

@RestController
public class LocomotoraController {
	@Autowired
	LocomotoraService locomotoraService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("/locomotives")
	public List<LocomotiveDto> getAllLocomotoras() throws Exception {
		return locomotoraService.getAllLocomotora().stream().map(locomotive -> modelMapper.map(locomotive, LocomotiveDto.class)).collect(Collectors.toList());
	}
	
	@GetMapping("/locomotives/{id}")
	public LocomotiveDto getLocomotoraById(@PathVariable("id") Long id) throws Exception {
		Locomotora locomotive = locomotoraService.getLocomotoraById(id);
		LocomotiveDto locomotiveResponse = modelMapper.map(locomotive, LocomotiveDto.class); 
		return locomotiveResponse;
	}
	
	@PostMapping("/locomotives")
	public LocomotiveDto addLocomotora(@Valid @RequestBody LocomotiveDto locomotiveDto) throws Exception {
		Locomotora locomotiveRequest = modelMapper.map(locomotiveDto, Locomotora.class);
		Locomotora locomotive = locomotoraService.addOrUpdateLocomotora(locomotiveRequest);
		LocomotiveDto locomotiveResponse = modelMapper.map(locomotive, LocomotiveDto.class);
    	return locomotiveResponse;
	}
	
	@PutMapping("/locomotives")
	public LocomotiveDto updateLocomotora(@RequestBody LocomotiveDto locomotiveDto) throws Exception {
		Locomotora locomotiveRequest = modelMapper.map(locomotiveDto, Locomotora.class);
		Locomotora locomotive = locomotoraService.addOrUpdateLocomotora(locomotiveRequest);
		LocomotiveDto locomotiveResponse = modelMapper.map(locomotive, LocomotiveDto.class);
    	return locomotiveResponse;
	}
	
	@DeleteMapping("/locomotives/{id}")
	public void deleteLocomotora(@PathVariable("id") Long id) throws Exception {
		locomotoraService.deleteLocomotora(id);
	}

}
