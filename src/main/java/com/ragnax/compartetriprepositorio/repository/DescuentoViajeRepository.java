package com.ragnax.compartetriprepositorio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.compartetriprepositorio.entidad.Descuento;
import com.ragnax.compartetriprepositorio.entidad.DescuentoViaje;
import com.ragnax.compartetriprepositorio.entidad.Viaje;

public interface DescuentoViajeRepository extends JpaRepository<DescuentoViaje, Integer> {
	
	
	@Query("select dv from DescuentoViaje dv where dv.idDescuento = :idDescuento")
	Page<DescuentoViaje> findByIdDescuento(Descuento idDescuento, Pageable page);
	
	@Query("select dv from DescuentoViaje dv where dv.idViaje = :idViaje")
	Page<DescuentoViaje> findByIdViaje(Viaje idViaje, Pageable page);
	
	
	DescuentoViaje findByIdDescuentoAndIdViaje(Descuento idDescuento, Viaje idViaje);
}
