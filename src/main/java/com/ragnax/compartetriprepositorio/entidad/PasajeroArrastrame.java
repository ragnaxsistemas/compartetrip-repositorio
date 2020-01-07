package com.ragnax.compartetriprepositorio.entidad;

import java.io.Serializable;

//import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: PasajeroArrastrame
 * en la base de Datos representa 
 */
@Entity
@Table (name="pasajero_arrastrame")

public class PasajeroArrastrame implements Serializable{
 
	private static final long serialVersionUID = 77141173088279835L;

	@Id
	@OrderBy
	@Column(name="id_pasajero_arrastrame")
	private Integer idPasajeroArrastrame;
	
	@Column(name="fk_id_usuario")
	private Integer idUsuario;
	
	@ManyToOne
	@JoinColumn(name="fk_id_clasificacion_pasajero")
	private ClasificacionPasajero idClasificacionPasajero;
	
	@ManyToOne
	@JoinColumn(name="fk_id_rol_pasajero")
	private RolPasajero idRolPasajero;
	
	@Column(name="ubicacion_actual_pasajero")
	private String ubicacionActualPasajero;
	
//	@OneToMany(mappedBy="idPasajeroArrastrame")
//	private List<PasajeroArrastrameViaje> pasajeros_arrastrames_viajes;
	
	public PasajeroArrastrame() {
		super();
	}
	
	public PasajeroArrastrame(Integer idUsuario) {
		super();
		this.idUsuario = idUsuario;
	}
	
	public PasajeroArrastrame(Integer idUsuario, ClasificacionPasajero idClasificacionPasajero,
			RolPasajero idRolPasajero, String ubicacionActualPasajero) {
		super();
		this.idUsuario = idUsuario;
		this.idClasificacionPasajero = idClasificacionPasajero;
		this.idRolPasajero = idRolPasajero;
		this.ubicacionActualPasajero = ubicacionActualPasajero;
	}

	public Integer getIdPasajeroArrastrame() {
		return idPasajeroArrastrame;
	}

	public void setIdPasajeroArrastrame(Integer idPasajeroArrastrame) {
		this.idPasajeroArrastrame = idPasajeroArrastrame;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public ClasificacionPasajero getIdClasificacionPasajero() {
		return idClasificacionPasajero;
	}

	public void setIdClasificacionPasajero(ClasificacionPasajero idClasificacionPasajero) {
		this.idClasificacionPasajero = idClasificacionPasajero;
	}

	public RolPasajero getIdRolPasajero() {
		return idRolPasajero;
	}

	public void setIdRolPasajero(RolPasajero idRolPasajero) {
		this.idRolPasajero = idRolPasajero;
	}

	public String getUbicacionActualPasajero() {
		return ubicacionActualPasajero;
	}

	public void setUbicacionActualPasajero(String ubicacionActualPasajero) {
		this.ubicacionActualPasajero = ubicacionActualPasajero;
	}

//	public List<PasajeroArrastrameViaje> getPasajeros_arrastrames_viajes() {
//		return pasajeros_arrastrames_viajes;
//	}
//
//	public void setPasajeros_arrastrames_viajes(List<PasajeroArrastrameViaje> pasajeros_arrastrames_viajes) {
//		this.pasajeros_arrastrames_viajes = pasajeros_arrastrames_viajes;
//	}

}
