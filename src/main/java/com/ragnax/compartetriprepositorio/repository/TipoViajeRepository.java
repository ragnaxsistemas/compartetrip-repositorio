package com.ragnax.compartetriprepositorio.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.compartetriprepositorio.entidad.TipoViaje;


public interface TipoViajeRepository extends JpaRepository<TipoViaje, Integer> {
	
	
	@Query("select tva from TipoViaje tva where tva.nombreTipoViaje = :nombreTipoViaje")
	Page<TipoViaje> findByNombreTipoViaje(String nombreTipoViaje, Pageable page);
	
	
}
