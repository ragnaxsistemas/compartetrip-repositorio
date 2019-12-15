package com.ragnax.compartetriprepositorio.entidad;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: Descuento
 * en la base de Datos representa 
 */
@Entity
@Table (name="descuento_viaje")

public class DescuentoViaje{
 
	@Id
	@OrderBy
	@Column(name="id_descuento_viaje")
	private Integer idDescuentoViaje;
	
	@ManyToOne
	@JoinColumn(name="fk_id_descuento")
	private Descuento idDescuento;
	
	@ManyToOne
	@JoinColumn(name="fk_id_viaje")
	private Viaje idViaje;
	
	@Column(name="estado_descuento_viaje")
	private Boolean estadoDescuentoViaje;
	
	public DescuentoViaje() {
		super();
	}
	
	public DescuentoViaje(Descuento idDescuento, Viaje idViaje) {
		super();
		this.idDescuento = idDescuento;
		this.idViaje = idViaje;
	}
	
	public DescuentoViaje(Descuento idDescuento, Viaje idViaje, Boolean estadoDescuentoViaje) {
		super();
		this.idDescuento = idDescuento;
		this.idViaje = idViaje;
		this.estadoDescuentoViaje = estadoDescuentoViaje;
	}
	
	public Integer getIdDescuentoViaje() {
		return idDescuentoViaje;
	}

	public void setIdDescuentoViaje(Integer idDescuentoViaje) {
		this.idDescuentoViaje = idDescuentoViaje;
	}

	public Descuento getIdDescuento() {
		return idDescuento;
	}

	public void setIdDescuento(Descuento idDescuento) {
		this.idDescuento = idDescuento;
	}

	public Viaje getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(Viaje idViaje) {
		this.idViaje = idViaje;
	}

	public Boolean getEstadoDescuentoViaje() {
		return estadoDescuentoViaje;
	}

	public void setEstadoDescuentoViaje(Boolean estadoDescuentoViaje) {
		this.estadoDescuentoViaje = estadoDescuentoViaje;
	}
}
