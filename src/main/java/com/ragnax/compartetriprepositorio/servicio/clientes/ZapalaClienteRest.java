package com.ragnax.compartetriprepositorio.servicio.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.ragnax.compartetriprepositorio.servicio.clientes.modelo.Zapala;
import com.ragnax.compartetriprepositorio.servicio.clientes.modelo.ZapalaRequest;

@FeignClient(name = "ragnax-zapala" , url = "localhost:8081")
public interface ZapalaClienteRest {
	
	@PostMapping("/generar-tiempo-duracion")
	public Zapala generarTiempoDuracion(ZapalaRequest zapalaRequest);
	
	@PostMapping("/convertir-strfechaconformat-to-timestamp")
	public Zapala convertirStrFechaConFormatToTimestamp(ZapalaRequest zapalaRequest);
	
	@PostMapping("/generar-codigo-numero")
	public Zapala generarCodigoByNumero(ZapalaRequest zapalaRequest);

	@PostMapping("/generar-codigo-numero-encodear")
	public Zapala generarCodigoByNumeroByEncodear(ZapalaRequest zapalaRequest);
	
	@PostMapping("/generar-patron-rut")
	public Zapala generarPatronRUT(ZapalaRequest zapalaRequest);

}
