package com.example.springBoot_hibernate_ferrocarriles.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springBoot_hibernate_ferrocarriles.model.Itinerario;
import com.example.springBoot_hibernate_ferrocarriles.repository.ItinerarioRepository;

@Service
public class ItinerarioService {
	@Autowired
	private ItinerarioRepository itinerarioRepository;

	
	public ItinerarioService() {
		super();
	}

	public ItinerarioService(ItinerarioRepository itinerarioRepository) {
		super();
		this.itinerarioRepository = itinerarioRepository;
	}
	
	public List<Itinerario> getAllItinerarios(){
		List<Itinerario> itinerarios=new ArrayList<Itinerario>();
		itinerarioRepository.findAll().forEach(itinerario1->itinerarios.add(itinerario1));
		return itinerarios;
	}
	
	public Itinerario getItinerarioById(Long id) {
		return itinerarioRepository.findById(id).get();
	}
	
	public void addOrUpdateItinerario(Itinerario itinerario) {
		itinerarioRepository.save(itinerario);		
	}
	
	public void deleteItinerario(Long id) {
		itinerarioRepository.deleteById(id);
	}
	
}
