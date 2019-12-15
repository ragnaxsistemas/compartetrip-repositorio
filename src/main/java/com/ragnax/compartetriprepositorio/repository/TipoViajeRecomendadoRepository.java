package com.ragnax.compartetriprepositorio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.compartetriprepositorio.entidad.TipoViajeRecomendado;

public interface TipoViajeRecomendadoRepository extends JpaRepository<TipoViajeRecomendado, Integer> {
	
	@Query("select tvr from TipoViajeRecomendado tvr where tvr.nombreTipoViajeRecomendado = :nombreTipoViajeRecomendado")
	Page<TipoViajeRecomendado> findByNombreTipoViajeRecomendado(String nombreTipoViajeRecomendado, Pageable page);
}
