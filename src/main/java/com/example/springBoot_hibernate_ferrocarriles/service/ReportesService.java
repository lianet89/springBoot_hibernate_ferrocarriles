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
	
	public List<EquipoDeTraccion> equiposAptosParaItinerario(int id) throws Exception{
		log.info("Listing the traction equipments that can cover the itinerary with ID:{}", id);
		List<EquipoDeTraccion> equiposAptosParaItinerario = new ArrayList<>();
		
		try {
			for(EquipoDeTraccion equipo:equipoDeTraccionService.getAllEquipoDeTraccion()) {
				if(equipo.cantidadMaxCoches()>=itinerarioService.getItinerarioById((long) id).getCantidadVagones() && equipo.getLineaDeTrenes()==1) {
					equiposAptosParaItinerario.add(equipo);
				}
			}
		} catch(Exception ex) {
			System.out.println("An error has occurred: " + ex.getMessage());
		} 
		return equiposAptosParaItinerario;
	}
	
	public List<Locomotora> locomotorasDeMarca(String marca) throws Exception {
		log.info("Listing the locomotives that are of the brand:{}", marca);
		List<Locomotora> locomotorasDeMarca = new ArrayList<>();
		
		try {
			for(Locomotora locomotora:locomotoraService.getAllLocomotora()) {
				if(locomotora.getMarcaFabricante().equals(marca)) {
					locomotorasDeMarca.add(locomotora);
				}			
			}
		} catch(Exception ex) {
			System.out.println("An error has occurred: " + ex.getMessage());
		} 	
		Comparator<Locomotora> porKilometraje = Comparator.comparing(Locomotora::getKilometrajeRecorrido);
        Collections.sort(locomotorasDeMarca, porKilometraje);
        Collections.reverse(locomotorasDeMarca);	
        return locomotorasDeMarca;
	}
	
	public void findDestinations(List<Itinerario> listaItinerarios, List<String> listaAuxiliar, int cantidadTemporal, int i) throws Exception {
		log.info("Listing the itineraries that have the same destination of the itinerary:{}", i);
		
		try {
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
		} catch(Exception ex) {
			System.out.println("An error has occurred: " + ex.getMessage());
		}
	}
	
	public String originMoreDestinations() throws Exception {
		log.info("Obtaining the origin province that have more destinations.");
		
		List<String> listaAuxiliar = new ArrayList<>();
		int cantidadTemporal = 0;
		int cantidadFinal = 0;		
		String origenTemporal = "";
		String origenGanador = "";
		
		try {
			List<Itinerario> listaItinerarios = itinerarioService.getAllItinerarios();
			
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
			}
		} catch(Exception ex) {
			System.out.println("An error has occurred: " + ex.getMessage());
		}
		return origenGanador;
	}
	
	/*Obtengo el itinerario a eliminar dado su id (itineraryToDelete) y la lista de itinerarios (itineraryList).
	 * Si la lista esta vacia muestro un mensaje "No existen itinerarios". Sino, recorro itineraryList de inicio a fin (i) para comparar 
	 * por la ProvinciaOrigen con el itinerario a eliminar.
	 * Si tienen diferentes ID y el mismo origen, guardo en la variable "temporalItinerary" el itinerario de la posicion i.
	 * Si el itinerario de la posicion i y el itinerario a eliminar tienen el mismo origen y mismo destino, guardo en la variable combination 
	 * el origen y destino de ese itinerario y finalizo el ciclo.
	 * Sino, recorro itineraryList del final al inicio (j) para comparar con el itinerario de la posicion i y con el "itineraryToDelete".
	 * Si el itinerario de la posicion j e itineraryToDelete tienen el mismo origen y mismo destino, guardo en la variable combination 
	 * el origen y destino de ese itinerario y finalizo el ciclo.
	 * Sino, si el destino del itinerario de la posicion i coincide con el origen del itinerario de la posicion j, y el destino
	 * del itinerario en la posicion j coincide con el destino del itinerario a eliminar, guardo en la variable combination 
	 * el origen y destino de los itinerarios de las posiciones i y j y finalizo el ciclo.
	 * Retorno la variable combination.
	 * */
	//el siguiente metodo esta PENDIENTE DE SER DIVIDIDO en metodos mas pequennos...	
	public String coverItinerary(int id) throws Exception {
		log.info("Obtaining the combination of itineraries that can cover the itinerary:{}", id);
		
		Itinerario temporalItinerary = new Itinerario();
		String combination = "";
		
		try {
			Itinerario itineraryToDelete = itinerarioService.getItinerarioById((long) id);
			List<Itinerario> itineraryList = itinerarioService.getAllItinerarios();
						
			if(itineraryList.isEmpty()) {
				return "No existen itinerarios.";
			} else {
				for(int i = 0; i<itineraryList.size(); i++) {
					temporalItinerary = itineraryList.get(i);
					if(temporalItinerary.getNumeroIdentificacion() != id &&
							itineraryToDelete.getProvinciaOrigen().equals(temporalItinerary.getProvinciaOrigen()) &&
							itineraryToDelete.getProvinciaDestino().equals(temporalItinerary.getProvinciaDestino())){
						combination = temporalItinerary.getProvinciaOrigen() + " - " + temporalItinerary.getProvinciaDestino() + 
								" ( " + temporalItinerary.getNumeroIdentificacion() + " ) ";
						break;
					}else if(temporalItinerary.getNumeroIdentificacion() != id &&
								itineraryToDelete.getProvinciaOrigen().equals(temporalItinerary.getProvinciaOrigen())) {
						for(int j = itineraryList.size()-1; j>=0; j--) {
							if(itineraryList.get(j).getNumeroIdentificacion() != id && 
										itineraryList.get(j).getProvinciaOrigen().equals(itineraryToDelete.getProvinciaOrigen()) &&
										itineraryList.get(j).getProvinciaDestino().equals(itineraryToDelete.getProvinciaDestino())) {
									combination = itineraryList.get(j).getProvinciaOrigen() + " - " + itineraryList.get(j).getProvinciaDestino() + 
											" ( " + itineraryList.get(j).getNumeroIdentificacion() + " ) ";
									break;								
								} else if(itineraryList.get(j).getNumeroIdentificacion() != id && 
										temporalItinerary.getProvinciaDestino().equals(itineraryList.get(j).getProvinciaOrigen()) &&
										itineraryList.get(j).getProvinciaDestino().equals(itineraryToDelete.getProvinciaDestino())){
									combination = temporalItinerary.getProvinciaOrigen() + " - " + temporalItinerary.getProvinciaDestino() + 
											" , " + itineraryList.get(j).getProvinciaOrigen() + " - " + itineraryList.get(j).getProvinciaDestino() + 
											" ( " + temporalItinerary.getNumeroIdentificacion() + " - " + itineraryList.get(j).getNumeroIdentificacion() + 
											" ) ";
									break;								
								}
							}
					}
					if(!combination.isBlank()) {
						break;		
					}
				}
				if(combination.isBlank()) {
					return "No";
				}else
					return combination;	
			}
		} catch(Exception ex) {
			System.out.println("An error has occurred: " + ex.getMessage());
			return "";
		}
		
	}

}
