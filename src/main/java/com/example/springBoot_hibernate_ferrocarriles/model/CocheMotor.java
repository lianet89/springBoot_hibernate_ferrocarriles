package com.example.springBoot_hibernate_ferrocarriles.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@Table(name="CocheMotor")
@PrimaryKeyJoinColumn(name="numeroIdentificacion")
public class CocheMotor extends EquipoDeTraccion{
	private boolean climatizado;
    
	public CocheMotor() {
		super();
	}

	public CocheMotor(int numeroIdentificacion, int kilometrajeRecorrido, int potenciaMotor, int lineaDeTrenes, boolean climatizado) {
        super(numeroIdentificacion, kilometrajeRecorrido, potenciaMotor, lineaDeTrenes);
        this.climatizado = climatizado;
    }

    public boolean isClimatizado() {
        return climatizado;
    }

    public void setClimatizado(boolean climatizado) {
        this.climatizado = climatizado;
    }

    @Override
    public int cantidadMaxCoches() {
        int i=0;
        if (potenciaMotor<4000){
            i=1;
        } else if (potenciaMotor>=4000) {
            i=2;
        }
        return i;
    }


}
