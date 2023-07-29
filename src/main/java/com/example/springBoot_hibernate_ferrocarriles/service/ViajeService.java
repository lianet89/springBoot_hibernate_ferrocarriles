package com.example.springBoot_hibernate_ferrocarriles.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springBoot_hibernate_ferrocarriles.model.EquipoDeTraccion;
import com.example.springBoot_hibernate_ferrocarriles.model.Itinerario;
import com.example.springBoot_hibernate_ferrocarriles.model.Viaje;
import com.example.springBoot_hibernate_ferrocarriles.repository.ViajeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ViajeService {
	@Autowired
	private ViajeRepository viajeRepository;
	@Autowired
	private EquipoDeTraccionService equipoDeTraccionService;
	
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
	
	public Viaje addOrUpdateViaje(Viaje viaje){	
		return viajeRepository.save(viaje);
	}
	
	public void deleteViaje(Long idViaje){
		Viaje viaje = viajeRepository.findById(idViaje).get();
		EquipoDeTraccion equipo = viaje.getEquipoDeTraccion();
		int kilometrajeActual = equipo.getKilometrajeRecorrido() + viaje.getItinerario().getCantidadKilometros();
		viajeRepository.deleteById(idViaje);
		equipo.setKilometrajeRecorrido(kilometrajeActual);
		equipo.setLineaDeTrenes(1);
		equipoDeTraccionService.addOrUpdateEquipoDeTraccion(equipo);
	}
	
	public Viaje addViaje(Itinerario itinerario){		
		int numeroViaje = viajeRepository.findAll().size()+1;
		boolean equipoDisponible =false;
		List<EquipoDeTraccion> linea = equipoDeTraccionService.getAllEquipoDeTraccion();
		Viaje nuevoViaje = new Viaje();
        
        Comparator<EquipoDeTraccion> porKilometraje = Comparator.comparing(EquipoDeTraccion::getKilometrajeRecorrido);
        Collections.sort(linea, porKilometraje);
        
        for (EquipoDeTraccion equipo:linea) {
            if (equipo.cantidadMaxCoches()>=itinerario.getCantidadVagones() && equipo.getLineaDeTrenes()==1){
                equipoDisponible = true;
                nuevoViaje = new Viaje(numeroViaje, itinerario, equipo);
                viajeRepository.save(nuevoViaje);
                equipo.setLineaDeTrenes(2);
                equipoDeTraccionService.addOrUpdateEquipoDeTraccion(equipo);
                break;
            }
        }
        if (equipoDisponible ==false){
            System.out.println("No existen equipos disponibles para el itinerario nÃºmero "+ itinerario.getNumeroIdentificacion()+".");
        }		
		return nuevoViaje;
	}
	
}
