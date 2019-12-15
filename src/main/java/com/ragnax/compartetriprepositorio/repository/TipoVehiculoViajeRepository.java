package com.ragnax.compartetriprepositorio.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.compartetriprepositorio.entidad.TipoVehiculoViaje;


public interface TipoVehiculoViajeRepository extends JpaRepository<TipoVehiculoViaje, Integer> {
	
	
	@Query("select tvc from TipoVehiculoViaje tvc where tvc.nombreTipoVehiculoViaje = :nombreTipoVehiculoViaje")
	Page<TipoVehiculoViaje> findByNombreTipoVehiculoViaje(String nombreTipoVehiculoViaje, Pageable page);
	
}
