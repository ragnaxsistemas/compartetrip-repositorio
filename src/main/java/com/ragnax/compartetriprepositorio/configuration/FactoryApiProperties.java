package com.ragnax.compartetriprepositorio.configuration;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:factoryapi.properties")
@ConfigurationProperties(prefix = "factory")
/****Properties que pueden cambiar el valor ****/
public class FactoryApiProperties {
	
	private Configdata configdata;
	private Cache cache;
	
    public static class Configdata {
    	
private String fechayyyyMMddTHHmmssZ;
    	
    	@NotBlank
		private String estadoActivoConsulta;
    	
    	@NotBlank
		private String estadoInactivoConsulta;

		public String getEstadoActivoConsulta() {
			return estadoActivoConsulta;
		}
		
		public void setEstadoActivoConsulta(String estadoActivoConsulta) {
			this.estadoActivoConsulta = estadoActivoConsulta;
		}
		
		public String getEstadoInactivoConsulta() {
			return estadoInactivoConsulta;
		}
		
		public void setEstadoInactivoConsulta(String estadoInactivoConsulta) {
			this.estadoInactivoConsulta = estadoInactivoConsulta;
		}
		
		public String getFechayyyyMMddTHHmmssZ() {
			return fechayyyyMMddTHHmmssZ;
		}
		
		public void setFechayyyyMMddTHHmmssZ(String fechayyyyMMddTHHmmssZ) {
			this.fechayyyyMMddTHHmmssZ = fechayyyyMMddTHHmmssZ;
		}
    }
	
    public static class Cache {
		
    	private String evictBuscarTipoViajeRecomendado;
    	private String evictListarTodoTipoViajeRecomendado;
    	private String evictBuscarTipoVehiculoViaje;
    	private String evictListarTodoTipoVehiculoViaje;
    	private String evictBuscarTipoViaje;
    	private String evictListarTodoTipoViaje;
	    private String evictBuscarTipoStatusViaje;
	    private String evictListarTodoTipoStatusViaje;
	    private String evictBuscarClasificacionPasajero;
	    private String evictListarTodoClasificacionPasajero;
	    private String evictBuscarRolPasajero;
	    private String evictListarTodoRolPasajero;
	    private String evictBuscarDescuentoxCodigoDescuento; 
	    private String evictBuscarPasajeroArrastramexIdUsuario;
		public String getEvictBuscarTipoViajeRecomendado() {
			return evictBuscarTipoViajeRecomendado;
		}
		public void setEvictBuscarTipoViajeRecomendado(String evictBuscarTipoViajeRecomendado) {
			this.evictBuscarTipoViajeRecomendado = evictBuscarTipoViajeRecomendado;
		}
		public String getEvictListarTodoTipoViajeRecomendado() {
			return evictListarTodoTipoViajeRecomendado;
		}
		public void setEvictListarTodoTipoViajeRecomendado(String evictListarTodoTipoViajeRecomendado) {
			this.evictListarTodoTipoViajeRecomendado = evictListarTodoTipoViajeRecomendado;
		}
		public String getEvictBuscarTipoVehiculoViaje() {
			return evictBuscarTipoVehiculoViaje;
		}
		public void setEvictBuscarTipoVehiculoViaje(String evictBuscarTipoVehiculoViaje) {
			this.evictBuscarTipoVehiculoViaje = evictBuscarTipoVehiculoViaje;
		}
		public String getEvictListarTodoTipoVehiculoViaje() {
			return evictListarTodoTipoVehiculoViaje;
		}
		public void setEvictListarTodoTipoVehiculoViaje(String evictListarTodoTipoVehiculoViaje) {
			this.evictListarTodoTipoVehiculoViaje = evictListarTodoTipoVehiculoViaje;
		}
		public String getEvictBuscarTipoViaje() {
			return evictBuscarTipoViaje;
		}
		public void setEvictBuscarTipoViaje(String evictBuscarTipoViaje) {
			this.evictBuscarTipoViaje = evictBuscarTipoViaje;
		}
		public String getEvictListarTodoTipoViaje() {
			return evictListarTodoTipoViaje;
		}
		public void setEvictListarTodoTipoViaje(String evictListarTodoTipoViaje) {
			this.evictListarTodoTipoViaje = evictListarTodoTipoViaje;
		}
		public String getEvictBuscarTipoStatusViaje() {
			return evictBuscarTipoStatusViaje;
		}
		public void setEvictBuscarTipoStatusViaje(String evictBuscarTipoStatusViaje) {
			this.evictBuscarTipoStatusViaje = evictBuscarTipoStatusViaje;
		}
		public String getEvictListarTodoTipoStatusViaje() {
			return evictListarTodoTipoStatusViaje;
		}
		public void setEvictListarTodoTipoStatusViaje(String evictListarTodoTipoStatusViaje) {
			this.evictListarTodoTipoStatusViaje = evictListarTodoTipoStatusViaje;
		}
		public String getEvictBuscarClasificacionPasajero() {
			return evictBuscarClasificacionPasajero;
		}
		public void setEvictBuscarClasificacionPasajero(String evictBuscarClasificacionPasajero) {
			this.evictBuscarClasificacionPasajero = evictBuscarClasificacionPasajero;
		}
		public String getEvictListarTodoClasificacionPasajero() {
			return evictListarTodoClasificacionPasajero;
		}
		public void setEvictListarTodoClasificacionPasajero(String evictListarTodoClasificacionPasajero) {
			this.evictListarTodoClasificacionPasajero = evictListarTodoClasificacionPasajero;
		}
		public String getEvictBuscarRolPasajero() {
			return evictBuscarRolPasajero;
		}
		public void setEvictBuscarRolPasajero(String evictBuscarRolPasajero) {
			this.evictBuscarRolPasajero = evictBuscarRolPasajero;
		}
		public String getEvictListarTodoRolPasajero() {
			return evictListarTodoRolPasajero;
		}
		public void setEvictListarTodoRolPasajero(String evictListarTodoRolPasajero) {
			this.evictListarTodoRolPasajero = evictListarTodoRolPasajero;
		}
		public String getEvictBuscarDescuentoxCodigoDescuento() {
			return evictBuscarDescuentoxCodigoDescuento;
		}
		public void setEvictBuscarDescuentoxCodigoDescuento(String evictBuscarDescuentoxCodigoDescuento) {
			this.evictBuscarDescuentoxCodigoDescuento = evictBuscarDescuentoxCodigoDescuento;
		}
		public String getEvictBuscarPasajeroArrastramexIdUsuario() {
			return evictBuscarPasajeroArrastramexIdUsuario;
		}
		public void setEvictBuscarPasajeroArrastramexIdUsuario(String evictBuscarPasajeroArrastramexIdUsuario) {
			this.evictBuscarPasajeroArrastramexIdUsuario = evictBuscarPasajeroArrastramexIdUsuario;
		}
    	
    }

	public Configdata getConfigdata() {
		return configdata;
	}

	public void setConfigdata(Configdata configdata) {
		this.configdata = configdata;
	}

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

}