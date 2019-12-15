package com.ragnax.compartetriprepositorio.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.compartetriprepositorio.entidad.PasajeroArrastrameViaje;
import com.ragnax.compartetriprepositorio.entidad.Viaje;

public interface PasajeroArrastrameViajeRepository extends JpaRepository<PasajeroArrastrameViaje, Integer> {
	
	@Query("select cp from PasajeroArrastrameViaje cp where cp.idViaje = :idViaje")
	Page<PasajeroArrastrameViaje> findByIdViaje(Viaje idViaje, Pageable page);
	
}
