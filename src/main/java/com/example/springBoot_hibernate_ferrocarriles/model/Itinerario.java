package com.example.springBoot_hibernate_ferrocarriles.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

@Entity
public class Itinerario {
	@Id
	@GeneratedValue
	private int numeroIdentificacion;
    private int cantidadKilometros;
    private int cantidadVagones;
    private String provinciaOrigen;
    private String provinciaDestino;

    public Itinerario(int numeroIdentificacion, int cantidadKilometros, int cantidadVagones, String provinciaOrigen, String provinciaDestino) {
        this.numeroIdentificacion = numeroIdentificacion;
        this.cantidadKilometros = cantidadKilometros;
        this.cantidadVagones = cantidadVagones;
        this.provinciaOrigen = provinciaOrigen;
        this.provinciaDestino = provinciaDestino;
    }

    public int getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(int numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public int getCantidadKilometros() {
        return cantidadKilometros;
    }

    public void setCantidadKilometros(int cantidadKilometros) {
        this.cantidadKilometros = cantidadKilometros;
    }

    public int getCantidadVagones() {
        return cantidadVagones;
    }

    public void setCantidadVagones(int cantidadVagones) {
        this.cantidadVagones = cantidadVagones;
    }

    public String getProvinciaOrigen() {
        return provinciaOrigen;
    }

    public void setProvinciaOrigen(String provinciaOrigen) {
        this.provinciaOrigen = provinciaOrigen;
    }

    public String getProvinciaDestino() {
        return provinciaDestino;
    }

    public void setProvinciaDestino(String provinciaDestino) {
        this.provinciaDestino = provinciaDestino;
    }


}
