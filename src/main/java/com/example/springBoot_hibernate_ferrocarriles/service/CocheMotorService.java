package com.example.springBoot_hibernate_ferrocarriles.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springBoot_hibernate_ferrocarriles.model.CocheMotor;
import com.example.springBoot_hibernate_ferrocarriles.repository.CocheMotorRepository;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		log.info("Listing all motor-car.");
		List<CocheMotor> cochesMotor=new ArrayList<CocheMotor>();
		cocheMotorRepository.findAll().forEach(cocheMotor1->cochesMotor.add(cocheMotor1));
		return cochesMotor;
	}
	
	public CocheMotor getCocheMotorById(Long id) {
		log.info("Obtainig a motor-car by ID:{}", id);
		return cocheMotorRepository.findById(id).get();
	}
	
	public CocheMotor addOrUpdateCocheMotor(CocheMotor cocheMotor) {
		log.info("Adding a motor-car:{}", cocheMotor);
		return cocheMotorRepository.save(cocheMotor);		
	}
	
	public void deleteCocheMotor(Long id) {
		log.info("Deleting a motor-car by ID:{}", id);
		cocheMotorRepository.deleteById(id);
	}
	

}
