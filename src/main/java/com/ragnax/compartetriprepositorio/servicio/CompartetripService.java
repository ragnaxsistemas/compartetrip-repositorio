package com.ragnax.compartetriprepositorio.servicio;

import java.util.List;

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

public interface CompartetripService {
	
	public TipoViajeRecomendado crearTipoViajeRecomendado(TipoViajeRecomendado objTipoViajeRecomendado) throws LogicaImplException;
	public TipoViajeRecomendado actualizarTipoViajeRecomendado(Integer id, TipoViajeRecomendado objTipoViajeRecomendado) throws LogicaImplException;
	public TipoViajeRecomendado buscarTipoViajeRecomendado(TipoViajeRecomendado objTipoViajeRecomendado) throws LogicaImplException;
	public List<TipoViajeRecomendado> listarTodoTipoViajeRecomendado() throws LogicaImplException;
	
	public TipoVehiculoViaje crearTipoVehiculoViaje(TipoVehiculoViaje objTipoVehiculoViaje) throws LogicaImplException;
	public TipoVehiculoViaje actualizarTipoVehiculoViaje(Integer id, TipoVehiculoViaje objTipoVehiculoViaje) throws LogicaImplException;
	public TipoVehiculoViaje buscarTipoVehiculoViaje(TipoVehiculoViaje objTipoVehiculoViaje) throws LogicaImplException;
	public List<TipoVehiculoViaje> listarTodoTipoVehiculoViaje() throws LogicaImplException;
	
	public TipoViaje crearTipoViaje(TipoViaje objTipoViaje) throws LogicaImplException;
	public TipoViaje actualizarTipoViaje(Integer id, TipoViaje objTipoViaje) throws LogicaImplException;
	public TipoViaje buscarTipoViaje(TipoViaje objTipoViaje) throws LogicaImplException;
	public List<TipoViaje> listarTodoTipoViaje() throws LogicaImplException;
	
	public TipoStatusViaje crearTipoStatusViaje(TipoStatusViaje objTipoStatusViaje) throws LogicaImplException;
	public TipoStatusViaje actualizarTipoStatusViaje(Integer id, TipoStatusViaje objTipoStatusViaje) throws LogicaImplException;
	public TipoStatusViaje buscarTipoStatusViaje(TipoStatusViaje objTipoStatusViaje) throws LogicaImplException;
	public List<TipoStatusViaje> listarTodoTipoStatusViaje() throws LogicaImplException;
	
	public ClasificacionPasajero crearClasificacionPasajero(ClasificacionPasajero objClasificacionPasajero) throws LogicaImplException;
	public ClasificacionPasajero actualizarClasificacionPasajero(Integer id, ClasificacionPasajero objClasificacionPasajero) throws LogicaImplException;
	public ClasificacionPasajero buscarClasificacionPasajero(ClasificacionPasajero objClasificacionPasajero) throws LogicaImplException;
	public List<ClasificacionPasajero> listarTodoClasificacionPasajero() throws LogicaImplException;
	
	public RolPasajero crearRolPasajero(RolPasajero objRolPasajero) throws LogicaImplException;
	public RolPasajero actualizarRolPasajero(Integer id, RolPasajero objRolPasajero) throws LogicaImplException;
	public RolPasajero buscarRolPasajero(RolPasajero objRolPasajero) throws LogicaImplException;
	public List<RolPasajero> listarTodoRolPasajero() throws LogicaImplException;
	
	public Descuento crearDescuento(Descuento objDescuento) throws LogicaImplException;
	public Descuento actualizarDescuento(Integer id, Descuento objDescuento) throws LogicaImplException;
	public Descuento buscarDescuentoxCodigoDescuento(Descuento objDescuento) throws LogicaImplException;
	public List<Descuento> listarTodoDescuento() throws LogicaImplException;
	public List<Descuento> listarDescuentoxFechaFinDescuento(String fechaFinDescuentoA, String fechaFinDescuentoB) throws LogicaImplException;
	
	public PasajeroArrastrame crearPasajeroArrastrame(PasajeroArrastrame objPasajeroArrastrame) throws LogicaImplException;
	public PasajeroArrastrame actualizarPasajeroArrastrame(Integer id, PasajeroArrastrame objPasajeroArrastrame) throws LogicaImplException;
	public PasajeroArrastrame buscarPasajeroArrastramexIdUsuario(PasajeroArrastrame objPasajeroArrastrame) throws LogicaImplException;
	public List<PasajeroArrastrame> listarTodoPasajeroArrastrame() throws LogicaImplException;
	
	public Viaje generarCodigoViaje(Viaje objViaje) throws LogicaImplException;
	public Viaje crearViaje(Integer idPasajeroArrastrame, Viaje objViaje) throws LogicaImplException;
//	public ArrastrameTrip crearViaje(String idPasajeroArrastrame, Date dateViaje, Viaje objViaje) throws LogicaImplException;
	public Viaje actualizarViaje(Integer id, Viaje objViaje) throws LogicaImplException;
	public Viaje buscarViajexCodigoViaje(Viaje objViaje) throws LogicaImplException;
	public List<Viaje> listarViajexTimeInicioMismoInstanteViaje(String fechaViaje) throws LogicaImplException;
	public List<Viaje> listarTodoViaje() throws LogicaImplException;
	
	public StatusViaje crearStatusViaje(StatusViaje objStatusViaje) throws LogicaImplException;
	public List<StatusViaje> listarStatusViajexIdViaje(StatusViaje objStatusViaje) throws LogicaImplException;
	
	public DescuentoViaje crearDescuentoViaje(DescuentoViaje objDescuentoViaje) throws LogicaImplException;
	public DescuentoViaje buscarDescuentoViajexIdDescuentoxIdViaje(DescuentoViaje objDescuentoViaje) throws LogicaImplException;
	public DescuentoViaje eliminarDescuentoViaje(Integer idUsuario, DescuentoViaje objDescuentoViaje) throws LogicaImplException;
	public List<DescuentoViaje> listarDescuentoViajexidViaje(DescuentoViaje objDescuentoViaje) throws LogicaImplException;
	
	public ViajeRecomendado crearViajeRecomendado(ViajeRecomendado objViajeRecomendado) throws LogicaImplException;
	public ViajeRecomendado buscarViajeRecomendadoxIdViaje(ViajeRecomendado objViajeRecomendado) throws LogicaImplException;
	public List<ViajeRecomendado> listarViajeRecomendadoxidTipoViajeRecomendado(ViajeRecomendado objViajeRecomendado) throws LogicaImplException;
	
	public PasajeroArrastrameViaje crearPasajeroArrastrameViaje(PasajeroArrastrameViaje objPasajeroArrastrameViaje) throws LogicaImplException;
	public PasajeroArrastrameViaje actualizarPasajeroArrastrameViaje(Integer idPasajero, PasajeroArrastrameViaje objPasajeroArrastrameViaje) throws LogicaImplException;
	public PasajeroArrastrameViaje eliminarPasajeroArrastrameViaje(Integer idPasajero, PasajeroArrastrameViaje objPasajeroArrastrameViaje) throws LogicaImplException;
	public List<PasajeroArrastrameViaje> listarPasajeroArrastrameViajexidViaje(PasajeroArrastrameViaje objPasajeroArrastrameViaje) throws LogicaImplException;
	
	public void limpiarCacheLocal();
	
}
