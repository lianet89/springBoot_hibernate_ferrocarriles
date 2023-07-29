package com.example.springBoot_hibernate_ferrocarriles.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springBoot_hibernate_ferrocarriles.model.Locomotora;
import com.example.springBoot_hibernate_ferrocarriles.repository.LocomotoraRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LocomotoraService {
	@Autowired
	private LocomotoraRepository locomotoraRepository;
	
	public LocomotoraService() {
		super();
	}

	public LocomotoraService(LocomotoraRepository locomotoraRepository) {
		super();
		this.locomotoraRepository = locomotoraRepository;
	}
	
	public List<Locomotora> getAllLocomotora(){
		log.info("Listing all locomotives.");
		List<Locomotora> locomotoras = new ArrayList<Locomotora>();
		locomotoraRepository.findAll().forEach(locomotora1->locomotoras.add(locomotora1));
		return locomotoras;
	}
	
	public Locomotora getLocomotoraById(Long id){
		log.info("Obtainig a locomotive by ID:", id);
		return locomotoraRepository.findById(id).get();		
	}
	
	public Locomotora addOrUpdateLocomotora(Locomotora locomotora){
		log.info("Adding a locomotive:{}", locomotora);
		return locomotoraRepository.save(locomotora);
	}
	
	public void deleteLocomotora(Long id){
		log.info("Deleting a locomotive:{}", id);
		locomotoraRepository.deleteById(id);		
	}
	
}
