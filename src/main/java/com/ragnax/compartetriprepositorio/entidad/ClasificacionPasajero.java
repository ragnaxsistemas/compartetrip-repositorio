package com.ragnax.compartetriprepositorio.entidad;


import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ClasificacionPasajero
 * en la base de Datos representa 
 */
@Entity
@Table (name="clasificacion_pasajero")

public class ClasificacionPasajero{
 
	@Id
	@OrderBy
	@Column(name="id_clasificacion_pasajero")
	private Integer idClasificacionPasajero;
	
	@Column(name="nombre_clasificacion_pasajero")
	private String nombreClasificacionPasajero;
	
	@Column(name="limite_minimo_viaje")
	private Integer limiteMinimoViaje;
	
//	@Column(name="prioridad_clasificacion_pasajero")
//	private Integer prioridadClasificacionPasajero;
	
	@OneToMany(mappedBy="idClasificacionPasajero")
	private List<PasajeroArrastrame> pasajeros_arrastrames;
	
	public ClasificacionPasajero(Integer idClasificacionPasajero) {
		super();
		this.idClasificacionPasajero = idClasificacionPasajero;
	}
	
	public ClasificacionPasajero(String nombreClasificacionPasajero, Integer limiteMinimoViaje) {
		super();
		this.nombreClasificacionPasajero = nombreClasificacionPasajero;
		this.limiteMinimoViaje = limiteMinimoViaje;
	}
	
	public ClasificacionPasajero() {
		super();
	}

	public Integer getIdClasificacionPasajero() {
		return idClasificacionPasajero;
	}

	public void setIdClasificacionPasajero(Integer idClasificacionPasajero) {
		this.idClasificacionPasajero = idClasificacionPasajero;
	}

	public String getNombreClasificacionPasajero() {
		return nombreClasificacionPasajero;
	}

	public void setNombreClasificacionPasajero(String nombreClasificacionPasajero) {
		this.nombreClasificacionPasajero = nombreClasificacionPasajero;
	}

	public Integer getLimiteMinimoViaje() {
		return limiteMinimoViaje;
	}

	public void setLimiteMinimoViaje(Integer limiteMinimoViaje) {
		this.limiteMinimoViaje = limiteMinimoViaje;
	}

	public List<PasajeroArrastrame> getPasajeros_arrastrames() {
		return pasajeros_arrastrames;
	}

	public void setPasajeros_arrastrames(List<PasajeroArrastrame> pasajeros_arrastrames) {
		this.pasajeros_arrastrames = pasajeros_arrastrames;
	}
}
