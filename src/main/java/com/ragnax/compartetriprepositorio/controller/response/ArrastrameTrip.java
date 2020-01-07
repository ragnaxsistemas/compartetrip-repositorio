//package com.ragnax.compartetriprepositorio.controller.response;
//
//import java.io.Serializable;
//import java.util.List;
//
//import com.ragnax.compartetriprepositorio.entidad.ClasificacionPasajero;
//import com.ragnax.compartetriprepositorio.entidad.Descuento;
//import com.ragnax.compartetriprepositorio.entidad.DescuentoViaje;
//import com.ragnax.compartetriprepositorio.entidad.PasajeroArrastrame;
//import com.ragnax.compartetriprepositorio.entidad.PasajeroArrastrameViaje;
//import com.ragnax.compartetriprepositorio.entidad.RolPasajero;
//import com.ragnax.compartetriprepositorio.entidad.StatusViaje;
//import com.ragnax.compartetriprepositorio.entidad.TipoStatusViaje;
//import com.ragnax.compartetriprepositorio.entidad.TipoVehiculoViaje;
//import com.ragnax.compartetriprepositorio.entidad.TipoViaje;
//import com.ragnax.compartetriprepositorio.entidad.Viaje;
//import com.ragnax.compartetriprepositorio.entidad.ViajeRecomendado;
//import com.ragnax.compartetriprepositorio.entidad.TipoViajeRecomendado;
//
//public class ArrastrameTrip implements Serializable{
//
//	private static final long serialVersionUID = -4301293450469130528L;
//
//	private TipoViajeRecomendado tipoViajeRecomendado;
//	private TipoVehiculoViaje tipoVehiculoViaje;
//	private TipoViaje tipoViaje;
//	private TipoStatusViaje tipoStatusViaje;
//	private ClasificacionPasajero clasificacionPasajero;
//	private RolPasajero rolPasajero;
//	private Descuento descuento;
//	private PasajeroArrastrame pasajeroArrastrame;
//	private Viaje viaje;
//	private StatusViaje statusViaje;
//	private ViajeRecomendado viajeRecomendado;
//	private DescuentoViaje descuentoViaje;
//	private PasajeroArrastrameViaje pasajeroArrastrameViaje;
//	
//	private List<TipoViajeRecomendado> listaTipoViajeRecomendado;
//	private List<TipoVehiculoViaje> listaTipoVehiculoViaje;
//	private List<TipoViaje> listaTipoViaje;
//	private List<TipoStatusViaje> listaTipoStatusViaje;
//	private List<ClasificacionPasajero> listaClasificacionPasajero;
//	private List<RolPasajero> listaRolPasajero;
//	private List<Descuento> listaDescuento;
//	private List<PasajeroArrastrame> listaPasajeroArrastrame;
//	private List<Viaje> listaViaje;
//	private List<StatusViaje> listaStatusViaje;
//	private List<ViajeRecomendado> listaViajeRecomendado;
//	private List<DescuentoViaje> listaDescuentoViaje;
//	private List<PasajeroArrastrameViaje> listaPasajeroArrastrameViaje;
//	
//	public ArrastrameTrip() {
//		super();
//	}
//
//	public TipoViajeRecomendado getTipoViajeRecomendado() {
//		return tipoViajeRecomendado;
//	}
//
//	public void setTipoViajeRecomendado(TipoViajeRecomendado tipoViajeRecomendado) {
//		this.tipoViajeRecomendado = tipoViajeRecomendado;
//	}
//
//	public TipoVehiculoViaje getTipoVehiculoViaje() {
//		return tipoVehiculoViaje;
//	}
//
//	public void setTipoVehiculoViaje(TipoVehiculoViaje tipoVehiculoViaje) {
//		this.tipoVehiculoViaje = tipoVehiculoViaje;
//	}
//
//	public TipoViaje getTipoViaje() {
//		return tipoViaje;
//	}
//
//	public void setTipoViaje(TipoViaje tipoViaje) {
//		this.tipoViaje = tipoViaje;
//	}
//
//	public TipoStatusViaje getTipoStatusViaje() {
//		return tipoStatusViaje;
//	}
//
//	public void setTipoStatusViaje(TipoStatusViaje tipoStatusViaje) {
//		this.tipoStatusViaje = tipoStatusViaje;
//	}
//
//	public ClasificacionPasajero getClasificacionPasajero() {
//		return clasificacionPasajero;
//	}
//
//	public void setClasificacionPasajero(ClasificacionPasajero clasificacionPasajero) {
//		this.clasificacionPasajero = clasificacionPasajero;
//	}
//
//	public RolPasajero getRolPasajero() {
//		return rolPasajero;
//	}
//
//	public void setRolPasajero(RolPasajero rolPasajero) {
//		this.rolPasajero = rolPasajero;
//	}
//
//	public Descuento getDescuento() {
//		return descuento;
//	}
//
//	public void setDescuento(Descuento descuento) {
//		this.descuento = descuento;
//	}
//
//	public PasajeroArrastrame getPasajeroArrastrame() {
//		return pasajeroArrastrame;
//	}
//
//	public void setPasajeroArrastrame(PasajeroArrastrame pasajeroArrastrame) {
//		this.pasajeroArrastrame = pasajeroArrastrame;
//	}
//
//	public Viaje getViaje() {
//		return viaje;
//	}
//
//	public void setViaje(Viaje viaje) {
//		this.viaje = viaje;
//	}
//
//	public StatusViaje getStatusViaje() {
//		return statusViaje;
//	}
//
//	public void setStatusViaje(StatusViaje statusViaje) {
//		this.statusViaje = statusViaje;
//	}
//
//	public ViajeRecomendado getViajeRecomendado() {
//		return viajeRecomendado;
//	}
//
//	public void setViajeRecomendado(ViajeRecomendado viajeRecomendado) {
//		this.viajeRecomendado = viajeRecomendado;
//	}
//
//	public DescuentoViaje getDescuentoViaje() {
//		return descuentoViaje;
//	}
//
//	public void setDescuentoViaje(DescuentoViaje descuentoViaje) {
//		this.descuentoViaje = descuentoViaje;
//	}
//
//	public PasajeroArrastrameViaje getPasajeroArrastrameViaje() {
//		return pasajeroArrastrameViaje;
//	}
//
//	public void setPasajeroArrastrameViaje(PasajeroArrastrameViaje pasajeroArrastrameViaje) {
//		this.pasajeroArrastrameViaje = pasajeroArrastrameViaje;
//	}
//
//	public List<TipoViajeRecomendado> getListaTipoViajeRecomendado() {
//		return listaTipoViajeRecomendado;
//	}
//
//	public void setListaTipoViajeRecomendado(List<TipoViajeRecomendado> listaTipoViajeRecomendado) {
//		this.listaTipoViajeRecomendado = listaTipoViajeRecomendado;
//	}
//
//	public List<TipoVehiculoViaje> getListaTipoVehiculoViaje() {
//		return listaTipoVehiculoViaje;
//	}
//
//	public void setListaTipoVehiculoViaje(List<TipoVehiculoViaje> listaTipoVehiculoViaje) {
//		this.listaTipoVehiculoViaje = listaTipoVehiculoViaje;
//	}
//
//	public List<TipoViaje> getListaTipoViaje() {
//		return listaTipoViaje;
//	}
//
//	public void setListaTipoViaje(List<TipoViaje> listaTipoViaje) {
//		this.listaTipoViaje = listaTipoViaje;
//	}
//
//	public List<TipoStatusViaje> getListaTipoStatusViaje() {
//		return listaTipoStatusViaje;
//	}
//
//	public void setListaTipoStatusViaje(List<TipoStatusViaje> listaTipoStatusViaje) {
//		this.listaTipoStatusViaje = listaTipoStatusViaje;
//	}
//
//	public List<ClasificacionPasajero> getListaClasificacionPasajero() {
//		return listaClasificacionPasajero;
//	}
//
//	public void setListaClasificacionPasajero(List<ClasificacionPasajero> listaClasificacionPasajero) {
//		this.listaClasificacionPasajero = listaClasificacionPasajero;
//	}
//
//	public List<RolPasajero> getListaRolPasajero() {
//		return listaRolPasajero;
//	}
//
//	public void setListaRolPasajero(List<RolPasajero> listaRolPasajero) {
//		this.listaRolPasajero = listaRolPasajero;
//	}
//
//	public List<Descuento> getListaDescuento() {
//		return listaDescuento;
//	}
//
//	public void setListaDescuento(List<Descuento> listaDescuento) {
//		this.listaDescuento = listaDescuento;
//	}
//
//	public List<PasajeroArrastrame> getListaPasajeroArrastrame() {
//		return listaPasajeroArrastrame;
//	}
//
//	public void setListaPasajeroArrastrame(List<PasajeroArrastrame> listaPasajeroArrastrame) {
//		this.listaPasajeroArrastrame = listaPasajeroArrastrame;
//	}
//
//	public List<Viaje> getListaViaje() {
//		return listaViaje;
//	}
//
//	public void setListaViaje(List<Viaje> listaViaje) {
//		this.listaViaje = listaViaje;
//	}
//
//	public List<StatusViaje> getListaStatusViaje() {
//		return listaStatusViaje;
//	}
//
//	public void setListaStatusViaje(List<StatusViaje> listaStatusViaje) {
//		this.listaStatusViaje = listaStatusViaje;
//	}
//
//	public List<ViajeRecomendado> getListaViajeRecomendado() {
//		return listaViajeRecomendado;
//	}
//
//	public void setListaViajeRecomendado(List<ViajeRecomendado> listaViajeRecomendado) {
//		this.listaViajeRecomendado = listaViajeRecomendado;
//	}
//
//	public List<DescuentoViaje> getListaDescuentoViaje() {
//		return listaDescuentoViaje;
//	}
//
//	public void setListaDescuentoViaje(List<DescuentoViaje> listaDescuentoViaje) {
//		this.listaDescuentoViaje = listaDescuentoViaje;
//	}
//
//	public List<PasajeroArrastrameViaje> getListaPasajeroArrastrameViaje() {
//		return listaPasajeroArrastrameViaje;
//	}
//
//	public void setListaPasajeroArrastrameViaje(List<PasajeroArrastrameViaje> listaPasajeroArrastrameViaje) {
//		this.listaPasajeroArrastrameViaje = listaPasajeroArrastrameViaje;
//	}
//	
//}
