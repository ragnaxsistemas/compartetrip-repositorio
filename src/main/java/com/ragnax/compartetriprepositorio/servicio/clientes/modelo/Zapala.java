package com.ragnax.compartetriprepositorio.servicio.clientes.modelo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Zapala implements Serializable{

	private static final long serialVersionUID = 5379307160546001781L;

	
	private Timestamp tiempoStrtoTimeStamp;
	private String codigoGenerado;
	
	public Zapala() {
		super();
	}
	
	public Zapala(String codigoGenerado) {
		super();
		this.codigoGenerado = codigoGenerado;
	}
	
	public Zapala(Timestamp tiempoStrtoTimeStamp) {
		super();
		this.tiempoStrtoTimeStamp = tiempoStrtoTimeStamp;
	}

	public Timestamp getTiempoStrtoTimeStamp() {
		return tiempoStrtoTimeStamp;
	}

	public void setTiempoStrtoTimeStamp(Timestamp tiempoStrtoTimeStamp) {
		this.tiempoStrtoTimeStamp = tiempoStrtoTimeStamp;
	}

	public String getCodigoGenerado() {
		return codigoGenerado;
	}

	public void setCodigoGenerado(String codigoGenerado) {
		this.codigoGenerado = codigoGenerado;
	}
	
	
	
}
