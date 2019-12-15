package com.ragnax.compartetriprepositorio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.compartetriprepositorio.entidad.TipoStatusViaje;


public interface TipoStatusViajeRepository extends JpaRepository<TipoStatusViaje, Integer> {
	
	@Query("select tva from TipoStatusViaje tva where tva.nombreTipoStatusViaje = :nombreTipoStatusViaje")
	Page<TipoStatusViaje> findByNombreTipoStatusViaje(String nombreTipoStatusViaje, Pageable page);
	
}
