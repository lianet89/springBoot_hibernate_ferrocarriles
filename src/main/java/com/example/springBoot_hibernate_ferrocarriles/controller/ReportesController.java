package com.example.springBoot_hibernate_ferrocarriles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.springBoot_hibernate_ferrocarriles.model.EquipoDeTraccion;
import com.example.springBoot_hibernate_ferrocarriles.model.Itinerario;
import com.example.springBoot_hibernate_ferrocarriles.model.Locomotora;
import com.example.springBoot_hibernate_ferrocarriles.service.EquipoDeTraccionService;
import com.example.springBoot_hibernate_ferrocarriles.service.ItinerarioService;
import com.example.springBoot_hibernate_ferrocarriles.service.LocomotoraService;

@RestController
public class ReportesController {
	@Autowired
	EquipoDeTraccionService equipoDeTraccionService;
	@Autowired
	ItinerarioService itinerarioService;
	@Autowired 
	LocomotoraService locomotoraService;
	List<String> listaAuxiliar = new ArrayList<>();

	public ReportesController() {
		super();
	}
	
	@GetMapping("/reportes/equiposAptos/{idItinerario}")
	public List<EquipoDeTraccion> equiposAptosParaItinerario(@PathVariable("idItinerario")int idItinerario){
		List<EquipoDeTraccion> equiposAptosParaItinerario = new ArrayList<>();
		for(EquipoDeTraccion equipo:equipoDeTraccionService.getAllEquipoDeTraccion()) {
			if(equipo.cantidadMaxCoches()>=itinerarioService.getItinerarioById((long) idItinerario).getCantidadVagones() && equipo.getLineaDeTrenes()==1) {
				equiposAptosParaItinerario.add(equipo);
			}
		}		
		return equiposAptosParaItinerario;
	}
	
	@GetMapping("/reportes/locomotorasDeMarca/{marca}")
	public List<Locomotora> locomotorasDeMarca(@PathVariable("marca")String marca){
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
	
	@GetMapping("/reportes/origenMasSalidas")
	public String origenMasSalidas() {
		List<Itinerario> listaItinerarios = itinerarioService.getAllItinerarios();
		String origenGanador = "";
		int cantidadFinal = 0;
		String origenTemporal = "";
		int cantidadTemporal = 0;
		if(listaItinerarios.isEmpty()) {
			return "No existen itinerarios.";
		}else {				
			for(int i = 0; i<listaItinerarios.size(); i++) {				
				origenTemporal = listaItinerarios.get(i).getProvinciaOrigen();
				cantidadTemporal = 1;
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
				if(cantidadTemporal>cantidadFinal) {
					origenGanador = origenTemporal;
					cantidadFinal = cantidadTemporal;
				}
			}
			listaAuxiliar.clear();
			return origenGanador;
		}
		
	}
}
