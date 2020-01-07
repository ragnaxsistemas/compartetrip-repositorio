package com.ragnax.compartetriprepositorio.entidad;

import java.io.Serializable;

//import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: TipoViaje
 * en la base de Datos representa 
 */
@Entity
@Table (name="tipo_viaje")

public class TipoViaje implements Serializable{
 
	private static final long serialVersionUID = 5044658604973525937L;

	@Id
	@OrderBy
	@Column(name="id_tipo_viaje")
	private Integer idTipoViaje;
	
	@Column(name="nombre_tipo_viaje")
	private String nombreTipoViaje;
	
//	@OneToMany(mappedBy="idTipoViaje")
//	private List<Viaje> viajes;
	
	public TipoViaje() {
		super();
	}
	
	public TipoViaje(Integer idTipoViaje) {
		super();
		this.idTipoViaje = idTipoViaje;
	}
	
	public TipoViaje(Integer idTipoViaje, String nombreTipoViaje) {
		super();
		this.idTipoViaje = idTipoViaje;
		this.nombreTipoViaje = nombreTipoViaje;
	}

	public Integer getIdTipoViaje() {
		return idTipoViaje;
	}

	public void setIdTipoViaje(Integer idTipoViaje) {
		this.idTipoViaje = idTipoViaje;
	}

	public String getNombreTipoViaje() {
		return nombreTipoViaje;
	}

	public void setNombreTipoViaje(String nombreTipoViaje) {
		this.nombreTipoViaje = nombreTipoViaje;
	}

//	public List<Viaje> getViajes() {
//		return viajes;
//	}
//
//	public void setViajes(List<Viaje> viajes) {
//		this.viajes = viajes;
//	}
}
