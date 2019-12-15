package com.ragnax.compartetriprepositorio.servicio;

import java.sql.Timestamp;
import java.util.Date;

import com.ragnax.compartetriprepositorio.controller.response.ArrastrameTrip;
import com.ragnax.compartetriprepositorio.entidad.ClasificacionPasajero;
import com.ragnax.compartetriprepositorio.entidad.Descuento;
import com.ragnax.compartetriprepositorio.entidad.DescuentoViaje;
import com.ragnax.compartetriprepositorio.entidad.PasajeroArrastrame;
import com.ragnax.compartetriprepositorio.entidad.PasajeroArrastrameViaje;
import com.ragnax.compartetriprepositorio.entidad.RolPasajero;
import com.ragnax.compartetriprepositorio.entidad.StatusViaje;
import com.ragnax.compartetriprepositorio.entidad.TipoStatusViaje;
import com.ragnax.compartetriprepositorio.entidad.TipoVehiculoViaje;
import com.ragnax.compartetriprepositorio.entidad.TipoViaje;
import com.ragnax.compartetriprepositorio.entidad.TipoViajeRecomendado;
import com.ragnax.compartetriprepositorio.entidad.Viaje;
import com.ragnax.compartetriprepositorio.entidad.ViajeRecomendado;
import com.ragnax.compartetriprepositorio.exception.LogicaImplException;

public interface ArrastrameTripService {
	
	public ArrastrameTrip crearTipoViajeRecomendado(TipoViajeRecomendado objTipoViajeRecomendado) throws LogicaImplException;
	public ArrastrameTrip actualizarTipoViajeRecomendado(Integer id, TipoViajeRecomendado objTipoViajeRecomendado) throws LogicaImplException;
	public ArrastrameTrip buscarTipoViajeRecomendado(TipoViajeRecomendado objTipoViajeRecomendado) throws LogicaImplException;
	public ArrastrameTrip listarTodoTipoViajeRecomendado() throws LogicaImplException;
	
	public ArrastrameTrip crearTipoVehiculoViaje(TipoVehiculoViaje objTipoVehiculoViaje) throws LogicaImplException;
	public ArrastrameTrip actualizarTipoVehiculoViaje(Integer id, TipoVehiculoViaje objTipoVehiculoViaje) throws LogicaImplException;
	public ArrastrameTrip buscarTipoVehiculoViaje(TipoVehiculoViaje objTipoVehiculoViaje) throws LogicaImplException;
	public ArrastrameTrip listarTodoTipoVehiculoViaje() throws LogicaImplException;
	
	public ArrastrameTrip crearTipoViaje(TipoViaje objTipoViaje) throws LogicaImplException;
	public ArrastrameTrip actualizarTipoViaje(Integer id, TipoViaje objTipoViaje) throws LogicaImplException;
	public ArrastrameTrip buscarTipoViaje(TipoViaje objTipoViaje) throws LogicaImplException;
	public ArrastrameTrip listarTodoTipoViaje() throws LogicaImplException;
	
	public ArrastrameTrip crearTipoStatusViaje(TipoStatusViaje objTipoStatusViaje) throws LogicaImplException;
	public ArrastrameTrip actualizarTipoStatusViaje(Integer id, TipoStatusViaje objTipoStatusViaje) throws LogicaImplException;
	public ArrastrameTrip buscarTipoStatusViaje(TipoStatusViaje objTipoStatusViaje) throws LogicaImplException;
	public ArrastrameTrip listarTodoTipoStatusViaje() throws LogicaImplException;
	
	public ArrastrameTrip crearClasificacionPasajero(ClasificacionPasajero objClasificacionPasajero) throws LogicaImplException;
	public ArrastrameTrip actualizarClasificacionPasajero(Integer id, ClasificacionPasajero objClasificacionPasajero) throws LogicaImplException;
	public ArrastrameTrip buscarClasificacionPasajero(ClasificacionPasajero objClasificacionPasajero) throws LogicaImplException;
	public ArrastrameTrip listarTodoClasificacionPasajero() throws LogicaImplException;
	
	public ArrastrameTrip crearRolPasajero(RolPasajero objRolPasajero) throws LogicaImplException;
	public ArrastrameTrip actualizarRolPasajero(Integer id, RolPasajero objRolPasajero) throws LogicaImplException;
	public ArrastrameTrip buscarRolPasajero(RolPasajero objRolPasajero) throws LogicaImplException;
	public ArrastrameTrip listarTodoRolPasajero() throws LogicaImplException;
	
	public ArrastrameTrip crearDescuento(Descuento objDescuento) throws LogicaImplException;
	public ArrastrameTrip actualizarDescuento(Integer id, Descuento objDescuento) throws LogicaImplException;
	public ArrastrameTrip buscarDescuentoxCodigoDescuento(Descuento objDescuento) throws LogicaImplException;
	public ArrastrameTrip listarTodoDescuento() throws LogicaImplException;
	public ArrastrameTrip listarDescuentoxFechaFinDescuento(String fechaFinDescuentoA, String fechaFinDescuentoB) throws LogicaImplException;
	
	public ArrastrameTrip crearPasajeroArrastrame(PasajeroArrastrame objPasajeroArrastrame) throws LogicaImplException;
	public ArrastrameTrip actualizarPasajeroArrastrame(Integer id, PasajeroArrastrame objPasajeroArrastrame) throws LogicaImplException;
	public ArrastrameTrip buscarPasajeroArrastramexIdUsuario(PasajeroArrastrame objPasajeroArrastrame) throws LogicaImplException;
	public ArrastrameTrip listarTodoPasajeroArrastrame() throws LogicaImplException;
	
	public ArrastrameTrip generarCodigoViaje(Viaje objViaje) throws LogicaImplException;
	public ArrastrameTrip crearViaje(String idPasajeroArrastrame, Viaje objViaje) throws LogicaImplException;
//	public ArrastrameTrip crearViaje(String idPasajeroArrastrame, Date dateViaje, Viaje objViaje) throws LogicaImplException;
	public ArrastrameTrip actualizarViaje(Integer id, Viaje objViaje) throws LogicaImplException;
	public ArrastrameTrip buscarViajexCodigoViaje(Viaje objViaje) throws LogicaImplException;
	public ArrastrameTrip buscarViajexTimeInicioMismoInstanteViaje(String fechaViaje) throws LogicaImplException;
	public ArrastrameTrip listarTodoViaje() throws LogicaImplException;
	
	public ArrastrameTrip crearStatusViaje(StatusViaje objStatusViaje) throws LogicaImplException;
	public ArrastrameTrip listarStatusViajexIdViaje(StatusViaje objStatusViaje) throws LogicaImplException;
	
	public ArrastrameTrip crearDescuentoViaje(DescuentoViaje objDescuentoViaje) throws LogicaImplException;
	public ArrastrameTrip buscarDescuentoViajexIdDescuentoxIdViaje(DescuentoViaje objDescuentoViaje) throws LogicaImplException;
	public ArrastrameTrip eliminarDescuentoViaje(Integer idUsuario, DescuentoViaje objDescuentoViaje) throws LogicaImplException;
	public ArrastrameTrip listarDescuentoViajexidViaje(DescuentoViaje objDescuentoViaje) throws LogicaImplException;
	
	public ArrastrameTrip crearViajeRecomendado(ViajeRecomendado objViajeRecomendado) throws LogicaImplException;
	public ArrastrameTrip buscarViajeRecomendadoxIdViaje(ViajeRecomendado objViajeRecomendado) throws LogicaImplException;
	public ArrastrameTrip listarViajeRecomendadoxidTipoViajeRecomendado(ViajeRecomendado objViajeRecomendado) throws LogicaImplException;
	
	public ArrastrameTrip crearPasajeroArrastrameViaje(PasajeroArrastrameViaje objPasajeroArrastrameViaje) throws LogicaImplException;
	public ArrastrameTrip actualizarPasajeroArrastrameViaje(Integer idPasajero, PasajeroArrastrameViaje objPasajeroArrastrameViaje) throws LogicaImplException;
	public ArrastrameTrip eliminarPasajeroArrastrameViaje(Integer idPasajero, PasajeroArrastrameViaje objPasajeroArrastrameViaje) throws LogicaImplException;
	public ArrastrameTrip listarPasajeroArrastrameViajexidViaje(PasajeroArrastrameViaje objPasajeroArrastrameViaje) throws LogicaImplException;
	
	public void limpiarCacheLocal();
	
}
