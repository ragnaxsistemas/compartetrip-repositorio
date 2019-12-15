package com.ragnax.compartetriprepositorio.repository;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.compartetriprepositorio.entidad.Viaje;

public interface ViajeRepository extends JpaRepository<Viaje, Integer> {
	
	@Query("select v from Viaje v where v.codigoViaje = :codigoViaje")
	Page<Viaje> findByCodigoViaje(String codigoViaje, Pageable page);
	
	List<Viaje> findByTimeInicioViajeBetween(Timestamp timeInicioViajeA, Timestamp timeInicioViajeB);
}