package com.ragnax.compartetriprepositorio.entidad;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 *  implementation class for : StatusViaje
 * en la base de Datos representa 
 */
@Entity
@Table (name="status_viaje")

public class StatusViaje implements Serializable{
 
	private static final long serialVersionUID = 5848697973676176206L;

	@Id
	@OrderBy
	@Column(name="id_status_viaje")
	private Integer idStatusViaje;
	
	@Column(name="fecha_status_viaje")
	private Timestamp fechaStatusViaje;
	
	@ManyToOne
	@JoinColumn(name="fk_id_tipo_status_viaje")
	private TipoStatusViaje idTipoStatusViaje;
	
	@ManyToOne
	@JoinColumn(name="fk_id_viaje")
	private Viaje idViaje;
	
	public StatusViaje() {
		super();
	}

	public StatusViaje(Viaje idViaje) {
		super();
		this.idViaje = idViaje;
	}
	
	public StatusViaje(Timestamp fechaStatusViaje, Viaje idViaje) {
		super();
		this.fechaStatusViaje = fechaStatusViaje;
		this.idViaje = idViaje;
	}

	public Integer getIdStatusViaje() {
		return idStatusViaje;
	}

	public void setIdStatusViaje(Integer idStatusViaje) {
		this.idStatusViaje = idStatusViaje;
	}

	public Timestamp getFechaStatusViaje() {
		return fechaStatusViaje;
	}

	public void setFechaStatusViaje(Timestamp fechaStatusViaje) {
		this.fechaStatusViaje = fechaStatusViaje;
	}

	public TipoStatusViaje getIdTipoStatusViaje() {
		return idTipoStatusViaje;
	}

	public void setIdTipoStatusViaje(TipoStatusViaje idTipoStatusViaje) {
		this.idTipoStatusViaje = idTipoStatusViaje;
	}

	public Viaje getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(Viaje idViaje) {
		this.idViaje = idViaje;
	}
	
}
