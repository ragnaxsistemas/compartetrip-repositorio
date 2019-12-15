package com.ragnax.compartetriprepositorio.entidad;

import java.sql.Timestamp;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Descuento
 * en la base de Datos representa 
 */
@Entity
@Table (name="descuento")

public class Descuento{
 
	@Id
	@OrderBy
	@Column(name="id_descuento")
	private Integer idDescuento;
	
	@Column(name="codigo_descuento")
	private String codigoDescuento;
	
	@Column(name="fecha_inicio_descuento")
	private Timestamp fechaInicioDescuento;
	
	@Column(name="fecha_fin_descuento")
	private Timestamp fechaFinDescuento;
	
	@Column(name="porcentaje_descuento")
	private Integer porcentajeDescuento;
	
	public Descuento() {
		super();
	}
	
	public Descuento(String codigoDescuento) {
		super();
		this.codigoDescuento = codigoDescuento;
	}
	
	public Descuento(String codigoDescuento, Timestamp fechaInicioDescuento, Timestamp fechaFinDescuento,
			Integer porcentajeDescuento) {
		super();
		this.codigoDescuento = codigoDescuento;
		this.fechaInicioDescuento = fechaInicioDescuento;
		this.fechaFinDescuento = fechaFinDescuento;
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public Integer getIdDescuento() {
		return idDescuento;
	}

	public void setIdDescuento(Integer idDescuento) {
		this.idDescuento = idDescuento;
	}

	public String getCodigoDescuento() {
		return codigoDescuento;
	}

	public void setCodigoDescuento(String codigoDescuento) {
		this.codigoDescuento = codigoDescuento;
	}

	public Timestamp getFechaInicioDescuento() {
		return fechaInicioDescuento;
	}

	public void setFechaInicioDescuento(Timestamp fechaInicioDescuento) {
		this.fechaInicioDescuento = fechaInicioDescuento;
	}

	public Timestamp getFechaFinDescuento() {
		return fechaFinDescuento;
	}

	public void setFechaFinDescuento(Timestamp fechaFinDescuento) {
		this.fechaFinDescuento = fechaFinDescuento;
	}
	
}
