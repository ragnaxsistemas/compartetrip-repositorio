package com.ragnax.compartetriprepositorio.entidad;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: PasajeroArrastrameViaje
 * en la base de Datos representa 
 */
@Entity
@Table (name="pasajero_arrastrame_viaje")

public class PasajeroArrastrameViaje implements Serializable{
 
	private static final long serialVersionUID = -6192592759521885745L;

	@Id
	@OrderBy
	@Column(name="id_pasajero_arrastrame_viaje")
	private Integer idPasajeroArrastrameViaje;
	
	@ManyToOne
	@JoinColumn(name="fk_id_viaje")
	private Viaje idViaje;
	
	@ManyToOne
	@JoinColumn(name="fk_id_pasajero_arrastrame")
	private PasajeroArrastrame idPasajeroArrastrame;
	
	public PasajeroArrastrameViaje() {
		super();
	}
	
	public PasajeroArrastrameViaje(Viaje idViaje) {
		super();
		this.idViaje = idViaje;
	}
	
	public PasajeroArrastrameViaje(Viaje idViaje,
			PasajeroArrastrame idPasajeroArrastrame) {
		super();
		this.idViaje = idViaje;
		this.idPasajeroArrastrame = idPasajeroArrastrame;
	}

	public Integer getIdPasajeroArrastrameViaje() {
		return idPasajeroArrastrameViaje;
	}

	public void setIdPasajeroArrastrameViaje(Integer idPasajeroArrastrameViaje) {
		this.idPasajeroArrastrameViaje = idPasajeroArrastrameViaje;
	}

	public Viaje getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(Viaje idViaje) {
		this.idViaje = idViaje;
	}

	public PasajeroArrastrame getIdPasajeroArrastrame() {
		return idPasajeroArrastrame;
	}

	public void setIdPasajeroArrastrame(PasajeroArrastrame idPasajeroArrastrame) {
		this.idPasajeroArrastrame = idPasajeroArrastrame;
	}
	
}
