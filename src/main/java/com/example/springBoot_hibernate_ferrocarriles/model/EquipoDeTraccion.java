package com.example.springBoot_hibernate_ferrocarriles.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Table(name="EquipoDeTraccion")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class EquipoDeTraccion {
	@Id
	protected int numeroIdentificacion;
    protected int kilometrajeRecorrido;
    protected int potenciaMotor;
    protected int lineaDeTrenes;

    public EquipoDeTraccion() {
    	super();
    }

    public EquipoDeTraccion(int numeroIdentificacion, int kilometrajeRecorrido, int potenciaMotor, int lineaDeTrenes) {
        this.numeroIdentificacion = numeroIdentificacion;
        this.kilometrajeRecorrido = kilometrajeRecorrido;
        this.potenciaMotor = potenciaMotor;
        this.lineaDeTrenes = lineaDeTrenes;
    }

    public int getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(int numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public int getKilometrajeRecorrido() {
        return kilometrajeRecorrido;
    }

    public void setKilometrajeRecorrido(int kilometrajeRecorrido) {
        this.kilometrajeRecorrido = kilometrajeRecorrido;
    }

    public int getPotenciaMotor() {
        return potenciaMotor;
    }

    public void setPotenciaMotor(int potenciaMotor) {
        this.potenciaMotor = potenciaMotor;
    }

    public int getLineaDeTrenes() {
		return lineaDeTrenes;
	}

	public void setLineaDeTrenes(int lineaDeTrenes) {
		this.lineaDeTrenes = lineaDeTrenes;
	}

	public abstract int cantidadMaxCoches();


}
