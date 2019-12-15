package com.ragnax.compartetriprepositorio.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.compartetriprepositorio.entidad.TipoViajeRecomendado;
import com.ragnax.compartetriprepositorio.entidad.Viaje;
import com.ragnax.compartetriprepositorio.entidad.ViajeRecomendado;

public interface ViajeRecomendadoRepository extends JpaRepository<ViajeRecomendado, Integer> {
	
	@Query("select tva from ViajeRecomendado tva where tva.idViaje = :idViaje")
	Page<ViajeRecomendado> findByIdViaje(Viaje idViaje, Pageable page);
	
	List<ViajeRecomendado> findByIdTipoViajeRecomendado(TipoViajeRecomendado idTipoViajeRecomendado);
}
