package com.ragnax.compartetriprepositorio.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ragnax.compartetriprepositorio.controller.response.RagnaxError;
import com.ragnax.compartetriprepositorio.entidad.ClasificacionPasajero;
import com.ragnax.compartetriprepositorio.entidad.Descuento;
import com.ragnax.compartetriprepositorio.entidad.DescuentoViaje;
import com.ragnax.compartetriprepositorio.entidad.PasajeroArrastrame;
import com.ragnax.compartetriprepositorio.entidad.PasajeroArrastrameViaje;
import com.ragnax.compartetriprepositorio.entidad.RolPasajero;
import com.ragnax.compartetriprepositorio.entidad.StatusViaje;
import com.ragnax.compartetriprepositorio.entidad.TipoStatusViaje;
import com.ragnax.compartetriprepositorio.entidad.TipoVehiculoViaje;
import com.ragnax.compartetriprepositorio.entidad.TipoViaje;
import com.ragnax.compartetriprepositorio.entidad.TipoViajeRecomendado;
import com.ragnax.compartetriprepositorio.entidad.Viaje;
import com.ragnax.compartetriprepositorio.entidad.ViajeRecomendado;
import com.ragnax.compartetriprepositorio.exception.LogicaImplException;
import com.ragnax.compartetriprepositorio.servicio.CompartetripService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

@RestController
@RequestMapping
public class ArrastrameTripController {
	
	/****@GetMapping  no soporta Errors****/
	@Autowired
	CompartetripService arrastrameTripService;

	@GetMapping(value = "${servicio.app.url.limpiarCache}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void clearAllCaches() {
		arrastrameTripService.limpiarCacheLocal();
	}
	
	/***************************************************/
	/*************** TipoViajeRecomendado *** *******************/
	/***************************************************/
	@ApiOperation(value = "Crear Tipo de Viaje Recomendado", response = TipoViajeRecomendado.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoViajeRecomendado.class)
	})
	@PostMapping(value = "${servicio.app.url.crearTipoViajeRecomendado}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoViajeRecomendado>  crearTipoViajeRecomendado(  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid TipoViajeRecomendado objTipoViajeRecomendado, @ApiIgnore Errors errors)  throws LogicaImplException{

		
		
		return new ResponseEntity<>(arrastrameTripService.crearTipoViajeRecomendado(
				objTipoViajeRecomendado), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualizar Tipo de Viaje Recomendado", response = TipoViajeRecomendado.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoViajeRecomendado.class)
	})
	@PutMapping(value = "${servicio.app.url.actualizarTipoViajeRecomendado}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoViajeRecomendado>  actualizarTipoViajeRecomendado(
			  @ApiParam(value = "objeto de entrada", required = true) @RequestBody @Valid TipoViajeRecomendado objTipoViajeRecomendado,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id, 
			@ApiIgnore Errors errors)  throws LogicaImplException{

		

		return new ResponseEntity<>(arrastrameTripService.actualizarTipoViajeRecomendado(
				Integer.parseInt(id), objTipoViajeRecomendado) , HttpStatus.OK);
	}

	@ApiOperation(value = "Buscar Tipo de Viaje Recomendado", response = TipoViajeRecomendado.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de busqueda", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoViajeRecomendado.class)
	})
	@GetMapping(value = "${servicio.app.url.buscarTipoViajeRecomendado}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoViajeRecomendado>  buscarTipoViajeRecomendado(  @ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id)  throws LogicaImplException{

		

		return new ResponseEntity<>(arrastrameTripService.buscarTipoViajeRecomendado(
				new TipoViajeRecomendado(Integer.parseInt(id))),  HttpStatus.OK);
	}

	@ApiOperation(value = "Listar Todos los Tipo de Viaje Recomendado", response = TipoViajeRecomendado.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoViajeRecomendado.class)
	})

	@GetMapping(value = "${servicio.app.url.listarTodoTipoViajeRecomendado}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TipoViajeRecomendado>>  listarTodoTipoViajeRecomendado()  throws LogicaImplException{

		

		return new ResponseEntity<>(arrastrameTripService.listarTodoTipoViajeRecomendado(), HttpStatus.OK);

	}
	
	/***************************************************/
	/*************** TipoVehiculoViaje *****************/
	/***************************************************/
	
	@ApiOperation(value = "Crear Tipo de Vehiculo para el Viaje", response = TipoVehiculoViaje.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoVehiculoViaje.class)
	})
	@PostMapping(value = "${servicio.app.url.crearTipoVehiculoViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoVehiculoViaje>  crearTipoVehiculoViaje(  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid TipoVehiculoViaje objTipoVehiculoViaje, @ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.crearTipoVehiculoViaje(objTipoVehiculoViaje), 
				HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualizar Tipo de Vehiculo para el Viaje", response = TipoVehiculoViaje.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoVehiculoViaje.class)
	})
	@PutMapping(value = "${servicio.app.url.actualizarTipoVehiculoViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoVehiculoViaje>  actualizarTipoVehiculoViaje(
			  @ApiParam(value = "objeto de entrada", required = true) @RequestBody @Valid TipoVehiculoViaje objTipoVehiculoViaje,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id, 
			@ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.actualizarTipoVehiculoViaje(
				Integer.parseInt(id), objTipoVehiculoViaje), HttpStatus.OK);
	}

	@ApiOperation(value = "Buscar Tipo de Vehiculo para el Viaje", response = TipoVehiculoViaje.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoVehiculoViaje.class)
	})
	@GetMapping(value = "${servicio.app.url.buscarTipoVehiculoViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoVehiculoViaje>  buscarTipoVehiculoViaje(  @ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.buscarTipoVehiculoViaje(
				new TipoVehiculoViaje(Integer.parseInt(id))), HttpStatus.OK);
	}

	@ApiOperation(value = "Listar Todos los Tipo de Vehiculo para el Viaje", response = RagnaxError.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = RagnaxError.class)
	})

	@GetMapping(value = "${servicio.app.url.listarTodoTipoVehiculoViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TipoVehiculoViaje>>  listarTodoTipoVehiculoViaje()  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.listarTodoTipoVehiculoViaje(), HttpStatus.OK);

	}
	/***************************************************/
	/*************** TipoViaje *****************/
	/***************************************************/
	@ApiOperation(value = "Crear Tipo de Viaje", response = TipoViaje.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoViaje.class)
	})
	@PostMapping(value = "${servicio.app.url.crearTipoViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoViaje>  crearTipoViaje(  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid TipoViaje objTipoViaje, @ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.crearTipoViaje(objTipoViaje), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualizar Tipo de Vehiculo para el Viaje", response = TipoViaje.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoViaje.class)
	})
	@PutMapping(value = "${servicio.app.url.actualizarTipoViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoViaje>  actualizarTipoViaje(
			  @ApiParam(value = "objeto de entrada", required = true) @RequestBody @Valid TipoViaje objTipoViaje,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id, 
			@ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.actualizarTipoViaje(Integer.parseInt(id), objTipoViaje), HttpStatus.OK);
	}

	@ApiOperation(value = "Buscar Tipo de Vehiculo para el Viaje", response = TipoViaje.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoViaje.class)
	})
	@GetMapping(value = "${servicio.app.url.buscarTipoViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoViaje>  buscarTipoViaje(  @ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.buscarTipoViaje(
				new TipoViaje(Integer.parseInt(id))), HttpStatus.OK);
	}

	@ApiOperation(value = "Listar Todos los Tipo de Vehiculo para el Viaje", response = TipoViaje.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de busqueda", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoViaje.class)
	})

	@GetMapping(value = "${servicio.app.url.listarTodoTipoViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TipoViaje>>  listarTodoTipoViaje()  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.listarTodoTipoViaje(), HttpStatus.OK);

	}
	
	@ApiOperation(value = "Crear Tipo de Status de Viaje", response = TipoStatusViaje.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoStatusViaje.class)
	})
	@PostMapping(value = "${servicio.app.url.crearTipoStatusViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoStatusViaje>  crearTipoStatusViaje(  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid TipoStatusViaje objTipoStatusViaje, @ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.crearTipoStatusViaje(
				objTipoStatusViaje), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualizar Tipo de Vehiculo para el Viaje", response = TipoStatusViaje.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoStatusViaje.class)
	})
	@PutMapping(value = "${servicio.app.url.actualizarTipoStatusViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoStatusViaje>  actualizarTipoStatusViaje(
			  @ApiParam(value = "objeto de entrada", required = true) @RequestBody @Valid TipoStatusViaje objTipoStatusViaje,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id, 
			@ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.actualizarTipoStatusViaje(
				Integer.parseInt(id), objTipoStatusViaje), HttpStatus.OK);
	}

	@ApiOperation(value = "Buscar Tipo de Vehiculo para el Viaje", response = TipoStatusViaje.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoStatusViaje.class)
	})
	@GetMapping(value = "${servicio.app.url.buscarTipoStatusViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoStatusViaje>  buscarTipoStatusViaje(  @ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.buscarTipoStatusViaje(
				new TipoStatusViaje(Integer.parseInt(id))), HttpStatus.OK);
	}

	@ApiOperation(value = "Listar Todos los Tipo de Vehiculo para el Viaje", response = TipoStatusViaje.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = TipoStatusViaje.class)
	})

	@GetMapping(value = "${servicio.app.url.listarTodoTipoStatusViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TipoStatusViaje>>  listarTodoTipoStatusViaje()  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.listarTodoTipoStatusViaje(), HttpStatus.OK);

	}
	
	@ApiOperation(value = "Crear Clasificacion de Pasajero", response = ClasificacionPasajero.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = ClasificacionPasajero.class)
	})
	@PostMapping(value = "${servicio.app.url.crearClasificacionPasajero}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClasificacionPasajero>  crearClasificacionPasajero(  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid ClasificacionPasajero objClasificacionPasajero, @ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.crearClasificacionPasajero(
				objClasificacionPasajero), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualizar Clasificacion de Pasajero", response = ClasificacionPasajero.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = ClasificacionPasajero.class)
	})
	@PutMapping(value = "${servicio.app.url.actualizarClasificacionPasajero}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClasificacionPasajero>  actualizarClasificacionPasajero(
			  @ApiParam(value = "objeto de entrada", required = true) @RequestBody @Valid ClasificacionPasajero objClasificacionPasajero,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id, 
			@ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.actualizarClasificacionPasajero(
				Integer.parseInt(id), objClasificacionPasajero), HttpStatus.OK);
	}

	@ApiOperation(value = "Buscar Tipo de Vehiculo para el Viaje", response = ClasificacionPasajero.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = ClasificacionPasajero.class)
	})
	@GetMapping(value = "${servicio.app.url.buscarClasificacionPasajero}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClasificacionPasajero>  buscarClasificacionPasajero(  @ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.buscarClasificacionPasajero(
				new ClasificacionPasajero(Integer.parseInt(id))),HttpStatus.OK);
	}

	@ApiOperation(value = "Listar Todos los Tipo de Vehiculo para el Viaje", response = ClasificacionPasajero.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = ClasificacionPasajero.class)
	})

	@GetMapping(value = "${servicio.app.url.listarTodoClasificacionPasajero}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClasificacionPasajero>>  listarTodoClasificacionPasajero()  throws LogicaImplException{
				
		return new ResponseEntity<>(arrastrameTripService.listarTodoClasificacionPasajero(), HttpStatus.OK);

	}
	
	@ApiOperation(value = "Crear Tipo de Rol Pasajero", response = RolPasajero.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = RolPasajero.class)
	})
	@PostMapping(value = "${servicio.app.url.crearRolPasajero}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RolPasajero>  crearRolPasajero(  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid RolPasajero objRolPasajero, @ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.crearRolPasajero(
				objRolPasajero), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualizar Tipo de Vehiculo para el Viaje", response = RolPasajero.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = RolPasajero.class)
	})
	@PutMapping(value = "${servicio.app.url.actualizarRolPasajero}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RolPasajero>  actualizarRolPasajero(
			  @ApiParam(value = "objeto de entrada", required = true) @RequestBody @Valid RolPasajero objRolPasajero,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id, 
			@ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.actualizarRolPasajero(
				Integer.parseInt(id), objRolPasajero), HttpStatus.OK);
	}

	@ApiOperation(value = "Buscar Tipo de Rol de Pasajero", response = RagnaxError.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RolPasajero.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = RolPasajero.class)
	})
	@GetMapping(value = "${servicio.app.url.buscarRolPasajero}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RolPasajero>  buscarRolPasajero(  @ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.buscarRolPasajero(
				new RolPasajero(Integer.parseInt(id))),  HttpStatus.OK);
	}

	@ApiOperation(value = "Listar Todos los Tipo de Vehiculo para el Viaje", response = RolPasajero.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = RolPasajero.class)
	})

	@GetMapping(value = "${servicio.app.url.listarTodoRolPasajero}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RolPasajero>>  listarTodoRolPasajero()  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.listarTodoRolPasajero(), HttpStatus.OK);

	}
	
	@ApiOperation(value = "Crear Tipo de Descuento", response = Descuento.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Descuento.class)
	})
	@PostMapping(value = "${servicio.app.url.crearDescuento}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Descuento>  crearDescuento(  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid Descuento objDescuento, @ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.crearDescuento(objDescuento) , HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualizar Tipo de Descuento", response = RagnaxError.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = RagnaxError.class)
	})
	@PutMapping(value = "${servicio.app.url.actualizarDescuento}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Descuento>  actualizarDescuento(
			  @ApiParam(value = "objeto de entrada", required = true) @RequestBody @Valid Descuento objDescuento,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id, 
			@ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.actualizarDescuento(Integer.parseInt(id), objDescuento),  HttpStatus.OK);
	}

	@ApiOperation(value = "Buscar Tipo de Descuento", response = RagnaxError.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = RagnaxError.class)
	})
	@GetMapping(value = "${servicio.app.url.buscarDescuentoxCodigoDescuento}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Descuento>  buscarDescuentoxCodigoDescuento(  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String codigodescuento)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.buscarDescuentoxCodigoDescuento(
				new Descuento(codigodescuento)), HttpStatus.OK);
	}

	@ApiOperation(value = "Listar Todos los Tipo de Descuento", response = RagnaxError.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = RagnaxError.class)
	})

	@GetMapping(value = "${servicio.app.url.listarTodoDescuento}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Descuento>>  listarTodoDescuento()  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.listarTodoDescuento(), HttpStatus.OK);

	}
	
	@ApiOperation(value = "Listar Todos los Tipo de Descuento", response = RagnaxError.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = RagnaxError.class)
	})

	@GetMapping(value = "${servicio.app.url.listarDescuentoxFechaFinDescuento}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Descuento>> listarDescuentoxFechaFinDescuento(
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String fechaFinDescuentoA,
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String fechaFinDescuentoB)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.listarDescuentoxFechaFinDescuento(
				fechaFinDescuentoA, fechaFinDescuentoB), HttpStatus.OK);

	}
	
	@ApiOperation(value = "Crear PasajeroArrastrame", response = PasajeroArrastrame.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = PasajeroArrastrame.class)
	})
	@PostMapping(value = "${servicio.app.url.crearPasajeroArrastrame}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PasajeroArrastrame>  crearPasajeroArrastrame(  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid PasajeroArrastrame objPasajeroArrastrame, @ApiIgnore Errors errors)  throws LogicaImplException{

		
		
		return new ResponseEntity<>(arrastrameTripService.crearPasajeroArrastrame(
				objPasajeroArrastrame), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualizar Tipo de PasajeroArrastrame", response = PasajeroArrastrame.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = PasajeroArrastrame.class)
	})
	@PutMapping(value = "${servicio.app.url.actualizarPasajeroArrastrame}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PasajeroArrastrame>  actualizarPasajeroArrastrame(
			  @ApiParam(value = "objeto de entrada", required = true) @RequestBody @Valid PasajeroArrastrame objPasajeroArrastrame,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id, 
			@ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.actualizarPasajeroArrastrame(
				Integer.parseInt(id), objPasajeroArrastrame), HttpStatus.OK);
	}

	@ApiOperation(value = "Buscar Tipo de Pasajero Arrastrame", response = PasajeroArrastrame.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = PasajeroArrastrame.class)
	})
	@GetMapping(value = "${servicio.app.url.buscarPasajeroArrastramexIdUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PasajeroArrastrame>  buscarPasajeroArrastramexIdUsuario(  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String idusuario)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.buscarPasajeroArrastramexIdUsuario(
				new PasajeroArrastrame(Integer.parseInt(idusuario))), HttpStatus.OK);
	}

	@ApiOperation(value = "Listar Todos los Pasajero Arrastrame", response = PasajeroArrastrame.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = PasajeroArrastrame.class)
	})

	@GetMapping(value = "${servicio.app.url.listarTodoPasajeroArrastrame}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PasajeroArrastrame>>  listarTodoPasajeroArrastrame(
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String fechaFinPasajeroArrastrameA,
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String fechaFinPasajeroArrastrameB)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.listarTodoPasajeroArrastrame(), HttpStatus.OK);

	}
	
	@ApiOperation(value = "Generar codigo de Viaje", response = RagnaxError.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = RagnaxError.class)
	})
	@PostMapping(value = "${servicio.app.url.generarCodigoViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Viaje>  generarCodigoViaje(
			  @ApiParam(value = "objeto de entrada", required = true)
			@RequestBody @Valid Viaje objViaje,
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String idpasajeroarrastrame, 
			@ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.generarCodigoViaje(objViaje), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Crear Viaje", response = Viaje.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Viaje.class)
	})
	@PostMapping(value = "${servicio.app.url.crearViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Viaje>  crearViaje(
			  @ApiParam(value = "objeto de entrada", required = true)
			@RequestBody @Valid Viaje objViaje,
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String idpasajeroarrastrame,
//			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String stimeinicioviaje,
			@ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.crearViaje(Integer.parseInt(idpasajeroarrastrame), objViaje), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualizar Viaje", response = Viaje.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Viaje.class)
	})
	@PutMapping(value = "${servicio.app.url.actualizarViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Viaje>  actualizarViaje(
			  @ApiParam(value = "objeto de entrada", required = true) @RequestBody @Valid Viaje objViaje,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id, 
			@ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.actualizarViaje(
				Integer.parseInt(id), objViaje), HttpStatus.OK);
	}

	@ApiOperation(value = "Buscar Viaje por codigo de viaje", response = Viaje.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Viaje.class)
	})
	@GetMapping(value = "${servicio.app.url.buscarViajexCodigoViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Viaje>  buscarViajexCodigoViaje(  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String codigoviaje)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.buscarViajexCodigoViaje(new Viaje(codigoviaje)), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Buscar Viaje por codigo de viaje", response = Viaje.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Viaje.class)
	})
	@GetMapping(value = "${servicio.app.url.buscarViajexTimeInicioMismoInstanteViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Viaje>>  buscarViajexTimeInicioMismoInstanteViaje( 
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String stimeinicioviaje)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.listarViajexTimeInicioMismoInstanteViaje(
				stimeinicioviaje),  HttpStatus.OK);
	}
	
	@ApiOperation(value = "Listar Todos los Viaje", response = Viaje.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Viaje.class)
	})

	@GetMapping(value = "${servicio.app.url.listarTodoViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Viaje>>  listarTodoViaje(
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String fechaFinViajeA,
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String fechaFinViajeB)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.listarTodoViaje(), HttpStatus.OK);

	}
	
	@ApiOperation(value = "Crear Status de Viaje", response = StatusViaje.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = StatusViaje.class)
	})
	@PostMapping(value = "${servicio.app.url.crearStatusViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StatusViaje>  crearStatusViaje(  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid StatusViaje objStatusViaje, @ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.crearStatusViaje(objStatusViaje), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Listar Staus de Viaje por el id del Viaje", response = RagnaxError.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = RagnaxError.class)
	})
	@GetMapping(value = "${servicio.app.url.listarStatusViajexIdViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StatusViaje>>  listarStatusViajexIdViaje(
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String idViaje)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.listarStatusViajexIdViaje(
				new StatusViaje(new Viaje(idViaje))), HttpStatus.OK);

	}
	/***************************************************/
	/*************** DescuentoViaje ***********/
	/***************************************************/
	@ApiOperation(value = "Crear DescuentoViaje", response = DescuentoViaje.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = DescuentoViaje.class)
	})
	@PostMapping(value = "${servicio.app.url.crearDescuentoViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DescuentoViaje>  crearDescuentoViaje(  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid DescuentoViaje objDescuentoViaje, @ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.crearDescuentoViaje(
				objDescuentoViaje), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualizar Tipo de DescuentoViaje", response = DescuentoViaje.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = DescuentoViaje.class)
	})
	@PutMapping(value = "${servicio.app.url.buscarDescuentoViajexIdDescuentoxIdViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DescuentoViaje>  buscarDescuentoViajexIdDescuentoxIdViaje(
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String codigodescuento,
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String codigoviaje)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.buscarDescuentoViajexIdDescuentoxIdViaje(
				new DescuentoViaje(new Descuento(codigodescuento), new Viaje(codigoviaje))), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualizar Tipo de DescuentoViaje", response = DescuentoViaje.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = DescuentoViaje.class)
	})
	@GetMapping(value = "${servicio.app.url.eliminarDescuentoViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DescuentoViaje>  eliminarDescuentoViaje(
			
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String idusuario,
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String codigodescuento,
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String codigoViaje) throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.eliminarDescuentoViaje(
				Integer.parseInt(idusuario), new DescuentoViaje(new Descuento(codigodescuento), new Viaje(codigoViaje)))
				, HttpStatus.OK);
	}

	@ApiOperation(value = "Listar Todos los DescuentoViaje", response = DescuentoViaje.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = DescuentoViaje.class)
	})

	@GetMapping(value = "${servicio.app.url.listarDescuentoViajexidViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DescuentoViaje>>  listarTodoDescuentoViaje(
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String codigoviaje)  throws LogicaImplException {

		return new ResponseEntity<>(arrastrameTripService.listarDescuentoViajexidViaje(
				new DescuentoViaje(null, new Viaje(codigoviaje))), HttpStatus.OK);

	}
	
	/***************************************************/
	/*************** ViajeRecomendado ***********/
	/***************************************************/
	@ApiOperation(value = "Crear ViajeRecomendado", response = ViajeRecomendado.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = ViajeRecomendado.class)
	})
	@PostMapping(value = "${servicio.app.url.crearViajeRecomendado}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ViajeRecomendado>  crearViajeRecomendado(  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid ViajeRecomendado objViajeRecomendado, @ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.crearViajeRecomendado(
				objViajeRecomendado), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualizar Tipo de ViajeRecomendado", response = ViajeRecomendado.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = ViajeRecomendado.class)
	})
	@PutMapping(value = "${servicio.app.url.buscarViajeRecomendadoxIdViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ViajeRecomendado>  buscarViajeRecomendadoxIdDescuentoxIdViaje(
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String codigoviaje)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.buscarViajeRecomendadoxIdViaje(
				new ViajeRecomendado(new Viaje(codigoviaje), null)), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Listar Todos los ViajeRecomendado", response = ViajeRecomendado.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = ViajeRecomendado.class)
	})

	@GetMapping(value = "${servicio.app.url.listarViajeRecomendadoxidTipoViajeRecomendado}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ViajeRecomendado>>  listarTodoViajeRecomendado(
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String idtipoviaje)  throws LogicaImplException {

		return new ResponseEntity<>(arrastrameTripService.
				listarViajeRecomendadoxidTipoViajeRecomendado(
				new ViajeRecomendado(null, new TipoViajeRecomendado(Integer.parseInt(idtipoviaje)))), HttpStatus.OK);
	}
	
	/***************************************************/
	/*************** PasajeroArrastrameViaje ***********/
	/***************************************************/
	@ApiOperation(value = "Crear Tipo de Pasajero Arrastrame Viaje", response = RagnaxError.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = RagnaxError.class)
	})
	@PostMapping(value = "${servicio.app.url.crearPasajeroArrastrameViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PasajeroArrastrameViaje>  crearPasajeroArrastrameViaje(  @ApiParam(value = "objeto de entrada", required = true) 
	@RequestBody @Valid PasajeroArrastrameViaje objPasajeroArrastrameViaje, @ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.crearPasajeroArrastrameViaje(
				objPasajeroArrastrameViaje), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Actualizar Tipo de Viaje Recomendado", response = RagnaxError.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = RagnaxError.class)
	})
	@PutMapping(value = "${servicio.app.url.actualizarPasajeroArrastrameViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PasajeroArrastrameViaje>  actualizarPasajeroArrastrameViaje(
			  @ApiParam(value = "objeto de entrada", required = true) @RequestBody @Valid PasajeroArrastrameViaje objPasajeroArrastrameViaje,  
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id, 
			@ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.actualizarPasajeroArrastrameViaje(
				Integer.parseInt(id), objPasajeroArrastrameViaje), HttpStatus.OK);
	}

	@ApiOperation(value = "Buscar Tipo de Viaje Recomendado", response = RagnaxError.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = RagnaxError.class)
	})
	@GetMapping(value = "${servicio.app.url.eliminarPasajeroArrastrameViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PasajeroArrastrameViaje>  eliminarPasajeroArrastrameViaje(
			@ApiParam(value = "objeto de entrada", required = true) @RequestBody @Valid PasajeroArrastrameViaje objPasajeroArrastrameViaje, 
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String id)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.eliminarPasajeroArrastrameViaje(Integer.parseInt(id), 
				objPasajeroArrastrameViaje), HttpStatus.OK);
	}

	@ApiOperation(value = "Listar Todos los Tipo de Viaje Recomendado", response = RagnaxError.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = RagnaxError.class)
	})

	@GetMapping(value = "${servicio.app.url.listarPasajeroArrastrameViajexidViaje}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PasajeroArrastrameViaje>>  listarPasajeroArrastrameViajexidViaje(
			@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") @PathVariable String codigoViaje)  throws LogicaImplException{

		return new ResponseEntity<>(arrastrameTripService.listarPasajeroArrastrameViajexidViaje(
				new PasajeroArrastrameViaje(new Viaje(codigoViaje))), HttpStatus.OK);

	}

}
