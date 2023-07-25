package com.example.springBoot_hibernate_ferrocarriles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springBoot_hibernate_ferrocarriles.model.CocheMotor;
import com.example.springBoot_hibernate_ferrocarriles.model.Itinerario;
import com.example.springBoot_hibernate_ferrocarriles.repository.CocheMotorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CocheMotorService {
	@Autowired
	private CocheMotorRepository cocheMotorRepository;
		
	public CocheMotorService() {
		super();
	}

	public CocheMotorService(CocheMotorRepository cocheMotorRepositoy) {
		super();
		this.cocheMotorRepository = cocheMotorRepositoy;
	}
	
	public List<CocheMotor> getAllCocheMotor(){
		List<CocheMotor> cochesMotor=new ArrayList<CocheMotor>();
		cocheMotorRepository.findAll().forEach(cocheMotor1->cochesMotor.add(cocheMotor1));
		return cochesMotor;
	}
	
	public CocheMotor getCocheMotorById(Long idCocheMotor) {
		return cocheMotorRepository.findById(idCocheMotor).get();
	}
	
	public void addOrUpdateCocheMotor(CocheMotor cocheMotor) {
		cocheMotorRepository.save(cocheMotor);		
	}
	
	public void deleteCocheMotor(Long id) {
		cocheMotorRepository.deleteById(id);
	}
	

}
