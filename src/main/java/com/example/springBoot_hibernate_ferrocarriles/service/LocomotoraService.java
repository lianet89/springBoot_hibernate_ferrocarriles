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
	
	public List<Locomotora> getAllLocomotora() throws Exception {
		log.info("Listing all locomotives.");
		List<Locomotora> locomotoras = new ArrayList<Locomotora>();
		try {
			locomotoraRepository.findAll().forEach(locomotora1->locomotoras.add(locomotora1));
		} catch(Exception ex) {
			System.out.println("An error has occurred: " + ex.getMessage());
		} 
		return locomotoras;
	}
	
	public Locomotora getLocomotoraById(Long id) throws Exception {
		log.info("Obtainig a locomotive by ID:", id);
		Locomotora locomotora = new Locomotora ();
		try {
			locomotora = locomotoraRepository.findById(id).get();	
		} catch(Exception ex) {
			System.out.println("An error has occurred: " + ex.getMessage());
		} return locomotora;
	}
	
	public Locomotora addOrUpdateLocomotora(Locomotora locomotora) throws Exception {
		log.info("Adding a locomotive:{}", locomotora);
		Locomotora locomotoraSalvada = new Locomotora ();
		try {
			locomotoraSalvada = locomotoraRepository.save(locomotora);
		} catch(Exception ex) {
			System.out.println("An error has occurred: " + ex.getMessage());
		} return locomotoraSalvada;
	}
	
	public void deleteLocomotora(Long id) throws Exception {
		log.info("Deleting a locomotive:{}", id);
		try {
			locomotoraRepository.deleteById(id);
		} catch(Exception ex) {
			System.out.println("An error has occurred: " + ex.getMessage());
		}
	}
}
