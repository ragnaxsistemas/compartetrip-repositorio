package com.ragnax.compartetriprepositorio.repository;

public interface FactoryArrastrameTripDAO {
	
	public TipoVehiculoViajeRepository	getTipoVehiculoViajeRepository();	
	public TipoViajeRepository getTipoViajeRepository();
	public TipoViajeRecomendadoRepository getTipoViajeRecomendadoRepository();
	public TipoStatusViajeRepository getTipoStatusViajeRepository();
	public ClasificacionPasajeroRepository getClasificacionPasajeroRepository();
	public RolPasajeroRepository getRolPasajeroRepository();
	public DescuentoRepository getDescuentoRepository();
	public ViajeRepository getViajeRepository();
	public PasajeroArrastrameRepository getPasajeroArrastrameRepository();
	public StatusViajeRepository getStatusViajeRepository();
	public ViajeRecomendadoRepository getViajeRecomendadoRepository();
	public DescuentoViajeRepository getDescuentoViajeRepository();
	public PasajeroArrastrameViajeRepository getPasajeroArrastrameViajeRepository();
	
}
