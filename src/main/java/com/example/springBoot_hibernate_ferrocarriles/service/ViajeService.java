package com.example.springBoot_hibernate_ferrocarriles.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springBoot_hibernate_ferrocarriles.model.Viaje;
import com.example.springBoot_hibernate_ferrocarriles.repository.ViajeRepository;

@Service
public class ViajeService {
	@Autowired
	private ViajeRepository viajeRepository;
	
	public ViajeService() {
		super();
	}

	public ViajeService(ViajeRepository viajeRepository) {
		super();
		this.viajeRepository = viajeRepository;
	}
	
	public List<Viaje> getAllViajes(){
		List<Viaje> viajes = new ArrayList<Viaje>();		
		viajeRepository.findAll().forEach(viaje1->viajes.add(viaje1));
		return viajes;		
	}
	
	public Viaje getViajeById(Long idViaje){
		return viajeRepository.findById(idViaje).get();		
	}
	
	public void addOrUpdateViaje(Viaje viaje){	
		viajeRepository.save(viaje);
	}
	
	public void deleteViaje(Long idViaje){
		viajeRepository.deleteById(idViaje);
	}
	
}
