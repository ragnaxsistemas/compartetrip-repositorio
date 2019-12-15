package com.ragnax.compartetriprepositorio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.compartetriprepositorio.entidad.RolPasajero;

public interface RolPasajeroRepository extends JpaRepository<RolPasajero, Integer> {
	
	@Query("select cp from RolPasajero cp where cp.nombreRolPasajero = :nombreRolPasajero")
	Page<RolPasajero> findByNombreRolPasajero(String nombreRolPasajero, Pageable page);
	
}
