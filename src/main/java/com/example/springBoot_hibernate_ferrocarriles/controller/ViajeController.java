package com.example.springBoot_hibernate_ferrocarriles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBoot_hibernate_ferrocarriles.service.ViajeService;
import com.example.springBoot_hibernate_ferrocarriles.service.EquipoDeTraccionService;
import com.example.springBoot_hibernate_ferrocarriles.model.EquipoDeTraccion;
import com.example.springBoot_hibernate_ferrocarriles.model.Itinerario;
import com.example.springBoot_hibernate_ferrocarriles.model.Viaje;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class ViajeController {
	@Autowired
	ViajeService viajeService;
	@Autowired
	EquipoDeTraccionService equipoDeTraccionService;

	public ViajeController() {
		super();
	}
	
	@GetMapping("/viaje")
	public List<Viaje> getAllViajes(){
		return viajeService.getAllViajes();
	}
	
	@GetMapping("/viaje/{idViaje}")
	public Viaje getViajeById(@PathVariable("idViaje")Long idViaje){
		return viajeService.getViajeById(idViaje);
	}
	
	@PostMapping("/viaje")
	public Viaje addViaje(@RequestBody Itinerario itinerario){		
		int numeroViaje = viajeService.getAllViajes().size()+1;
		boolean equipoDisponible =false;
		List<EquipoDeTraccion> linea = equipoDeTraccionService.getAllEquipoDeTraccion();
		Viaje nuevoViaje = new Viaje();
        
        Comparator<EquipoDeTraccion> porKilometraje = Comparator.comparing(EquipoDeTraccion::getKilometrajeRecorrido);
        Collections.sort(linea, porKilometraje);
        
        for (EquipoDeTraccion equipo:linea) {
            if (equipo.cantidadMaxCoches()>=itinerario.getCantidadVagones() && equipo.getLineaDeTrenes()==1){
                equipoDisponible = true;
                nuevoViaje = new Viaje(numeroViaje, itinerario, equipo);
                viajeService.addOrUpdateViaje(nuevoViaje);
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
	
	@PutMapping("/viaje")
	public int updateViaje(@RequestBody Viaje viaje){		
		viajeService.addOrUpdateViaje(viaje);		
		return viaje.getNumeroViaje();		
	}
	
	@DeleteMapping("/viaje/{idViaje}")
	public void deleteViaje(@PathVariable("idViaje") Long idViaje){
		Viaje viaje = viajeService.getViajeById(idViaje);
		EquipoDeTraccion equipo = viaje.getEquipoDeTraccion();
		int kilometrajeActual = equipo.getKilometrajeRecorrido() + viaje.getItinerario().getCantidadKilometros();
		viajeService.deleteViaje(idViaje);
		equipo.setKilometrajeRecorrido(kilometrajeActual);
		equipo.setLineaDeTrenes(1);
		equipoDeTraccionService.addOrUpdateEquipoDeTraccion(equipo);
	}

}
