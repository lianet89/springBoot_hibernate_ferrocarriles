package com.example.springBoot_hibernate_ferrocarriles.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springBoot_hibernate_ferrocarriles.model.EquipoDeTraccion;
import com.example.springBoot_hibernate_ferrocarriles.model.Itinerario;
import com.example.springBoot_hibernate_ferrocarriles.model.Locomotora;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReportesService {
	@Autowired
	EquipoDeTraccionService equipoDeTraccionService;
	@Autowired
	ItinerarioService itinerarioService;
	@Autowired 
	LocomotoraService locomotoraService;

	public ReportesService() {
		super();
	}
	
	public List<EquipoDeTraccion> equiposAptosParaItinerario(int id){
		log.info("Listing the traction equipments that can cover the itinerary with ID:{}", id);
		List<EquipoDeTraccion> equiposAptosParaItinerario = new ArrayList<>();
		for(EquipoDeTraccion equipo:equipoDeTraccionService.getAllEquipoDeTraccion()) {
			if(equipo.cantidadMaxCoches()>=itinerarioService.getItinerarioById((long) id).getCantidadVagones() && equipo.getLineaDeTrenes()==1) {
				equiposAptosParaItinerario.add(equipo);
			}
		}
		return equiposAptosParaItinerario;
	}
	
	public List<Locomotora> locomotorasDeMarca(String marca){
		log.info("Listing the locomotives that are of the brand:{}", marca);
		List<Locomotora> locomotorasDeMarca = new ArrayList<>();
		for(Locomotora locomotora:locomotoraService.getAllLocomotora()) {
			if(locomotora.getMarcaFabricante().equals(marca)) {
				locomotorasDeMarca.add(locomotora);
			}			
		}
		Comparator<Locomotora> porKilometraje = Comparator.comparing(Locomotora::getKilometrajeRecorrido);
        Collections.sort(locomotorasDeMarca, porKilometraje);
        Collections.reverse(locomotorasDeMarca);	
        return locomotorasDeMarca;
	}
	
	public void findDestinations(List<Itinerario> listaItinerarios, List<String> listaAuxiliar, int cantidadTemporal, int i) {
		log.info("Listing the itineraries that have the same destination of the itinerary:{}", i);
		for(int j = listaItinerarios.size()-1; j>i; j--) {
			if(listaItinerarios.get(i).getProvinciaOrigen().equals(listaItinerarios.get(j).getProvinciaOrigen()) &&
					listaItinerarios.get(i).getProvinciaDestino().equals(listaItinerarios.get(j).getProvinciaDestino())) {
				listaItinerarios.remove(j);
			}else if(listaItinerarios.get(i).getProvinciaOrigen().equals(listaItinerarios.get(j).getProvinciaOrigen()) &&
					   !listaItinerarios.get(i).getProvinciaDestino().equals(listaItinerarios.get(j).getProvinciaDestino()) &&
					   !listaAuxiliar.contains(listaItinerarios.get(j).getProvinciaDestino())){				
				listaAuxiliar.add(listaItinerarios.get(j).getProvinciaDestino());
				cantidadTemporal++;
				listaItinerarios.remove(j);
			}
		}
	}
	
	public String originMoreDestinations() {
		log.info("Obtaining the origin province that have more destinations.");
		List<Itinerario> listaItinerarios = itinerarioService.getAllItinerarios();
		List<String> listaAuxiliar = new ArrayList<>();
		int cantidadTemporal = 0;
		int cantidadFinal = 0;		
		String origenTemporal = "";
		String origenGanador = "";
				
		if(listaItinerarios.isEmpty()) {
			return "No existen itinerarios.";
		}else {			
			for(int i = 0; i<listaItinerarios.size(); i++) {
				origenTemporal = listaItinerarios.get(i).getProvinciaOrigen();
				cantidadTemporal = 1;
				findDestinations(listaItinerarios, listaAuxiliar, cantidadTemporal, i);						
				if(cantidadTemporal>cantidadFinal) {
					origenGanador = origenTemporal;
					cantidadFinal = cantidadTemporal;
				}
			}
			return origenGanador;
		}
	}

}
