package com.example.springBoot_hibernate_ferrocarriles.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springBoot_hibernate_ferrocarriles.model.Itinerario;
import com.example.springBoot_hibernate_ferrocarriles.repository.ItinerarioRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		log.info("Listing all itineraries.");
		List<Itinerario> itinerarios=new ArrayList<Itinerario>();
		itinerarioRepository.findAll().forEach(itinerario1->itinerarios.add(itinerario1));
		return itinerarios;
	}
	
	public Itinerario getItinerarioById(Long id) {
		log.info("Obtaining an itinerary by ID:{}", id);
		return itinerarioRepository.findById(id).get();
	}
	
	public Itinerario addOrUpdateItinerario(Itinerario itinerario) {
		log.info("Adding an itinerary:{}", itinerario);
		return itinerarioRepository.save(itinerario);		
	}
	
	public void deleteItinerario(Long id) {
		log.info("Deleting an itinerary by ID:{}", id);
		itinerarioRepository.deleteById(id);
	}
	
}
