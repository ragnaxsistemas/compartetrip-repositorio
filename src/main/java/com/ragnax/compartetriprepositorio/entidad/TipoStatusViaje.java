package com.ragnax.compartetriprepositorio.entidad;


import java.io.Serializable;

//import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: TipoStatusViaje
 * en la base de Datos representa 
 */
@Entity
@Table (name="tipo_status_viaje")

public class TipoStatusViaje implements Serializable{
 
	private static final long serialVersionUID = 4354315657103349310L;

	@Id
	@OrderBy
	@Column(name="id_tipo_status_viaje")
	private Integer idTipoStatusViaje;
	
	@Column(name="nombre_tipo_status_viaje")
	private String nombreTipoStatusViaje;
	
//	@OneToMany(mappedBy="idTipoStatusViaje")
//	private List<StatusViaje> status_viaje;
	
	public TipoStatusViaje() {
		super();
	}

	public TipoStatusViaje(Integer idTipoStatusViaje) {
		super();
		this.idTipoStatusViaje = idTipoStatusViaje;
	}
	
	public TipoStatusViaje(Integer idTipoStatusViaje, String nombreTipoStatusViaje) {
		super();
		this.idTipoStatusViaje = idTipoStatusViaje;
		this.nombreTipoStatusViaje = nombreTipoStatusViaje;
	}

	public Integer getIdTipoStatusViaje() {
		return idTipoStatusViaje;
	}

	public void setIdTipoStatusViaje(Integer idTipoStatusViaje) {
		this.idTipoStatusViaje = idTipoStatusViaje;
	}

	public String getNombreTipoStatusViaje() {
		return nombreTipoStatusViaje;
	}

	public void setNombreTipoStatusViaje(String nombreTipoStatusViaje) {
		this.nombreTipoStatusViaje = nombreTipoStatusViaje;
	}

//	public List<StatusViaje> getStatus_viaje() {
//		return status_viaje;
//	}
//
//	public void setStatus_viaje(List<StatusViaje> status_viaje) {
//		this.status_viaje = status_viaje;
//	}
}
