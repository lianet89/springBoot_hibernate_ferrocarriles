package com.example.springBoot_hibernate_ferrocarriles.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import com.example.springBoot_hibernate_ferrocarriles.dto.LocomotiveDto;
import com.example.springBoot_hibernate_ferrocarriles.dto.TractionEquipmentDto;
import com.example.springBoot_hibernate_ferrocarriles.service.ReportesService;

@RestController
public class ReportesController {
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private ReportesService reportesService;
	
	@GetMapping("/reports/siutable-traction-equipment/{id}")
	public ResponseEntity <List<TractionEquipmentDto>> siutableTractionEquipment(@PathVariable("id") int id) throws Exception {
		List<TractionEquipmentDto> listResponse = reportesService.equiposAptosParaItinerario(id).stream().map(tractionEquipment -> modelMapper.map(tractionEquipment, TractionEquipmentDto.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listResponse);
	}
	
	@GetMapping("/reports/locomotives-of-a-brand/{marca}")
	public ResponseEntity<List<LocomotiveDto>> locomotivesOfABrand(@PathVariable("marca")String marca) throws Exception{
		List<LocomotiveDto> listResponse = reportesService.locomotorasDeMarca(marca).stream().map(locomotive -> modelMapper.map(locomotive, LocomotiveDto.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listResponse);
	}
	
	@GetMapping("/reports/origin-with-more-destinations")
	public String originWithMoreDestinations() throws Exception {
		return reportesService.originMoreDestinations();
	}
	
	@GetMapping("/reports/cover-itinerary/{id}")
	public ResponseEntity<String> coverItinerary(@PathVariable("id") int id) throws Exception{
		String stringResponse = reportesService.coverItinerary(id);
		return ResponseEntity.ok().body(stringResponse);
	}
	
}


