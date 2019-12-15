package com.ragnax.compartetriprepositorio.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.compartetriprepositorio.entidad.StatusViaje;
import com.ragnax.compartetriprepositorio.entidad.Viaje;


public interface StatusViajeRepository extends JpaRepository<StatusViaje, Integer> {

	List<StatusViaje> findByIdViaje(Viaje idViaje);
	
	@Query("select sn from StatusViaje sn where sn.idViaje = :idViaje")
	Page<StatusViaje> findByIdViaje(Viaje idViaje, Pageable page);
	
}
