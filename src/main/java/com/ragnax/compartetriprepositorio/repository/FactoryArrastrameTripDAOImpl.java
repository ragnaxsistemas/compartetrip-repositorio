package com.ragnax.compartetriprepositorio.repository;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Repository;

@Repository
public class FactoryArrastrameTripDAOImpl implements FactoryArrastrameTripDAO {
	
	@Autowired
	private TipoVehiculoViajeRepository tipoVehiculoViajeRepository;	
	
	public TipoVehiculoViajeRepository getTipoVehiculoViajeRepository() {
		return tipoVehiculoViajeRepository;
	}
	
	@Autowired
	private TipoViajeRepository tipoViajeRepository;	
	
	public TipoViajeRepository getTipoViajeRepository() {
		return tipoViajeRepository;
	}
	
	@Autowired
	private TipoViajeRecomendadoRepository tipoViajeRecomendadoRepository;	
	
	public TipoViajeRecomendadoRepository getTipoViajeRecomendadoRepository() {
		return tipoViajeRecomendadoRepository;
	}
	
	@Autowired
	private TipoStatusViajeRepository tipoStatusViajeRepository;	
	
	public TipoStatusViajeRepository getTipoStatusViajeRepository() {
		return tipoStatusViajeRepository;
	}
	
	@Autowired
	private ClasificacionPasajeroRepository clasificacionPasajeroRepository;	
	
	public ClasificacionPasajeroRepository getClasificacionPasajeroRepository() {
		return clasificacionPasajeroRepository;
	}

	@Autowired
	private RolPasajeroRepository rolPasajeroRepository;	
	
	public RolPasajeroRepository getRolPasajeroRepository() {
		return rolPasajeroRepository;
	}
	
	@Autowired
	private DescuentoRepository descuentoRepository;	
	
	public DescuentoRepository getDescuentoRepository() {
		return descuentoRepository;
	}
	
	@Autowired
	private ViajeRepository viajeRepository;	
	
	public ViajeRepository getViajeRepository() {
		return viajeRepository;
	}
	
	@Autowired
	private PasajeroArrastrameRepository pasajeroArrastrameRepository;	
	
	public PasajeroArrastrameRepository getPasajeroArrastrameRepository() {
		return pasajeroArrastrameRepository;
	}
	
	@Autowired
	private StatusViajeRepository statusViajeRepository;	
	
	public StatusViajeRepository getStatusViajeRepository() {
		return statusViajeRepository;
	}
	
	@Autowired
	private ViajeRecomendadoRepository viajeRecomendadoRepository;	
	
	public ViajeRecomendadoRepository getViajeRecomendadoRepository() {
		return viajeRecomendadoRepository;
	}
	
	@Autowired
	private DescuentoViajeRepository descuentoViajeRepository;	
	
	public DescuentoViajeRepository getDescuentoViajeRepository() {
		return descuentoViajeRepository;
	}
	
	@Autowired
	private PasajeroArrastrameViajeRepository pasajeroArrastrameViajeRepository;	
	
	public PasajeroArrastrameViajeRepository getPasajeroArrastrameViajeRepository() {
		return pasajeroArrastrameViajeRepository;
	}


	
	
	

	
	
	
	
}
