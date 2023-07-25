package com.example.springBoot_hibernate_ferrocarriles.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@Table(name="Locomotora")
@PrimaryKeyJoinColumn(name="numeroIdentificacion")
public class Locomotora extends EquipoDeTraccion{
	private  String marcaFabricante;
    
	public Locomotora() {
		super();
	}

	public Locomotora(int numeroIdentificacion, int kilometrajeRecorrido, int potenciaMotor, int lineaDeTrenes, String marcaFabricante) {
        super(numeroIdentificacion, kilometrajeRecorrido, potenciaMotor, lineaDeTrenes);
        this.marcaFabricante = marcaFabricante;
    }

    public String getMarcaFabricante() {
        return marcaFabricante;
    }

    public void setMarcaFabricante(String marcaFabricante) {
        this.marcaFabricante = marcaFabricante;
    }

    @Override
    public int cantidadMaxCoches() {
        int i=0;
        if (marcaFabricante.equalsIgnoreCase("SD50")){
            i=10;
        } else if (marcaFabricante.equalsIgnoreCase("DF76")) {
            i= 15;
        } else if (marcaFabricante.equalsIgnoreCase("MLW")) {
            i=20;
        }
        return i;
    }


}
