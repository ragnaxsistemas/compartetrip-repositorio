package com.ragnax.compartetriprepositorio.entidad;

import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: TipoViajeRecomendado
 * en la base de Datos representa 
 */
@Entity
@Table (name="tipo_viaje_recomendado")

public class TipoViajeRecomendado{
 
	@Id
	@OrderBy
	@Column(name="id_tipo_viaje_recomendado")
	private Integer idTipoViajeRecomendado;
	
	@Column(name="nombre_tipo_viaje_recomendado")
	private String nombreTipoViajeRecomendado;
	
	@OneToMany(mappedBy="idTipoViajeRecomendado")
	private List<ViajeRecomendado> viajes_recomendados;

	public TipoViajeRecomendado() {
		super();
	}
	
	public TipoViajeRecomendado(Integer idTipoViajeRecomendado) {
		super();
		this.idTipoViajeRecomendado = idTipoViajeRecomendado;
	}

	public TipoViajeRecomendado(Integer idTipoViajeRecomendado, String nombreTipoViajeRecomendado) {
		super();
		this.idTipoViajeRecomendado = idTipoViajeRecomendado;
		this.nombreTipoViajeRecomendado = nombreTipoViajeRecomendado;
	}

	public Integer getIdTipoViajeRecomendado() {
		return idTipoViajeRecomendado;
	}

	public void setIdTipoViajeRecomendado(Integer idTipoViajeRecomendado) {
		this.idTipoViajeRecomendado = idTipoViajeRecomendado;
	}
	
	public String getNombreTipoViajeRecomendado() {
		return nombreTipoViajeRecomendado;
	}

	public void setNombreTipoViajeRecomendado(String nombreTipoViajeRecomendado) {
		this.nombreTipoViajeRecomendado = nombreTipoViajeRecomendado;
	}

	public List<ViajeRecomendado> getViajes_recomendados() {
		return viajes_recomendados;
	}

	public void setViajes_recomendados(List<ViajeRecomendado> viajes_recomendados) {
		this.viajes_recomendados = viajes_recomendados;
	}
	
}
