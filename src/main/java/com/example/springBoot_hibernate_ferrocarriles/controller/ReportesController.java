package com.example.springBoot_hibernate_ferrocarriles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.example.springBoot_hibernate_ferrocarriles.model.EquipoDeTraccion;
import com.example.springBoot_hibernate_ferrocarriles.model.Locomotora;
import com.example.springBoot_hibernate_ferrocarriles.service.ReportesService;

@RestController
public class ReportesController {
	@Autowired
	private ReportesService reportesService;
	
	@GetMapping("/reports/siutable-traction-equipment/{id}")
	public List<EquipoDeTraccion> siutableTractionEquipment(@PathVariable("id")int id){
		return reportesService.equiposAptosParaItinerario(id);
	}
	
	@GetMapping("/reports/locomotives-of-a-brand/{marca}")
	public List<Locomotora> locomotivesOfABrand(@PathVariable("marca")String marca){
		return reportesService.locomotorasDeMarca(marca);
	}
	
	@GetMapping("/reports/origen-with-more-destinations")
	public String originWithMoreDestinations() {
		return reportesService.originMoreDestinations();
	}
	
}


