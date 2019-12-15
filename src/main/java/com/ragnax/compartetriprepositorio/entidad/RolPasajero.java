package com.ragnax.compartetriprepositorio.entidad;


import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: RolPasajero
 * en la base de Datos representa 
 */
@Entity
@Table (name="rol_pasajero")

public class RolPasajero{
 
	@Id
	@OrderBy
	@Column(name="id_rol_pasajero")
	private Integer idRolPasajero;
	
	@Column(name="nombre_rol_pasajero")
	private String nombreRolPasajero;
	
	@OneToMany(mappedBy="idRolPasajero")
	private List<PasajeroArrastrame> pasajeros_arrastrames;
	
	public RolPasajero() {
		super();
	}
	
	public RolPasajero(Integer idRolPasajero) {
		super();
		this.idRolPasajero = idRolPasajero;
	}
	
	public RolPasajero(Integer idRolPasajero, String nombreRolPasajero) {
		super();
		this.idRolPasajero = idRolPasajero;
		this.nombreRolPasajero = nombreRolPasajero;
	}

	public Integer getIdRolPasajero() {
		return idRolPasajero;
	}

	public void setIdRolPasajero(Integer idRolPasajero) {
		this.idRolPasajero = idRolPasajero;
	}

	public String getNombreRolPasajero() {
		return nombreRolPasajero;
	}

	public void setNombreRolPasajero(String nombreRolPasajero) {
		this.nombreRolPasajero = nombreRolPasajero;
	}

	public List<PasajeroArrastrame> getPasajeros_arrastrames() {
		return pasajeros_arrastrames;
	}

	public void setPasajeros_arrastrames(List<PasajeroArrastrame> pasajeros_arrastrames) {
		this.pasajeros_arrastrames = pasajeros_arrastrames;
	}
}
