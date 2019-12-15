package com.ragnax.compartetriprepositorio.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.compartetriprepositorio.entidad.ClasificacionPasajero;


public interface ClasificacionPasajeroRepository extends JpaRepository<ClasificacionPasajero, Integer> {
	
	@Query("select cp from ClasificacionPasajero cp where cp.nombreClasificacionPasajero = :nombreClasificacionPasajero")
	Page<ClasificacionPasajero> findByNombreClasificacionPasajero(String nombreClasificacionPasajero, Pageable page);
	
	
	
}
