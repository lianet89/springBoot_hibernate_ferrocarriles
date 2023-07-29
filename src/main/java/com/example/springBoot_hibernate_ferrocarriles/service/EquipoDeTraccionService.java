package com.example.springBoot_hibernate_ferrocarriles.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.springBoot_hibernate_ferrocarriles.model.EquipoDeTraccion;
import com.example.springBoot_hibernate_ferrocarriles.repository.EquipoDeTraccionRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EquipoDeTraccionService {
	@Autowired
	private  EquipoDeTraccionRepository equipoDeTraccionRepository;
	
	public EquipoDeTraccionService() {
		super();
	}

	public EquipoDeTraccionService(EquipoDeTraccionRepository equipoDeTraccionRepository) {
		super();
		this.equipoDeTraccionRepository = equipoDeTraccionRepository;
	}
	
	public List<EquipoDeTraccion> getAllEquipoDeTraccion(){
		log.info("Listing all traction equipment.");
		List<EquipoDeTraccion> equiposDeTraccion = new ArrayList<EquipoDeTraccion>();
		equipoDeTraccionRepository.findAll().forEach(equipoDeTraccion1->equiposDeTraccion.add(equipoDeTraccion1));
		return equiposDeTraccion;		
	}
	
	public EquipoDeTraccion getEquipoDeTraccionById(Long id){
		log.info("Obtainig a traction equipment by ID:{}", id);
		return equipoDeTraccionRepository.findById(id).get();		
	}
	
	public EquipoDeTraccion addOrUpdateEquipoDeTraccion(EquipoDeTraccion equipoDeTraccion){
		log.info("Adding a traction equipment:{}", equipoDeTraccion);
		return equipoDeTraccionRepository.save(equipoDeTraccion);
	}
	
	public void deleteEquipoDeTraccion(Long id){
		log.info("Deleting a traction equipment:{}", id);
		equipoDeTraccionRepository.deleteById(id);
	}

}
