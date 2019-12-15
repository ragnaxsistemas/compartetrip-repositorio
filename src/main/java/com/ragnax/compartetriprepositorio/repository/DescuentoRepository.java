package com.ragnax.compartetriprepositorio.repository;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.compartetriprepositorio.entidad.Descuento;

public interface DescuentoRepository extends JpaRepository<Descuento, Integer> {
	
	@Query("select sn from Descuento sn where sn.codigoDescuento = :codigoDescuento")
	Page<Descuento> findByCodigoDescuento(String codigoDescuento, Pageable page);
	
	List<Descuento> findByFechaFinDescuentoBetween(Timestamp fechaFinDescuentoA, Timestamp fechaFinDescuentoB);
	
}
