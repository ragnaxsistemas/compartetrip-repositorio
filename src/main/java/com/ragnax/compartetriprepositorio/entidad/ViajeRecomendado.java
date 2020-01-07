package com.ragnax.compartetriprepositorio.entidad;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: TipoViajeRecomendado
 * en la base de Datos representa 
 */
@Entity
@Table (name="viaje_recomendado")

public class ViajeRecomendado implements Serializable{
 
	private static final long serialVersionUID = 6339368606346657164L;

	@Id
	@OrderBy
	@Column(name="id_viaje_recomendado")
	private Integer idViajeRecomendado;
	
	@ManyToOne
	@JoinColumn(name="fk_id_viaje")
	private Viaje idViaje;
	
	@ManyToOne
	@JoinColumn(name="fk_id_tipo_viaje_recomendado")
	private TipoViajeRecomendado idTipoViajeRecomendado;

	public ViajeRecomendado() {
		super();
	}
	
	public ViajeRecomendado(Integer idViajeRecomendado) {
		super();
		this.idViajeRecomendado = idViajeRecomendado;
	}

	public ViajeRecomendado(Viaje idViaje, TipoViajeRecomendado idTipoViajeRecomendado) {
		super();
		this.idViaje = idViaje;
		this.idTipoViajeRecomendado = idTipoViajeRecomendado;
	}

	public Integer getIdViajeRecomendado() {
		return idViajeRecomendado;
	}

	public void setIdViajeRecomendado(Integer idViajeRecomendado) {
		this.idViajeRecomendado = idViajeRecomendado;
	}

	public Viaje getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(Viaje idViaje) {
		this.idViaje = idViaje;
	}

	public TipoViajeRecomendado getIdTipoViajeRecomendado() {
		return idTipoViajeRecomendado;
	}

	public void setIdTipoViajeRecomendado(TipoViajeRecomendado idTipoViajeRecomendado) {
		this.idTipoViajeRecomendado = idTipoViajeRecomendado;
	}
}
