package com.ragnax.compartetriprepositorio.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
//import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Viaje
 * en la base de Datos representa 
 */
@Entity
@Table (name="viaje")

public class Viaje implements Serializable{
 
	private static final long serialVersionUID = 2070159180483095374L;

	@Id
	@OrderBy
	@Column(name="id_viaje")
	private Integer idViaje;
	
	@Column(name="codigo_viaje")
	private String codigoViaje;
	
	@ManyToOne
	@JoinColumn(name="fk_id_pasajero_arrastrame")
	private PasajeroArrastrame idPasajeroArrastrame;
	
	@ManyToOne
	@JoinColumn(name="fk_id_tipo_viaje")
	private TipoViaje idTipoViaje;
	
	@ManyToOne
	@JoinColumn(name="fk_id_tipo_vehiculo_viaje")
	private TipoVehiculoViaje idTipoVehiculoViaje;
	
	@ManyToOne
	@JoinColumn(name="fk_id_viaje_recomendado")
	private TipoViajeRecomendado idTipoViajeRecomendado;
	
	@Column(name="cantidad_pasajeros")
	private String cantidadPasajeros;
	
//	@Column(name="stime_inicio_viaje")
//	private String stimeInicioViaje;
	
	@Column(name="time_inicio_viaje")
	private Timestamp timeInicioViaje;
	
	@Column(name="posicion_inicio_trayecto")
	private String posicionInicioTrayecto;
	
	@Column(name="nombre_localizacion_inicio")
	private String nombreLocalizacionInicio;
	
	@Column(name="posicion_final_trayecto")
	private String posicionFinalTrayecto;
	
	@Column(name="nombre_localizacion_final")
	private String nombreLocalizacionFinal;
	
	@Column(name="distancia_total")
	private String distanciaTotal;
	
	@Column(name="valor_cuota_trayecto")
	private BigDecimal valorCuotaTrayecto;
	
	@Column(name="fecha_registro_viaje")
	private Timestamp fechaRegistroViaje;
	
//	@OneToMany(mappedBy="idViaje")
//	private List<PasajeroArrastrameViaje> pasajeros_arrastrames_viajes;
//	
//	@OneToMany(mappedBy="idViaje")
//	private List<DescuentoViaje> descuentos_viajes;
//	
//	@OneToMany(mappedBy="idViaje")
//	private List<ViajeRecomendado> viajes_recomendados;

	public Viaje() {
		super();
	}
	
	public Viaje(String codigoViaje) {
		super();
		this.codigoViaje = codigoViaje;
	}
	
	public Viaje(String codigoViaje, String stimeInicioViaje) {
		super();
		this.codigoViaje = codigoViaje;
//		this.stimeInicioViaje = stimeInicioViaje;
	}
	
	public Viaje(PasajeroArrastrame idPasajeroArrastrame, 
			 TipoViaje idTipoViaje, TipoVehiculoViaje idTipoVehiculoViaje, TipoViajeRecomendado idTipoViajeRecomendado,
			String cantidadPasajeros, Timestamp timeInicioViaje,
 
			String posicionInicioTrayecto, String nombreLocalizacionInicio,
			String posicionFinalTrayecto, String nombreLocalizacionFinal, String distanciaTotal, BigDecimal valorCuotaTrayecto) {
		super();
		this.idPasajeroArrastrame = idPasajeroArrastrame;
		this.idTipoViaje = idTipoViaje;
		this.idTipoVehiculoViaje = idTipoVehiculoViaje;
		this.idTipoViajeRecomendado = idTipoViajeRecomendado;
		this.cantidadPasajeros = cantidadPasajeros;
		this.timeInicioViaje = timeInicioViaje;
		this.posicionInicioTrayecto = posicionInicioTrayecto;
		this.nombreLocalizacionInicio = nombreLocalizacionInicio;
		this.posicionFinalTrayecto = posicionFinalTrayecto;
		this.nombreLocalizacionFinal = nombreLocalizacionFinal;
		this.distanciaTotal = distanciaTotal;
		this.valorCuotaTrayecto = valorCuotaTrayecto;
	}
	
	public Viaje(PasajeroArrastrame idPasajeroArrastrame, 
			 TipoViaje idTipoViaje, TipoVehiculoViaje idTipoVehiculoViaje, TipoViajeRecomendado idTipoViajeRecomendado,
			String cantidadPasajeros, 
//			String stimeInicioViaje, 
			String posicionInicioTrayecto, String nombreLocalizacionInicio,
			String posicionFinalTrayecto, String nombreLocalizacionFinal, String distanciaTotal, BigDecimal valorCuotaTrayecto) {
		super();
		this.idPasajeroArrastrame = idPasajeroArrastrame;
		this.idTipoViaje = idTipoViaje;
		this.idTipoVehiculoViaje = idTipoVehiculoViaje;
		this.idTipoViajeRecomendado = idTipoViajeRecomendado;
		this.cantidadPasajeros = cantidadPasajeros;
//		this.stimeInicioViaje = stimeInicioViaje;
		this.posicionInicioTrayecto = posicionInicioTrayecto;
		this.nombreLocalizacionInicio = nombreLocalizacionInicio;
		this.posicionFinalTrayecto = posicionFinalTrayecto;
		this.nombreLocalizacionFinal = nombreLocalizacionFinal;
		this.distanciaTotal = distanciaTotal;
		this.valorCuotaTrayecto = valorCuotaTrayecto;
	}

	public Integer getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(Integer idViaje) {
		this.idViaje = idViaje;
	}

	public String getCodigoViaje() {
		return codigoViaje;
	}

	public void setCodigoViaje(String codigoViaje) {
		this.codigoViaje = codigoViaje;
	}

	public PasajeroArrastrame getIdPasajeroArrastrame() {
		return idPasajeroArrastrame;
	}

	public void setIdPasajeroArrastrame(PasajeroArrastrame idPasajeroArrastrame) {
		this.idPasajeroArrastrame = idPasajeroArrastrame;
	}

	public TipoViaje getIdTipoViaje() {
		return idTipoViaje;
	}

	public void setIdTipoViaje(TipoViaje idTipoViaje) {
		this.idTipoViaje = idTipoViaje;
	}

	public TipoVehiculoViaje getIdTipoVehiculoViaje() {
		return idTipoVehiculoViaje;
	}

	public void setIdTipoVehiculoViaje(TipoVehiculoViaje idTipoVehiculoViaje) {
		this.idTipoVehiculoViaje = idTipoVehiculoViaje;
	}

	public TipoViajeRecomendado getIdTipoViajeRecomendado() {
		return idTipoViajeRecomendado;
	}

	public void setIdTipoViajeRecomendado(TipoViajeRecomendado idTipoViajeRecomendado) {
		this.idTipoViajeRecomendado = idTipoViajeRecomendado;
	}

	public String getCantidadPasajeros() {
		return cantidadPasajeros;
	}

	public void setCantidadPasajeros(String cantidadPasajeros) {
		this.cantidadPasajeros = cantidadPasajeros;
	}

//	public String getStimeInicioViaje() {
//		return stimeInicioViaje;
//	}
//
//	public void setStimeInicioViaje(String stimeInicioViaje) {
//		this.stimeInicioViaje = stimeInicioViaje;
//	}

	public Timestamp getTimeInicioViaje() {
		return timeInicioViaje;
	}

	public void setTimeInicioViaje(Timestamp timeInicioViaje) {
		this.timeInicioViaje = timeInicioViaje;
	}

	public String getPosicionInicioTrayecto() {
		return posicionInicioTrayecto;
	}

	public void setPosicionInicioTrayecto(String posicionInicioTrayecto) {
		this.posicionInicioTrayecto = posicionInicioTrayecto;
	}

	public String getNombreLocalizacionInicio() {
		return nombreLocalizacionInicio;
	}

	public void setNombreLocalizacionInicio(String nombreLocalizacionInicio) {
		this.nombreLocalizacionInicio = nombreLocalizacionInicio;
	}

	public String getPosicionFinalTrayecto() {
		return posicionFinalTrayecto;
	}

	public void setPosicionFinalTrayecto(String posicionFinalTrayecto) {
		this.posicionFinalTrayecto = posicionFinalTrayecto;
	}

	public String getNombreLocalizacionFinal() {
		return nombreLocalizacionFinal;
	}

	public void setNombreLocalizacionFinal(String nombreLocalizacionFinal) {
		this.nombreLocalizacionFinal = nombreLocalizacionFinal;
	}

	public String getDistanciaTotal() {
		return distanciaTotal;
	}

	public void setDistanciaTotal(String distanciaTotal) {
		this.distanciaTotal = distanciaTotal;
	}

	public BigDecimal getValorCuotaTrayecto() {
		return valorCuotaTrayecto;
	}

	public void setValorCuotaTrayecto(BigDecimal valorCuotaTrayecto) {
		this.valorCuotaTrayecto = valorCuotaTrayecto;
	}

	public Timestamp getFechaRegistroViaje() {
		return fechaRegistroViaje;
	}

	public void setFechaRegistroViaje(Timestamp fechaRegistroViaje) {
		this.fechaRegistroViaje = fechaRegistroViaje;
	}

//	public List<PasajeroArrastrameViaje> getPasajeros_arrastrames_viajes() {
//		return pasajeros_arrastrames_viajes;
//	}
//
//	public void setPasajeros_arrastrames_viajes(List<PasajeroArrastrameViaje> pasajeros_arrastrames_viajes) {
//		this.pasajeros_arrastrames_viajes = pasajeros_arrastrames_viajes;
//	}
//
//	public List<DescuentoViaje> getDescuentos_viajes() {
//		return descuentos_viajes;
//	}
//
//	public void setDescuentos_viajes(List<DescuentoViaje> descuentos_viajes) {
//		this.descuentos_viajes = descuentos_viajes;
//	}
//
//	public List<ViajeRecomendado> getViajes_recomendados() {
//		return viajes_recomendados;
//	}
//
//	public void setViajes_recomendados(List<ViajeRecomendado> viajes_recomendados) {
//		this.viajes_recomendados = viajes_recomendados;
//	}
}
