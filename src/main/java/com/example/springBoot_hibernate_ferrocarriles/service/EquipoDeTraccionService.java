package com.example.springBoot_hibernate_ferrocarriles.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.springBoot_hibernate_ferrocarriles.model.EquipoDeTraccion;
import com.example.springBoot_hibernate_ferrocarriles.repository.EquipoDeTraccionRepository;

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
		List<EquipoDeTraccion> equiposDeTraccion = new ArrayList<EquipoDeTraccion>();
		equipoDeTraccionRepository.findAll().forEach(equipoDeTraccion1->equiposDeTraccion.add(equipoDeTraccion1));
		return equiposDeTraccion;		
	}
	
	public EquipoDeTraccion getEquipoDeTraccionById(Long idEquipoDeTraccion){
		return equipoDeTraccionRepository.findById(idEquipoDeTraccion).get();		
	}
	
	public void addOrUpdateEquipoDeTraccion(EquipoDeTraccion equipoDeTraccion){
		equipoDeTraccionRepository.save(equipoDeTraccion);
	}
	
	public void deleteEquipoDeTraccion(Long idEquipoDeTraccion){
		equipoDeTraccionRepository.deleteById(idEquipoDeTraccion);
	}

}
