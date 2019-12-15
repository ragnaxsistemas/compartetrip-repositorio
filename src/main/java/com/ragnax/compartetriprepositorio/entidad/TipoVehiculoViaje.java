package com.ragnax.compartetriprepositorio.entidad;


import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: TipoVehiculoViaje
 * en la base de Datos representa 
 */
@Entity
@Table (name="tipo_vehiculo_viaje")

public class TipoVehiculoViaje{
 
	@Id
	@OrderBy
	@Column(name="id_tipo_vehiculo_viaje")
	private Integer idTipoVehiculoViaje;
	
	@Column(name="nombre_tipo_vehiculo_viaje")
	private String nombreTipoVehiculoViaje;
	
	@Column(name="prioridad_tipo_vehiculo_viaje")
	private Integer prioridadTipoVehiculoViaje;
	
	@OneToMany(mappedBy="idTipoVehiculoViaje")
	private List<Viaje> viajes;
	
	public TipoVehiculoViaje() {
		super();
	}

	public TipoVehiculoViaje(Integer idTipoVehiculoViaje) {
		super();
		this.idTipoVehiculoViaje = idTipoVehiculoViaje;
	}
	
	public TipoVehiculoViaje(String nombreTipoVehiculoViaje, Integer prioridadTipoVehiculoViaje) {
		super();
		this.nombreTipoVehiculoViaje = nombreTipoVehiculoViaje;
		this.prioridadTipoVehiculoViaje = prioridadTipoVehiculoViaje;
	}

	public Integer getIdTipoVehiculoViaje() {
		return idTipoVehiculoViaje;
	}

	public void setIdTipoVehiculoViaje(Integer idTipoVehiculoViaje) {
		this.idTipoVehiculoViaje = idTipoVehiculoViaje;
	}

	public String getNombreTipoVehiculoViaje() {
		return nombreTipoVehiculoViaje;
	}

	public void setNombreTipoVehiculoViaje(String nombreTipoVehiculoViaje) {
		this.nombreTipoVehiculoViaje = nombreTipoVehiculoViaje;
	}

	public Integer getPrioridadTipoVehiculoViaje() {
		return prioridadTipoVehiculoViaje;
	}

	public void setPrioridadTipoVehiculoViaje(Integer prioridadTipoVehiculoViaje) {
		this.prioridadTipoVehiculoViaje = prioridadTipoVehiculoViaje;
	}

	public List<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(List<Viaje> viajes) {
		this.viajes = viajes;
	}
	
}
