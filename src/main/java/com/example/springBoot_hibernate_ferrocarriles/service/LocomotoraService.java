package com.example.springBoot_hibernate_ferrocarriles.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springBoot_hibernate_ferrocarriles.model.Locomotora;
import com.example.springBoot_hibernate_ferrocarriles.repository.LocomotoraRepository;

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
		List<Locomotora> locomotoras = new ArrayList<Locomotora>();
		locomotoraRepository.findAll().forEach(locomotora1->locomotoras.add(locomotora1));
		return locomotoras;
	}
	
	public Locomotora getLocomotoraById(Long idLocomotora){
		return locomotoraRepository.findById(idLocomotora).get();		
	}
	
	public void addOrUpdateLocomotora(Locomotora locomotora){
		locomotoraRepository.save(locomotora);		
	}
	
	public void deleteLocomotora(Long idLocomotora){
		locomotoraRepository.deleteById(idLocomotora);		
	}
	
}
