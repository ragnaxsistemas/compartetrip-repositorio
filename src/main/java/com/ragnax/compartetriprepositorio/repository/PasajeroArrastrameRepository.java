package com.ragnax.compartetriprepositorio.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.compartetriprepositorio.entidad.PasajeroArrastrame;

public interface PasajeroArrastrameRepository extends JpaRepository<PasajeroArrastrame, Integer> {
	
	@Query("select cp from PasajeroArrastrame cp where cp.idUsuario = :idUsuario")
	Page<PasajeroArrastrame> findByIdUsuario(Integer idUsuario, Pageable page);
	
}
