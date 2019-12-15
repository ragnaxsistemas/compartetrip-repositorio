package com.ragnax.compartetriprepositorio.servicio;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ragnax.compartetriprepositorio.configuration.FactoryApiProperties;
import com.ragnax.compartetriprepositorio.controller.response.ArrastrameTrip;
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
import com.ragnax.compartetriprepositorio.repository.FactoryArrastrameTripDAO;
import com.ragnax.compartetriprepositorio.servicio.clientes.ZapalaClienteRest;
import com.ragnax.compartetriprepositorio.servicio.clientes.modelo.ZapalaRequest;
import com.ragnax.compartetriprepositorio.servicio.utilidades.ArrastrameTripUtilidades;

@Service
@CacheConfig(cacheNames = { "buscarTipoViajeRecomendado", "listarTodoTipoViajeRecomendado", "buscarTipoVehiculoViaje",
		"listarTodoTipoVehiculoViaje", "buscarTipoViaje", "listarTodoTipoViaje","buscarTipoStatusViaje",
		"listarTodoTipoStatusViaje", "buscarClasificacionPasajero", "listarTodoClasificacionPasajero",
		"buscarRolPasajero", "listarTodoRolPasajero", "buscarDescuentoxCodigoDescuento", 
		"buscarPasajeroArrastramexIdUsuario"})
@ComponentScan(basePackageClasses = {FactoryApiProperties.class})
public class ArrastrameTripServiceImpl implements ArrastrameTripService {
	//Segun se necesite se van creando llamadas al repositorio para devolver entities.
	@Autowired
	private FactoryArrastrameTripDAO factoryArrastrameTripDAO;
	
	@Autowired
	private ZapalaClienteRest zapalaClienteRest;
	
	@Autowired
	private FactoryApiProperties factoryApiProperties;
	/***********************************************************/
	/******TipoViajeRecomendado TipoViajeRecomendado ***********/
	/***********************************************************/
	public ArrastrameTrip crearTipoViajeRecomendado(TipoViajeRecomendado objTipoViajeRecomendado) throws LogicaImplException{
		
		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {

			Pageable pageByNombreTipoViajeRecomendado = PageRequest.of(0, 1, Sort.by("nombreTipoViajeRecomendado").descending());

			Page<TipoViajeRecomendado> pageNombreTipoViajeRecomendado  =  
					factoryArrastrameTripDAO.getTipoViajeRecomendadoRepository(). findByNombreTipoViajeRecomendado(objTipoViajeRecomendado.getNombreTipoViajeRecomendado().toLowerCase(), pageByNombreTipoViajeRecomendado);

			if(pageNombreTipoViajeRecomendado.getContent().isEmpty()) {

				Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idTipoViajeRecomendado").descending());

				Page<TipoViajeRecomendado> pageIdTipoViajeRecomendado = factoryArrastrameTripDAO.getTipoViajeRecomendadoRepository().findAll(pageByidDesc);

				Integer idTipoViajeRecomendado =  (pageIdTipoViajeRecomendado.getContent().isEmpty()) ? (Integer) pageIdTipoViajeRecomendado.getContent().get(0).getIdTipoViajeRecomendado() + 1 : 1;
				/*Siempre hacer mayusculas los codigos**/
				objTipoViajeRecomendado.setNombreTipoViajeRecomendado(objTipoViajeRecomendado.getNombreTipoViajeRecomendado().toLowerCase());

				objTipoViajeRecomendado.setIdTipoViajeRecomendado(idTipoViajeRecomendado);

				factoryArrastrameTripDAO.getTipoViajeRecomendadoRepository().save(objTipoViajeRecomendado);

				TipoViajeRecomendado codTipoViajeRecomendado = buscarTipoViajeRecomendado(objTipoViajeRecomendado).getTipoViajeRecomendado(); 

				arrastrameTrip.setTipoViajeRecomendado(codTipoViajeRecomendado);
			}else {
				throw new LogicaImplException("No se puede actualizar TipoViajeRecomendado, parametros inválidos");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	public ArrastrameTrip actualizarTipoViajeRecomendado(Integer id, TipoViajeRecomendado objTipoViajeRecomendado) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {

			buscarTipoViajeRecomendado(new TipoViajeRecomendado(id));

			Pageable pageByNombreTipoViajeRecomendado = PageRequest.of(0, 1, Sort.by("nombreTipoViajeRecomendado").descending());

			Page<TipoViajeRecomendado> pageNombreTipoViajeRecomendado  = factoryArrastrameTripDAO.getTipoViajeRecomendadoRepository().findByNombreTipoViajeRecomendado(objTipoViajeRecomendado.getNombreTipoViajeRecomendado().toLowerCase(), pageByNombreTipoViajeRecomendado);
			/***Busqueda por nombre existe en un tipoNegocio No existe. o solo existe en el pageNombreTipoNegocio.idTipoNegocio = id 
				//... solo actualizar estado****/
			if(!pageNombreTipoViajeRecomendado.isEmpty() && pageNombreTipoViajeRecomendado.getContent().get(0).getIdTipoViajeRecomendado()==id){
				objTipoViajeRecomendado.setIdTipoViajeRecomendado(id);

				objTipoViajeRecomendado.setNombreTipoViajeRecomendado(objTipoViajeRecomendado.getNombreTipoViajeRecomendado().toLowerCase());

				factoryArrastrameTripDAO.getTipoViajeRecomendadoRepository().save(objTipoViajeRecomendado);

				arrastrameTrip.setTipoViajeRecomendado(objTipoViajeRecomendado);
			}
			else {
				throw new LogicaImplException("No se puede actualizar TipoViajeRecomendado, parametros ya existen en un identificador distinto");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	@Cacheable(value="buscarTipoViajeRecomendado")
	public ArrastrameTrip buscarTipoViajeRecomendado(TipoViajeRecomendado objTipoViajeRecomendado) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();
		try {	

			Optional<TipoViajeRecomendado> optPerTipoViajeRecomendado = factoryArrastrameTripDAO.getTipoViajeRecomendadoRepository().findById(objTipoViajeRecomendado.getIdTipoViajeRecomendado());

			/***Si existe reemplazar por el nuevo*/
			if(optPerTipoViajeRecomendado!=null && optPerTipoViajeRecomendado.isPresent()){

				arrastrameTrip.setTipoViajeRecomendado(optPerTipoViajeRecomendado.get());

			}else {
				throw new LogicaImplException("No existe TipoViajeRecomendado con identificador:" +objTipoViajeRecomendado.getIdTipoViajeRecomendado());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return arrastrameTrip;
	}	

	//	@Cacheable(value="listarTodoTipoViajeRecomendado")
	public ArrastrameTrip listarTodoTipoViajeRecomendado() throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			List<TipoViajeRecomendado> listaTipoViajeRecomendado = factoryArrastrameTripDAO.getTipoViajeRecomendadoRepository().findAll();

			if(listaTipoViajeRecomendado !=null && !listaTipoViajeRecomendado.isEmpty()){
				arrastrameTrip.setListaTipoViajeRecomendado(listaTipoViajeRecomendado);
			}else {
				throw new LogicaImplException("No existe lista de TipoViajeRecomendado");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	/***********************************************************/
	/******TipoVehiculoViaje TipoVehiculoViaje TipoVehiculoViaje TipoVehiculoViaje *****/
	/***********************************************************/
	public ArrastrameTrip crearTipoVehiculoViaje(TipoVehiculoViaje objTipoVehiculoViaje) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			Pageable pageByNombreTipoVehiculoViaje = PageRequest.of(0, 1, Sort.by("nombreTipoVehiculoViaje").descending());

			Page<TipoVehiculoViaje> pageNombreTipoVehiculoViaje  =
					factoryArrastrameTripDAO.getTipoVehiculoViajeRepository().findByNombreTipoVehiculoViaje(objTipoVehiculoViaje.getNombreTipoVehiculoViaje().toLowerCase(), pageByNombreTipoVehiculoViaje);

			if(pageNombreTipoVehiculoViaje!=null && pageNombreTipoVehiculoViaje.getContent().isEmpty()) {
				Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idTipoVehiculoViaje").descending());

				Page<TipoVehiculoViaje> pageIdTipoVehiculoViaje = 
						factoryArrastrameTripDAO.getTipoVehiculoViajeRepository().findAll(pageByidDesc);

				Integer idTipoVehiculoViaje = (!pageIdTipoVehiculoViaje.isEmpty()) ? (Integer) pageIdTipoVehiculoViaje.getContent().get(0).getIdTipoVehiculoViaje() + 1 : 1;
				/*Siempre hacer mayusculas los codigos**/
				objTipoVehiculoViaje.setNombreTipoVehiculoViaje(objTipoVehiculoViaje.getNombreTipoVehiculoViaje().toLowerCase());

				objTipoVehiculoViaje.setIdTipoVehiculoViaje(idTipoVehiculoViaje);

				factoryArrastrameTripDAO.getTipoVehiculoViajeRepository().save(objTipoVehiculoViaje);

				TipoVehiculoViaje codTipoVehiculoViaje = buscarTipoVehiculoViaje(objTipoVehiculoViaje).getTipoVehiculoViaje(); 

				arrastrameTrip.setTipoVehiculoViaje(codTipoVehiculoViaje);
			}else {
				throw new LogicaImplException("No se puede actualizar TipoVehiculoViaje, parametros inválidos");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	public ArrastrameTrip actualizarTipoVehiculoViaje(Integer id, TipoVehiculoViaje objTipoVehiculoViaje) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {

			buscarTipoVehiculoViaje(new TipoVehiculoViaje(id));

			Pageable pageByNombreTipoVehiculoViaje = PageRequest.of(0, 1, Sort.by("nombreTipoVehiculoViaje").descending());

			Page<TipoVehiculoViaje> pageNombreTipoVehiculoViaje  = factoryArrastrameTripDAO.getTipoVehiculoViajeRepository().findByNombreTipoVehiculoViaje(objTipoVehiculoViaje.getNombreTipoVehiculoViaje().toLowerCase(), pageByNombreTipoVehiculoViaje);
			/***Busqueda por nombre existe en un tipoNegocio No existe. o solo existe en el pageNombreTipoNegocio.idTipoNegocio = id 
				//... solo actualizar estado****/
			if(!pageNombreTipoVehiculoViaje.isEmpty() && pageNombreTipoVehiculoViaje.getContent().get(0).getIdTipoVehiculoViaje()==id){
				objTipoVehiculoViaje.setIdTipoVehiculoViaje(id);

				objTipoVehiculoViaje.setNombreTipoVehiculoViaje(objTipoVehiculoViaje.getNombreTipoVehiculoViaje().toLowerCase());

				factoryArrastrameTripDAO.getTipoVehiculoViajeRepository().save(objTipoVehiculoViaje);

				arrastrameTrip.setTipoVehiculoViaje(objTipoVehiculoViaje);
			}
			else {
				throw new LogicaImplException("No se puede actualizar TipoVehiculoViaje, parametros ya existen en un identificador distinto");
			}


		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	//	@Cacheable(value="buscarTipoVehiculoViaje")
	public ArrastrameTrip buscarTipoVehiculoViaje(TipoVehiculoViaje objTipoVehiculoViaje) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();
		try {	

			Optional<TipoVehiculoViaje> optPerTipoVehiculoViaje = factoryArrastrameTripDAO.getTipoVehiculoViajeRepository().findById(objTipoVehiculoViaje.getIdTipoVehiculoViaje());

			/***Si existe reemplazar por el nuevo*/
			if(optPerTipoVehiculoViaje!=null && optPerTipoVehiculoViaje.isPresent()){

				arrastrameTrip.setTipoVehiculoViaje(optPerTipoVehiculoViaje.get());

			}else {
				throw new LogicaImplException("No existe TipoVehiculoViaje con identificador:" +objTipoVehiculoViaje.getIdTipoVehiculoViaje());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return arrastrameTrip;
	}	

	@Cacheable(value="listarTodoTipoVehiculoViaje")
	public ArrastrameTrip listarTodoTipoVehiculoViaje() throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			List<TipoVehiculoViaje> listaTipoVehiculoViaje = factoryArrastrameTripDAO.getTipoVehiculoViajeRepository().findAll();

			if(listaTipoVehiculoViaje !=null && !listaTipoVehiculoViaje.isEmpty()){
				arrastrameTrip.setListaTipoVehiculoViaje(listaTipoVehiculoViaje);
			}else {
				throw new LogicaImplException("No existe lista de TipoVehiculoViaje");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	/***********************************************************/
	/******TipoViaje TipoViaje TipoViaje TipoViaje *****/
	/***********************************************************/

	public ArrastrameTrip crearTipoViaje(TipoViaje objTipoViaje) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {

			Pageable pageByNombreTipoViaje = PageRequest.of(0, 1, Sort.by("nombreTipoViaje").descending());

			Page<TipoViaje> pageNombreTipoViaje  = 
					factoryArrastrameTripDAO.getTipoViajeRepository().findByNombreTipoViaje(objTipoViaje.getNombreTipoViaje().toLowerCase(), pageByNombreTipoViaje);
			
			if(pageNombreTipoViaje.isEmpty()) {
				Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idTipoViaje").descending());

				Page<TipoViaje> pageIdTipoViaje =  factoryArrastrameTripDAO.getTipoViajeRepository().findAll(pageByidDesc);

				Integer idTipoViaje = (!pageIdTipoViaje.isEmpty()) ? (Integer) pageIdTipoViaje.getContent().get(0).getIdTipoViaje() + 1 : 1;
				/*Siempre hacer mayusculas los codigos**/
				objTipoViaje.setNombreTipoViaje(objTipoViaje.getNombreTipoViaje().toLowerCase());
				
				objTipoViaje.setIdTipoViaje(idTipoViaje);

				factoryArrastrameTripDAO.getTipoViajeRepository().save(objTipoViaje);

				TipoViaje codTipoViaje = buscarTipoViaje(objTipoViaje).getTipoViaje(); 

				arrastrameTrip.setTipoViaje(codTipoViaje);
				
			}else {
				throw new LogicaImplException("No se puede actualizar TipoViaje, parametros inválidos");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	public ArrastrameTrip actualizarTipoViaje(Integer id, TipoViaje objTipoViaje) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {

			buscarTipoViaje(new TipoViaje(id));

			Pageable pageByNombreTipoViaje = PageRequest.of(0, 1, Sort.by("nombreTipoViaje").descending());

			Page<TipoViaje> pageNombreTipoViaje  = factoryArrastrameTripDAO.getTipoViajeRepository().findByNombreTipoViaje(objTipoViaje.getNombreTipoViaje().toLowerCase(), pageByNombreTipoViaje);
			/***Busqueda por nombre existe en un tipoNegocio No existe. o solo existe en el pageNombreTipoNegocio.idTipoNegocio = id 
				//... solo actualizar estado****/
			if(!pageNombreTipoViaje.isEmpty() && pageNombreTipoViaje.getContent().get(0).getIdTipoViaje()==id){
				objTipoViaje.setIdTipoViaje(id);

				objTipoViaje.setNombreTipoViaje(objTipoViaje.getNombreTipoViaje().toLowerCase());

				factoryArrastrameTripDAO.getTipoViajeRepository().save(objTipoViaje);

				arrastrameTrip.setTipoViaje(objTipoViaje);
			}
			else {
				throw new LogicaImplException("No se puede actualizar TipoViaje, parametros ya existen en un identificador distinto");
			}


		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	@Cacheable(value="buscarTipoViaje")
	public ArrastrameTrip buscarTipoViaje(TipoViaje objTipoViaje) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();
		try {	

			Optional<TipoViaje> optPerTipoViaje = factoryArrastrameTripDAO.getTipoViajeRepository().findById(objTipoViaje.getIdTipoViaje());

			/***Si existe reemplazar por el nuevo*/
			if(optPerTipoViaje!=null && optPerTipoViaje.isPresent()){

				arrastrameTrip.setTipoViaje(optPerTipoViaje.get());

			}else {
				throw new LogicaImplException("No existe TipoViaje con identificador:" +objTipoViaje.getIdTipoViaje());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return arrastrameTrip;
	}	

	@Cacheable(value="listarTodoTipoViaje")
	public ArrastrameTrip listarTodoTipoViaje() throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			List<TipoViaje> listaTipoViaje = factoryArrastrameTripDAO.getTipoViajeRepository().findAll();

			if(listaTipoViaje !=null && !listaTipoViaje.isEmpty()){
				arrastrameTrip.setListaTipoViaje(listaTipoViaje);
			}else {
				throw new LogicaImplException("No existe lista de TipoViaje");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	public ArrastrameTrip crearTipoStatusViaje(TipoStatusViaje objTipoStatusViaje) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {

			Pageable pageByNombreTipoStatusViaje = PageRequest.of(0, 1, Sort.by("nombreTipoStatusViaje").descending());

			Page<TipoStatusViaje> pageNombreTipoStatusViaje  = 
					factoryArrastrameTripDAO.getTipoStatusViajeRepository(). findByNombreTipoStatusViaje(objTipoStatusViaje.getNombreTipoStatusViaje().toLowerCase(), pageByNombreTipoStatusViaje);
			
			if(pageNombreTipoStatusViaje.getContent().isEmpty()){
				Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idTipoStatusViaje").descending());

				Page<TipoStatusViaje> pageIdTipoStatusViaje =  factoryArrastrameTripDAO.getTipoStatusViajeRepository().findAll(pageByidDesc);

				Integer idTipoStatusViaje = (!pageIdTipoStatusViaje.isEmpty()) ? (Integer) pageIdTipoStatusViaje.getContent().get(0).getIdTipoStatusViaje() + 1 : 1;

				/*Siempre hacer mayusculas los codigos**/
				objTipoStatusViaje.setNombreTipoStatusViaje(objTipoStatusViaje.getNombreTipoStatusViaje().toLowerCase());

				objTipoStatusViaje.setIdTipoStatusViaje(idTipoStatusViaje);

				factoryArrastrameTripDAO.getTipoStatusViajeRepository().save(objTipoStatusViaje);

				TipoStatusViaje codTipoStatusViaje = buscarTipoStatusViaje(objTipoStatusViaje).getTipoStatusViaje(); 

				arrastrameTrip.setTipoStatusViaje(codTipoStatusViaje);
			}else {
				throw new LogicaImplException("No se puede actualizar TipoStatusViaje, parametros inválidos");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	public ArrastrameTrip actualizarTipoStatusViaje(Integer id, TipoStatusViaje objTipoStatusViaje) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			buscarTipoStatusViaje( new TipoStatusViaje(id));

			Pageable pageByNombreTipoStatusViaje = PageRequest.of(0, 1, Sort.by("nombreTipoStatusViaje").descending());

			Page<TipoStatusViaje> pageNombreTipoStatusViaje  = factoryArrastrameTripDAO.getTipoStatusViajeRepository().findByNombreTipoStatusViaje(objTipoStatusViaje.getNombreTipoStatusViaje().toLowerCase(), pageByNombreTipoStatusViaje);
			/***Busqueda por nombre existe en un tipoNegocio No existe. o solo existe en el pageNombreTipoNegocio.idTipoNegocio = id 
				//... solo actualizar estado****/
			if(!pageNombreTipoStatusViaje.isEmpty() && pageNombreTipoStatusViaje.getContent().get(0).getIdTipoStatusViaje()==id){
				objTipoStatusViaje.setIdTipoStatusViaje(id);

				objTipoStatusViaje.setNombreTipoStatusViaje(objTipoStatusViaje.getNombreTipoStatusViaje().toLowerCase());

				factoryArrastrameTripDAO.getTipoStatusViajeRepository().save(objTipoStatusViaje);

				arrastrameTrip.setTipoStatusViaje(objTipoStatusViaje);
			}
			else {
				throw new LogicaImplException("No se puede actualizar TipoStatusViaje, parametros ya existen en un identificador distinto");
			}



		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	@Cacheable(value="buscarTipoStatusViaje")
	public ArrastrameTrip buscarTipoStatusViaje(TipoStatusViaje objTipoStatusViaje) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();
		try {	

			Optional<TipoStatusViaje> optPerTipoStatusViaje = factoryArrastrameTripDAO.getTipoStatusViajeRepository().findById(objTipoStatusViaje.getIdTipoStatusViaje());

			/***Si existe reemplazar por el nuevo*/
			if(optPerTipoStatusViaje!=null && optPerTipoStatusViaje.isPresent()){

				arrastrameTrip.setTipoStatusViaje(optPerTipoStatusViaje.get());

			}else {
				throw new LogicaImplException("No existe TipoStatusViaje con identificador:" +objTipoStatusViaje.getIdTipoStatusViaje());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return arrastrameTrip;
	}	

	@Cacheable(value="listarTodoTipoStatusViaje")
	public ArrastrameTrip listarTodoTipoStatusViaje() throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			List<TipoStatusViaje> listaTipoStatusViaje = factoryArrastrameTripDAO.getTipoStatusViajeRepository().findAll();

			if(listaTipoStatusViaje !=null && !listaTipoStatusViaje.isEmpty()){
				arrastrameTrip.setListaTipoStatusViaje(listaTipoStatusViaje);
			}else {
				throw new LogicaImplException("No existe lista de TipoStatusViaje");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	/***********************************************************/
	/******ClasificacionPasajero ClasificacionPasajero ClasificacionPasajero ClasificacionPasajero *****/
	/***********************************************************/

	public ArrastrameTrip crearClasificacionPasajero(ClasificacionPasajero objClasificacionPasajero) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			Pageable pageByNombreClasificacionPasajero = PageRequest.of(0, 1, Sort.by("nombreClasificacionPasajero").descending());

			Page<ClasificacionPasajero> pageNombreClasificacionPasajero  = (!objClasificacionPasajero.getNombreClasificacionPasajero().equals("")) ?  
					factoryArrastrameTripDAO.getClasificacionPasajeroRepository().findByNombreClasificacionPasajero( objClasificacionPasajero.getNombreClasificacionPasajero().toLowerCase(), pageByNombreClasificacionPasajero)  : null;

					if(pageNombreClasificacionPasajero.getContent().isEmpty()){

						Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idClasificacionPasajero").descending());

						Page<ClasificacionPasajero> pageIdClasificacionPasajero = factoryArrastrameTripDAO.getClasificacionPasajeroRepository().findAll(pageByidDesc);

						Integer idClasificacionPasajero = (!pageIdClasificacionPasajero.isEmpty()) ? (Integer) pageIdClasificacionPasajero.getContent().get(0).getIdClasificacionPasajero() + 1 : 1;

						/*Siempre hacer mayusculas los codigos**/
						objClasificacionPasajero.setNombreClasificacionPasajero(objClasificacionPasajero.getNombreClasificacionPasajero().toLowerCase());

						objClasificacionPasajero.setIdClasificacionPasajero(idClasificacionPasajero);

						factoryArrastrameTripDAO.getClasificacionPasajeroRepository().save(objClasificacionPasajero);

						ClasificacionPasajero codClasificacionPasajero = buscarClasificacionPasajero(objClasificacionPasajero).getClasificacionPasajero(); 

						arrastrameTrip.setClasificacionPasajero(codClasificacionPasajero);

					}else {
						throw new LogicaImplException("No se puede crear ClasificacionPasajero, parametros ya existen en identificador valido");
					}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	public ArrastrameTrip actualizarClasificacionPasajero(Integer id, ClasificacionPasajero objClasificacionPasajero) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {

			buscarClasificacionPasajero( new ClasificacionPasajero(id));

			Pageable pageByNombreClasificacionPasajero = PageRequest.of(0, 1, Sort.by("nombreClasificacionPasajero").descending());

			Page<ClasificacionPasajero> pageNombreClasificacionPasajero  = factoryArrastrameTripDAO.getClasificacionPasajeroRepository().findByNombreClasificacionPasajero(objClasificacionPasajero.getNombreClasificacionPasajero().toLowerCase(), pageByNombreClasificacionPasajero);
			/***Busqueda por nombre existe en un tipoNegocio No existe. o solo existe en el pageNombreTipoNegocio.idTipoNegocio = id 
				//... solo actualizar estado****/
			if(!pageNombreClasificacionPasajero.isEmpty() && pageNombreClasificacionPasajero.getContent().get(0).getIdClasificacionPasajero()==id){
				objClasificacionPasajero.setIdClasificacionPasajero(id);

				objClasificacionPasajero.setNombreClasificacionPasajero(objClasificacionPasajero.getNombreClasificacionPasajero().toLowerCase());

				factoryArrastrameTripDAO.getClasificacionPasajeroRepository().save(objClasificacionPasajero);

				arrastrameTrip.setClasificacionPasajero(objClasificacionPasajero);
			}
			else {
				throw new LogicaImplException("No se puede actualizar ClasificacionPasajero, parametros ya existen en un identificador distinto");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	@Cacheable(value="buscarClasificacionPasajero")
	public ArrastrameTrip buscarClasificacionPasajero(ClasificacionPasajero objClasificacionPasajero) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();
		try {	

			Optional<ClasificacionPasajero> optPerClasificacionPasajero = factoryArrastrameTripDAO.getClasificacionPasajeroRepository().findById(objClasificacionPasajero.getIdClasificacionPasajero());

			/***Si existe reemplazar por el nuevo*/
			if(optPerClasificacionPasajero!=null && optPerClasificacionPasajero.isPresent()){

				arrastrameTrip.setClasificacionPasajero(optPerClasificacionPasajero.get());

			}else {
				throw new LogicaImplException("No existe ClasificacionPasajero con identificador:" +objClasificacionPasajero.getIdClasificacionPasajero());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return arrastrameTrip;
	}	

	@Cacheable(value="listarTodoClasificacionPasajero")
	public ArrastrameTrip listarTodoClasificacionPasajero() throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			List<ClasificacionPasajero> listaClasificacionPasajero = factoryArrastrameTripDAO.getClasificacionPasajeroRepository().findAll();

			if(listaClasificacionPasajero !=null && !listaClasificacionPasajero.isEmpty()){
				arrastrameTrip.setListaClasificacionPasajero(listaClasificacionPasajero);
			}else {
				throw new LogicaImplException("No existe lista de ClasificacionPasajero");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	/***********************************************************/
	/******RolPasajero RolPasajero RolPasajero RolPasajero *****/
	/***********************************************************/

	public ArrastrameTrip crearRolPasajero(RolPasajero objRolPasajero) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();
		try {

			Pageable pageByNombreRolPasajero = PageRequest.of(0, 1, Sort.by("nombreRolPasajero").descending());

			Page<RolPasajero> pageNombreRolPasajero  = 
					factoryArrastrameTripDAO.getRolPasajeroRepository().findByNombreRolPasajero(objRolPasajero.getNombreRolPasajero().toLowerCase(), pageByNombreRolPasajero);

					if(pageNombreRolPasajero.getContent().isEmpty()){
						Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idRolPasajero").descending());

						Page<RolPasajero> pageIdRolPasajero = factoryArrastrameTripDAO.getRolPasajeroRepository().findAll(pageByidDesc);

						Integer idRolPasajero = (!pageIdRolPasajero.isEmpty()) ? (Integer) pageIdRolPasajero.getContent().get(0).getIdRolPasajero() + 1 : 1;

						/*Siempre hacer mayusculas los codigos**/
						objRolPasajero.setNombreRolPasajero(objRolPasajero.getNombreRolPasajero().toLowerCase());

						objRolPasajero.setIdRolPasajero(idRolPasajero);

						factoryArrastrameTripDAO.getRolPasajeroRepository().save(objRolPasajero);

						RolPasajero codRolPasajero = buscarRolPasajero(objRolPasajero).getRolPasajero(); 

						arrastrameTrip.setRolPasajero(codRolPasajero);
					}else {
						throw new LogicaImplException("No se puede crear RolPasajero, parametros ya existen en identificador valido");
					}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	public ArrastrameTrip actualizarRolPasajero(Integer id, RolPasajero objRolPasajero) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			buscarRolPasajero(new RolPasajero(id));

			Pageable pageByNombreRolPasajero = PageRequest.of(0, 1, Sort.by("nombreRolPasajero").descending());

			Page<RolPasajero> pageNombreRolPasajero  = factoryArrastrameTripDAO.getRolPasajeroRepository().findByNombreRolPasajero(objRolPasajero.getNombreRolPasajero().toLowerCase(), pageByNombreRolPasajero);
			/***Busqueda por nombre existe en un tipoNegocio No existe. o solo existe en el pageNombreTipoNegocio.idTipoNegocio = id 
				//... solo actualizar estado****/
			if(!pageNombreRolPasajero.isEmpty() && pageNombreRolPasajero.getContent().get(0).getIdRolPasajero()==id){
				objRolPasajero.setIdRolPasajero(id);

				objRolPasajero.setNombreRolPasajero(objRolPasajero.getNombreRolPasajero().toLowerCase());

				factoryArrastrameTripDAO.getRolPasajeroRepository().save(objRolPasajero);

				arrastrameTrip.setRolPasajero(objRolPasajero);
			}
			else {
				throw new LogicaImplException("No se puede actualizar RolPasajero, parametros ya existen en un identificador distinto");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	@Cacheable(value="buscarRolPasajero")
	public ArrastrameTrip buscarRolPasajero(RolPasajero objRolPasajero) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();
		try {	

			Optional<RolPasajero> optPerRolPasajero = factoryArrastrameTripDAO.getRolPasajeroRepository().findById(objRolPasajero.getIdRolPasajero());

			/***Si existe reemplazar por el nuevo*/
			if(optPerRolPasajero!=null && optPerRolPasajero.isPresent()){

				arrastrameTrip.setRolPasajero(optPerRolPasajero.get());

			}else {
				throw new LogicaImplException("No existe RolPasajero con identificador:" +objRolPasajero.getIdRolPasajero());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return arrastrameTrip;
	}	

	@Cacheable(value="listarTodoRolPasajero")
	public ArrastrameTrip listarTodoRolPasajero() throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			List<RolPasajero> listaRolPasajero = factoryArrastrameTripDAO.getRolPasajeroRepository().findAll();

			if(listaRolPasajero !=null && !listaRolPasajero.isEmpty()){
				arrastrameTrip.setListaRolPasajero(listaRolPasajero);
			}else {
				throw new LogicaImplException("No existe lista de RolPasajero");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	/*******************************************/
	/****** Descuento Descuento Descuento ******/
	/*******************************************/
	public ArrastrameTrip crearDescuento(Descuento objDescuento) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {

			Pageable pageByNombreDescuento = PageRequest.of(0, 1, Sort.by("codigoDescuento").descending());

			Page<Descuento> pageCodigoDescuento  = 
					factoryArrastrameTripDAO.getDescuentoRepository().findByCodigoDescuento(objDescuento.getCodigoDescuento(), pageByNombreDescuento);

			if(pageCodigoDescuento.getContent().isEmpty()){
				Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idDescuento").descending());

				Page<Descuento> pageIdDescuento = factoryArrastrameTripDAO.getDescuentoRepository().findAll(pageByidDesc);

				Integer idDescuento = (!pageIdDescuento.isEmpty()) ? (Integer) pageIdDescuento.getContent().get(0).getIdDescuento() + 1 : 1;
				/*Siempre hacer mayusculas los codigos**/
				objDescuento.setIdDescuento(idDescuento);

				factoryArrastrameTripDAO.getDescuentoRepository().save(objDescuento);

				Descuento codDescuento = buscarDescuentoxCodigoDescuento(objDescuento).getDescuento(); 

				arrastrameTrip.setDescuento(codDescuento);
			}else {
				throw new LogicaImplException("No se puede crear Descuento, parametros ya existen en identificador valido");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	public ArrastrameTrip actualizarDescuento(Integer idUsuario, Descuento objDescuento) throws LogicaImplException {

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			Pageable pageByNombreDescuento = PageRequest.of(0, 1, Sort.by("codigoDescuento").descending());

			Page<Descuento> pageCodigoDescuento  = factoryArrastrameTripDAO.getDescuentoRepository().
					findByCodigoDescuento(objDescuento.getCodigoDescuento(), pageByNombreDescuento);

			if(pageCodigoDescuento.getContent().isEmpty()){

				objDescuento.setIdDescuento(idUsuario);

				factoryArrastrameTripDAO.getDescuentoRepository().save(objDescuento);

				arrastrameTrip.setDescuento(objDescuento);
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	@Cacheable(value="buscarDescuentoxCodigoDescuento")
	public ArrastrameTrip buscarDescuentoxCodigoDescuento(Descuento objDescuento) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			Pageable pageByNombreDescuento = PageRequest.of(0, 1, Sort.by("codigoDescuento").descending());

			Page<Descuento> pageCodigoDescuento  = factoryArrastrameTripDAO.getDescuentoRepository().
					findByCodigoDescuento(objDescuento.getCodigoDescuento(), pageByNombreDescuento);
			/***Si existe reemplazar por el nuevo*/
			if(!pageCodigoDescuento.isEmpty()){

				arrastrameTrip.setDescuento(pageCodigoDescuento.getContent().get(0));

			}else {
				throw new LogicaImplException("No existe TipoNegocio con usuario:" +objDescuento.getCodigoDescuento());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return arrastrameTrip;
	}	

	public ArrastrameTrip listarTodoDescuento() throws LogicaImplException{
		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			List<Descuento> listaDescuento = factoryArrastrameTripDAO.getDescuentoRepository().findAll();

			if(listaDescuento !=null && !listaDescuento.isEmpty()){
				arrastrameTrip.setListaDescuento(listaDescuento);
			}else {
				throw new LogicaImplException("No existe lista de Descuento");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	public ArrastrameTrip listarDescuentoxFechaFinDescuento(String sFechaInicial, String sFechaFinal) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

//		Timestamp sFechaFinDescuentoA = DateMapper.mapperSimplyDateFormatYYYY_MM_DD_HH_MM_SSToTimeStamp(fechaFinDescuentoA);
//
//		Timestamp sFechaFinDescuentoB = DateMapper.mapperSimplyDateFormatYYYY_MM_DD_HH_MM_SSToTimeStamp(fechaFinDescuentoB);
		
		Timestamp tsInicial = zapalaClienteRest.convertirStrFechaConFormatToTimestamp(new ZapalaRequest(
				ArrastrameTripUtilidades.convertirStrFechaConFormatToTimestamp(
						sFechaInicial, factoryApiProperties.getConfigdata().getFechayyyyMMddTHHmmssZ()))).getTiempoStrtoTimeStamp();
		
		Timestamp tsFinal = zapalaClienteRest.convertirStrFechaConFormatToTimestamp(new ZapalaRequest(
				ArrastrameTripUtilidades.convertirStrFechaConFormatToTimestamp(
						sFechaFinal, factoryApiProperties.getConfigdata().getFechayyyyMMddTHHmmssZ()))).getTiempoStrtoTimeStamp();
		
		try {
			List<Descuento> listaDescuento = factoryArrastrameTripDAO.getDescuentoRepository().findByFechaFinDescuentoBetween(
					tsInicial, tsFinal);

			if(listaDescuento !=null && !listaDescuento.isEmpty()){
				arrastrameTrip.setListaDescuento(listaDescuento);
			}else {
				throw new LogicaImplException("No existe lista de Descuento");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	/***********************************************************/
	/******PasajeroArrastrame PasajeroArrastrame ***********/
	/***********************************************************/

	public ArrastrameTrip crearPasajeroArrastrame(PasajeroArrastrame objPasajeroArrastrame) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			Pageable pageByNombrePasajeroArrastrame = PageRequest.of(0, 1, Sort.by("idUsuario").descending());

			Page<PasajeroArrastrame> pageIdUsuarioArrastrame  =  
					factoryArrastrameTripDAO.getPasajeroArrastrameRepository().findByIdUsuario(objPasajeroArrastrame.getIdUsuario(), pageByNombrePasajeroArrastrame);

					if(pageIdUsuarioArrastrame.getContent().isEmpty()){
						Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idPasajeroArrastrame").descending());

						Page<PasajeroArrastrame> pageIdPasajeroArrastrame = factoryArrastrameTripDAO.getPasajeroArrastrameRepository().findAll(pageByidDesc);

						Integer idPasajeroArrastrame = (!pageIdPasajeroArrastrame.isEmpty()) ? (Integer) pageIdPasajeroArrastrame.getContent().get(0).getIdPasajeroArrastrame() + 1 : 1;
						/*Siempre hacer mayusculas los codigos**/
						objPasajeroArrastrame.setIdPasajeroArrastrame(idPasajeroArrastrame);

						factoryArrastrameTripDAO.getPasajeroArrastrameRepository().save(objPasajeroArrastrame);

						PasajeroArrastrame codPasajeroArrastrame = buscarPasajeroArrastramexIdUsuario(objPasajeroArrastrame).getPasajeroArrastrame(); 

						arrastrameTrip.setPasajeroArrastrame(codPasajeroArrastrame);
					}else {
						throw new LogicaImplException("No se puede crear PasajeroArrastrame, parametros ya existen en identificador valido");
					}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	public ArrastrameTrip actualizarPasajeroArrastrame(Integer idUsuario, PasajeroArrastrame objPasajeroArrastrame) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {

			Pageable pageByNombrePasajeroArrastrame = PageRequest.of(0, 1, Sort.by("idUsuario").descending());

			Page<PasajeroArrastrame> pageIdUsuarioArrastrame  = factoryArrastrameTripDAO.getPasajeroArrastrameRepository().
					findByIdUsuario(idUsuario, pageByNombrePasajeroArrastrame);

			if(pageIdUsuarioArrastrame.getContent().isEmpty()){

				objPasajeroArrastrame.setIdPasajeroArrastrame(idUsuario);

				factoryArrastrameTripDAO.getPasajeroArrastrameRepository().save(objPasajeroArrastrame);

				arrastrameTrip.setPasajeroArrastrame(objPasajeroArrastrame);
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	@Cacheable(value="buscarPasajeroArrastramexIdUsuario")
	public ArrastrameTrip buscarPasajeroArrastramexIdUsuario(PasajeroArrastrame objPasajeroArrastrame) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			Pageable pageByNombrePasajeroArrastrame = PageRequest.of(0, 1, Sort.by("idUsuario").descending());

			Page<PasajeroArrastrame> pageIdUsuarioArrastrame  = factoryArrastrameTripDAO.getPasajeroArrastrameRepository().
					findByIdUsuario(objPasajeroArrastrame.getIdUsuario(), pageByNombrePasajeroArrastrame);
			/***Si existe reemplazar por el nuevo*/
			if(!pageIdUsuarioArrastrame.isEmpty()){

				arrastrameTrip.setPasajeroArrastrame(pageIdUsuarioArrastrame.getContent().get(0));

			}else {
				throw new LogicaImplException("No existe PasajeroArrastrame con usuario:" +objPasajeroArrastrame.getIdUsuario());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return arrastrameTrip;
	}	

	public ArrastrameTrip listarTodoPasajeroArrastrame() throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			List<PasajeroArrastrame> listaPasajeroArrastrame = factoryArrastrameTripDAO.getPasajeroArrastrameRepository().findAll();

			if(listaPasajeroArrastrame !=null && !listaPasajeroArrastrame.isEmpty()){
				arrastrameTrip.setListaPasajeroArrastrame(listaPasajeroArrastrame);
			}else {
				throw new LogicaImplException("No existe lista de PasajeroArrastrame");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	/***********************************************************/
	/******Viaje Viaje *****************************************/
	/***********************************************************/
	public ArrastrameTrip generarCodigoViaje(Viaje objViaje) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			
			PasajeroArrastrame objPasajeroArrastrame = buscarPasajeroArrastramexIdUsuario(objViaje.getIdPasajeroArrastrame()).getPasajeroArrastrame();
			
			if(buscarRolPasajero(objPasajeroArrastrame.getIdRolPasajero()).getRolPasajero().getIdRolPasajero() > 0 && 
					buscarClasificacionPasajero(objPasajeroArrastrame.getIdClasificacionPasajero()).getClasificacionPasajero().getIdClasificacionPasajero() > 0){
				
				
				String codigoViaje = zapalaClienteRest.generarCodigoByNumeroByEncodear(new ZapalaRequest(
						ArrastrameTripUtilidades.crearListaCadenaCodigoNegocio(objPasajeroArrastrame, objViaje))).getCodigoGenerado();

				/**Buscar si el codigo existe*/
				Pageable pageByCodigoDesc = PageRequest.of(0, 1, Sort.by("codigoViaje").descending());
				/*****Buscar el ProductoFeeComision por codigo *****/
				Page<Viaje> pageCodigoViaje  = factoryArrastrameTripDAO.getViajeRepository().findByCodigoViaje(codigoViaje, pageByCodigoDesc);

				if(pageCodigoViaje.isEmpty()) {

					objViaje.setCodigoViaje(codigoViaje);

					arrastrameTrip.setViaje(objViaje);
				}
			}else {
				throw new LogicaImplException("No se puede crear codigo de viaje, datos de validacion erroneos");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

//	public ArrastrameTrip crearViaje(String idPasajeroArrastrame, Date dateViaje, Viaje objViaje) throws LogicaImplException {
	public ArrastrameTrip crearViaje(String idPasajeroArrastrame, Viaje objViaje) throws LogicaImplException {

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			//
			String sFechaViaje = ArrastrameTripUtilidades.convertirTimestamptoStr(objViaje.getTimeInicioViaje(), factoryApiProperties.getConfigdata().getFechayyyyMMddTHHmmssZ());
			
			List<Viaje> listaViaje =  buscarViajexTimeInicioMismoInstanteViaje(sFechaViaje).getListaViaje();
			
			PasajeroArrastrame objPasajeroArrastrame = (listaViaje!=null) ?
					
					buscarPasajeroArrastramexIdUsuario(new PasajeroArrastrame(Integer.parseInt(idPasajeroArrastrame))).getPasajeroArrastrame() : null;
					
					
					
					Timestamp tsInicial = zapalaClienteRest.convertirStrFechaConFormatToTimestamp(new ZapalaRequest(
							ArrastrameTripUtilidades.convertirStrFechaConFormatToTimestamp(
							new SimpleDateFormat(factoryApiProperties.getConfigdata().getFechayyyyMMddTHHmmssZ()).format(new Date()), 
							factoryApiProperties.getConfigdata().getFechayyyyMMddTHHmmssZ()))).getTiempoStrtoTimeStamp();


					if(objViaje.getCodigoViaje().equals(zapalaClienteRest.generarCodigoByNumeroByEncodear(
							new ZapalaRequest(ArrastrameTripUtilidades.
									crearListaCadenaCodigoNegocio(objPasajeroArrastrame, objViaje))).getCodigoGenerado())) {

						Pageable pageByNombreViaje = PageRequest.of(0, 1, Sort.by("codigoViaje").descending());

						Page<Viaje> pageCodigoViaje  = factoryArrastrameTripDAO.getViajeRepository().
								findByCodigoViaje(objViaje.getCodigoViaje(), pageByNombreViaje);


						Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idViaje").descending());

						Page<Viaje> pageIdViaje = (pageCodigoViaje.getContent().isEmpty()) ? factoryArrastrameTripDAO.getViajeRepository().findAll(pageByidDesc) : null;

						Integer idViaje = (pageIdViaje!=null && !pageIdViaje.isEmpty()) ? (Integer) pageIdViaje.getContent().get(0).getIdViaje() + 1 : 1;
						/*Siempre hacer mayusculas los codigos**/
						objViaje.setIdViaje(idViaje);

						objViaje.setFechaRegistroViaje(tsInicial);

						factoryArrastrameTripDAO.getViajeRepository().save(objViaje);

						Viaje codViaje = buscarViajexCodigoViaje(objViaje).getViaje(); 

						arrastrameTrip = crearStatusViaje(new StatusViaje(tsInicial, objViaje));

						arrastrameTrip.setViaje(codViaje);


					}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	public ArrastrameTrip actualizarViaje(Integer idUsuario, Viaje objViaje) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {

			Pageable pageByNombreViaje = PageRequest.of(0, 1, Sort.by("codigoViaje").descending());

			Page<Viaje> pageCodigoViaje  = factoryArrastrameTripDAO.getViajeRepository().
					findByCodigoViaje(objViaje.getCodigoViaje(), pageByNombreViaje);

			if(pageCodigoViaje.getContent().isEmpty()){

				objViaje.setIdViaje(idUsuario);

				factoryArrastrameTripDAO.getViajeRepository().save(objViaje);

				arrastrameTrip.setViaje(objViaje);
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	public ArrastrameTrip buscarViajexCodigoViaje(Viaje objViaje) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			Pageable pageByNombreViaje = PageRequest.of(0, 1, Sort.by("codigoViaje").descending());

			Page<Viaje> pageCodigoViaje  = factoryArrastrameTripDAO.getViajeRepository().
					findByCodigoViaje(objViaje.getCodigoViaje(), pageByNombreViaje);
			/***Si existe reemplazar por el nuevo*/
			if(!pageCodigoViaje.isEmpty()){

				arrastrameTrip.setViaje(pageCodigoViaje.getContent().get(0));

			}else {
				throw new LogicaImplException("No existe Viaje con usuario:" +objViaje.getCodigoViaje());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return arrastrameTrip;
	}
	
	/*Fecha en Formato yyyyMMddTHHmmssZ */
	public ArrastrameTrip buscarViajexTimeInicioMismoInstanteViaje(String sFechaViaje) throws LogicaImplException {

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			
			Timestamp timestamp = zapalaClienteRest.convertirStrFechaConFormatToTimestamp(new ZapalaRequest(
							ArrastrameTripUtilidades.convertirStrFechaConFormatToTimestamp(
									sFechaViaje, factoryApiProperties.getConfigdata().getFechayyyyMMddTHHmmssZ()))).getTiempoStrtoTimeStamp();
			
			Date dateUtil = new Date(timestamp.getTime());
			
			Date dateLimiteInf = ArrastrameTripUtilidades.agregarHoras(dateUtil, 6);
			
			Date dateLimiteSup = ArrastrameTripUtilidades.agregarHoras(dateUtil, -6);
			
			String sfechaViajeInf = new SimpleDateFormat(factoryApiProperties.getConfigdata().getFechayyyyMMddTHHmmssZ()).format(dateLimiteInf);
			
			String sfechaViajeSup = new SimpleDateFormat(factoryApiProperties.getConfigdata().getFechayyyyMMddTHHmmssZ()).format(dateLimiteSup);
			
			Timestamp timestampInf = zapalaClienteRest.convertirStrFechaConFormatToTimestamp(new ZapalaRequest(
					ArrastrameTripUtilidades.convertirStrFechaConFormatToTimestamp(
							sfechaViajeInf, factoryApiProperties.getConfigdata().getFechayyyyMMddTHHmmssZ()))).getTiempoStrtoTimeStamp();
			
			Timestamp timestampSup = zapalaClienteRest.convertirStrFechaConFormatToTimestamp(new ZapalaRequest(
					ArrastrameTripUtilidades.convertirStrFechaConFormatToTimestamp(
							sfechaViajeSup, factoryApiProperties.getConfigdata().getFechayyyyMMddTHHmmssZ()))).getTiempoStrtoTimeStamp();
			
			/*********************************/
			List<Viaje>  listaViaje = factoryArrastrameTripDAO.getViajeRepository().
					findByTimeInicioViajeBetween(timestampInf, timestampSup);
			/*** Si existe reemplazar por el nuevo ***/
			if(listaViaje == null || listaViaje.isEmpty()){

				arrastrameTrip.setListaViaje(null);

			}else {
				arrastrameTrip.setListaViaje(listaViaje);
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return arrastrameTrip;
	}



	public ArrastrameTrip listarTodoViaje() throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			List<Viaje> listaViaje = factoryArrastrameTripDAO.getViajeRepository().findAll();

			if(listaViaje !=null && !listaViaje.isEmpty()){
				arrastrameTrip.setListaViaje(listaViaje);
			}else {
				throw new LogicaImplException("No existe lista de Viaje");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	/****************************************************/
	/****** PasajeroArrastrame  PasajeroArrastrame ******/
	/****************************************************/
	public ArrastrameTrip crearStatusViaje(StatusViaje objStatusViaje) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		//Si no existe fecha declarada, se agrega la fecha
		if(objStatusViaje.getFechaStatusViaje()==null) {

			objStatusViaje.setFechaStatusViaje(new Timestamp(new Date().getTime()));
		}

		try {
			//validar  Que existe el id de Negocio;
			buscarViajexCodigoViaje(objStatusViaje.getIdViaje());

			/**Buscar si existe statusViaje asociado idViaje*/
			Pageable pageByCodigoDesc = PageRequest.of(0, 1, Sort.by("idStatusViaje").descending());

			Page<StatusViaje> pageStatusViaje  = factoryArrastrameTripDAO.getStatusViajeRepository().findByIdViaje(objStatusViaje.getIdViaje(), pageByCodigoDesc);
			//IdTipoStatusViaje()==3 es el maximo disponible para el flujo
			if(pageStatusViaje.isEmpty() || pageStatusViaje.getContent().get(0).getIdTipoStatusViaje().getIdTipoStatusViaje()<3){
				/**si no existe statusViaje asociado idViaje o existe pero el tipo status de negocio es menor que 3, obtener para crear*/
				Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idStatusViaje").descending());

				Page<StatusViaje> pageIdStatusViaje = factoryArrastrameTripDAO.getStatusViajeRepository().findAll(pageByidDesc);

				//Si existe status service aumentar en uno el contador.
				Integer idStatusViaje = (!pageIdStatusViaje.isEmpty()) ? (Integer) pageIdStatusViaje.getContent().get(0).getIdStatusViaje() + 1 : 1;

				Integer idTipoStatusViaje = (!pageStatusViaje.isEmpty() && pageStatusViaje.getContent().get(0)!=null && 
						pageStatusViaje.getContent().get(0).getIdTipoStatusViaje().getIdTipoStatusViaje()!= null && 
						pageStatusViaje.getContent().get(0).getIdTipoStatusViaje().getIdTipoStatusViaje() < 5 && (Integer) pageStatusViaje.getContent().get(0).getIdTipoStatusViaje().getIdTipoStatusViaje() < 5) ?
								(Integer) pageStatusViaje.getContent().get(0).getIdTipoStatusViaje().getIdTipoStatusViaje()+ 1: 1;

				objStatusViaje.setIdStatusViaje(idStatusViaje);

				objStatusViaje.setIdTipoStatusViaje(new TipoStatusViaje(idTipoStatusViaje));

				factoryArrastrameTripDAO.getStatusViajeRepository().save(objStatusViaje);

				pageStatusViaje  = factoryArrastrameTripDAO.getStatusViajeRepository().findByIdViaje(objStatusViaje.getIdViaje(), pageByCodigoDesc); 

				arrastrameTrip.setStatusViaje(pageStatusViaje.getContent().get(0));
			}else {
				throw new LogicaImplException("No se puede crear StatusViaje, parametros ya existen en identificador valido");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	public ArrastrameTrip listarStatusViajexIdViaje(StatusViaje objStatusViaje) throws LogicaImplException {

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			//Como la lista es pequeña, obtener ultimo status service por idViaje... ordenar descendiente para obtener el ultimo
			List<StatusViaje> listaStatusViaje  = factoryArrastrameTripDAO.getStatusViajeRepository().findByIdViaje(objStatusViaje.getIdViaje());

			/***Busqueda por nombre existe en un tipoStatusViaje No existe. o solo existe en el pageNombreTipoStatusViaje.idTipoStatusViaje = id 
			//... solo actualizar estado****/
			if(listaStatusViaje!=null){

				arrastrameTrip.setListaStatusViaje(listaStatusViaje);
			}
			else {
				throw new LogicaImplException("No existe StatusViaje para codigo de negocio:" +objStatusViaje.getIdViaje().getCodigoViaje());
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return arrastrameTrip;
	}

	/****************************************************/
	/****** DescuentoViaje  DescuentoViaje **************/
	/****************************************************/
	public ArrastrameTrip crearDescuentoViaje(DescuentoViaje objDescuentoViaje) throws LogicaImplException {
		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();
		//Si no existe fecha declarada, se agrega la fecha

		try {
			//validar  Que existe el id de Negocio;
			buscarDescuentoxCodigoDescuento(objDescuentoViaje.getIdDescuento());

			buscarViajexCodigoViaje(objDescuentoViaje.getIdViaje());

			/**Buscar si existe statusViaje asociado idViaje*/
			Pageable pageByCodigoDesc = PageRequest.of(0, 1, Sort.by("idViaje").descending());

			Page<DescuentoViaje> pageDescuentoViaje = factoryArrastrameTripDAO.getDescuentoViajeRepository().findByIdViaje(objDescuentoViaje.getIdViaje(), pageByCodigoDesc);
			//IdTipoStatusViaje()==3 es el maximo disponible para el flujo
			if(pageDescuentoViaje.getContent().isEmpty()){
				Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idDescuentoViaje").descending());

				Page<DescuentoViaje> pageIdDescuentoViaje = factoryArrastrameTripDAO.getDescuentoViajeRepository().findAll(pageByidDesc);

				Integer idDescuentoViaje = (!pageIdDescuentoViaje.isEmpty()) ? (Integer) pageIdDescuentoViaje.getContent().get(0).getIdDescuentoViaje() + 1 : 1;
				/*Siempre hacer mayusculas los codigos**/
				objDescuentoViaje.setIdDescuentoViaje(idDescuentoViaje);

				objDescuentoViaje.setEstadoDescuentoViaje(Boolean.parseBoolean(factoryApiProperties.getConfigdata().getEstadoActivoConsulta()));

				factoryArrastrameTripDAO.getDescuentoViajeRepository().save(objDescuentoViaje);

				DescuentoViaje codDescuentoViaje = buscarDescuentoViajexIdDescuentoxIdViaje(objDescuentoViaje).getDescuentoViaje(); 

				arrastrameTrip.setDescuentoViaje(codDescuentoViaje);
			}else {
				throw new LogicaImplException("No se puede crear DescuentoViaje, parametros ya existen en identificador valido");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return arrastrameTrip;
	}

	public ArrastrameTrip buscarDescuentoViajexIdDescuentoxIdViaje(DescuentoViaje objDescuentoViaje) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {

			if(objDescuentoViaje.getIdDescuento().getIdDescuento()>0 && objDescuentoViaje.getIdViaje().getIdViaje()>0) {

				DescuentoViaje descuentoViaje  = factoryArrastrameTripDAO.getDescuentoViajeRepository().
						findByIdDescuentoAndIdViaje(objDescuentoViaje.getIdDescuento(), objDescuentoViaje.getIdViaje());

				arrastrameTrip.setDescuentoViaje(descuentoViaje);

			}else {
				throw new LogicaImplException("No existe DescuentoViaje con descuento:" +objDescuentoViaje.getIdDescuento().getIdDescuento());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return arrastrameTrip;
	}	

	//Enviar un mensaje de alguna forma e indicar que se reomovio de la lista
	public ArrastrameTrip eliminarDescuentoViaje(Integer idUsuario, DescuentoViaje objDescuentoViaje) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {

			if(objDescuentoViaje.getIdDescuento().getIdDescuento()>0 && objDescuentoViaje.getIdViaje().getIdViaje()>0) {

				DescuentoViaje descuentoViaje  = factoryArrastrameTripDAO.getDescuentoViajeRepository().
						findByIdDescuentoAndIdViaje(objDescuentoViaje.getIdDescuento(), objDescuentoViaje.getIdViaje());

				if(descuentoViaje!=null){
					factoryArrastrameTripDAO.getDescuentoViajeRepository().delete(descuentoViaje);
				}
			}else {
				throw new LogicaImplException("No se puede actualizar DescuentoViaje, identificador ya existe");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	public ArrastrameTrip listarDescuentoViajexidViaje(DescuentoViaje objDescuentoViaje) throws LogicaImplException {

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			List<DescuentoViaje> listaDescuentoViaje = factoryArrastrameTripDAO.getDescuentoViajeRepository().findAll();

			if(listaDescuentoViaje !=null && !listaDescuentoViaje.isEmpty()){
				arrastrameTrip.setListaDescuentoViaje(listaDescuentoViaje);
			}else {
				throw new LogicaImplException("No existe lista de DescuentoViaje");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}


	/****************************************************/
	/****** DescuentoViaje  DescuentoViaje **************/
	/****************************************************/
	public ArrastrameTrip crearViajeRecomendado(ViajeRecomendado objViajeRecomendado) throws LogicaImplException {
		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();
		//Si no existe fecha declarada, se agrega la fecha

		try {
			//validar  Que existe el id de Negocio;
			buscarTipoViajeRecomendado(objViajeRecomendado.getIdTipoViajeRecomendado());

			buscarViajexCodigoViaje(objViajeRecomendado.getIdViaje());

			/**Buscar si existe statusViaje asociado idViaje*/
			Pageable pageByIdViajeRecomendado = PageRequest.of(0, 1, Sort.by("idViaje").descending());

			Page<ViajeRecomendado> pageViajeRecomendado = factoryArrastrameTripDAO.getViajeRecomendadoRepository().findByIdViaje(objViajeRecomendado.getIdViaje(), pageByIdViajeRecomendado);
			//IdTipoStatusViaje()==3 es el maximo disponible para el flujo
			if(pageViajeRecomendado.getContent().isEmpty()){
				Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idViajeRecomendado").descending());

				Page<ViajeRecomendado> pageIdViajeRecomendado = factoryArrastrameTripDAO.getViajeRecomendadoRepository().findAll(pageByidDesc);

				Integer idViajeRecomendado = (!pageIdViajeRecomendado.isEmpty()) ? (Integer) pageIdViajeRecomendado.getContent().get(0).getIdViajeRecomendado() + 1 : 1;
				/*Siempre hacer mayusculas los codigos**/
				objViajeRecomendado.setIdViajeRecomendado(idViajeRecomendado);

				factoryArrastrameTripDAO.getViajeRecomendadoRepository().save(objViajeRecomendado);

				DescuentoViaje codDescuentoViaje = buscarViajeRecomendadoxIdViaje(objViajeRecomendado).getDescuentoViaje(); 

				arrastrameTrip.setDescuentoViaje(codDescuentoViaje);
			}else {
				throw new LogicaImplException("No se puede crear DescuentoViaje, parametros ya existen en identificador valido");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return arrastrameTrip;
	}

	public ArrastrameTrip buscarViajeRecomendadoxIdViaje(ViajeRecomendado objViajeRecomendado) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {

			if(objViajeRecomendado.getIdViaje().getIdViaje()>0 && objViajeRecomendado.getIdViaje().getIdViaje()>0) {

				Pageable pageByIdViajeRecomendado = PageRequest.of(0, 1, Sort.by("idViaje").descending());

				Page<ViajeRecomendado> pageViajeRecomendado = factoryArrastrameTripDAO.getViajeRecomendadoRepository().findByIdViaje(objViajeRecomendado.getIdViaje(), pageByIdViajeRecomendado);

				arrastrameTrip.setViajeRecomendado(pageViajeRecomendado.getContent().get(0));

			}else {
				throw new LogicaImplException("No existe DescuentoViaje con descuento:" +objViajeRecomendado.getIdViaje().getIdViaje());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		return arrastrameTrip;
	}	

	//Enviar un mensaje de alguna forma e indicar que se reomovio de la lista
	public ArrastrameTrip listarViajeRecomendadoxidTipoViajeRecomendado(ViajeRecomendado objViajeRecomendado) throws LogicaImplException {

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {

			if(objViajeRecomendado.getIdTipoViajeRecomendado().getIdTipoViajeRecomendado()>0 && objViajeRecomendado.getIdTipoViajeRecomendado().getIdTipoViajeRecomendado()>0) {

				List<ViajeRecomendado> listaViajeRecomendado  = factoryArrastrameTripDAO.getViajeRecomendadoRepository().
						findByIdTipoViajeRecomendado(objViajeRecomendado.getIdTipoViajeRecomendado());

				if(listaViajeRecomendado!=null){
					arrastrameTrip.setListaViajeRecomendado(listaViajeRecomendado);
				}
			}else {
				throw new LogicaImplException("No se puede actualizar DescuentoViaje, identificador ya existe");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}
	/****************************************************/
	/****** PasajeroArrastrame  PasajeroArrastrame ******/
	/****************************************************/

	//Un Pasajero Activo de la asociado a un Viaje.
	public ArrastrameTrip crearPasajeroArrastrameViaje(PasajeroArrastrameViaje objPasajeroArrastrameViaje) throws LogicaImplException {

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			Pageable pageByIdViaje = PageRequest.of(0, 1, Sort.by("idViaje").descending());

			Page<PasajeroArrastrameViaje> pageIdViaje  = (objPasajeroArrastrameViaje.getIdViaje()!=null ) ? 
					factoryArrastrameTripDAO.getPasajeroArrastrameViajeRepository().findByIdViaje(objPasajeroArrastrameViaje.getIdViaje(), pageByIdViaje) : null;

					//El Pasajero no puede ser añadido si ya esta en un viaje diaponible

					if(pageIdViaje!=null && pageIdViaje.getContent().isEmpty()){
						Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idPasajeroArrastrameViaje").descending());

						Page<PasajeroArrastrameViaje> pageIdPasajeroArrastrameViaje = factoryArrastrameTripDAO.getPasajeroArrastrameViajeRepository().findAll(pageByidDesc);

						Integer idPasajeroArrastrameViaje = (!pageIdPasajeroArrastrameViaje.isEmpty()) ? (Integer) pageIdPasajeroArrastrameViaje.getContent().get(0).getIdPasajeroArrastrameViaje() + 1 : 1;

						/*Siempre hacer mayusculas los codigos**/
						objPasajeroArrastrameViaje.setIdPasajeroArrastrameViaje(idPasajeroArrastrameViaje);

						factoryArrastrameTripDAO.getPasajeroArrastrameViajeRepository().save(objPasajeroArrastrameViaje);

						List<PasajeroArrastrameViaje> listaPasajeroArrastrameViaje = 
								listarPasajeroArrastrameViajexidViaje(objPasajeroArrastrameViaje).getListaPasajeroArrastrameViaje();
						for(PasajeroArrastrameViaje pav :listaPasajeroArrastrameViaje) {
							arrastrameTrip.setPasajeroArrastrameViaje(pav);
							break;
						}

					}else {
						throw new LogicaImplException("No se puede crear PasajeroArrastrameViaje, parametros ya existen en identificador valido");
					}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	public ArrastrameTrip actualizarPasajeroArrastrameViaje (Integer idPasajero, PasajeroArrastrameViaje objPasajeroArrastrameViaje) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {

			if(objPasajeroArrastrameViaje.getIdViaje()!=null && objPasajeroArrastrameViaje.getIdViaje().getIdViaje()!=null) {


				Pageable pageByIdViaje = PageRequest.of(0, 1, Sort.by("idViaje").descending());

				Page<PasajeroArrastrameViaje> pageIdViaje  = factoryArrastrameTripDAO.getPasajeroArrastrameViajeRepository().
						findByIdViaje(objPasajeroArrastrameViaje.getIdViaje(), pageByIdViaje);

				if(pageIdViaje.getContent().isEmpty()){

					objPasajeroArrastrameViaje.setIdPasajeroArrastrameViaje(idPasajero);

					factoryArrastrameTripDAO.getPasajeroArrastrameViajeRepository().save(objPasajeroArrastrameViaje);

					arrastrameTrip.setPasajeroArrastrameViaje(objPasajeroArrastrameViaje);
				}
			}else {
				throw new LogicaImplException("No se puede actualizar PasajeroArrastrameViaje, identificador ya existe");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	public ArrastrameTrip eliminarPasajeroArrastrameViaje(Integer idPasajero, PasajeroArrastrameViaje objPasajeroArrastrameViaje) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {

			if(objPasajeroArrastrameViaje.getIdViaje()!=null && objPasajeroArrastrameViaje.getIdViaje().getIdViaje()!=null) {


				Pageable pageByIdViaje = PageRequest.of(0, 1, Sort.by("idViaje").descending());

				Page<PasajeroArrastrameViaje> pageIdViaje  = factoryArrastrameTripDAO.getPasajeroArrastrameViajeRepository().
						findByIdViaje(objPasajeroArrastrameViaje.getIdViaje(), pageByIdViaje);

				if(pageIdViaje.getContent().isEmpty()){

					objPasajeroArrastrameViaje.setIdPasajeroArrastrameViaje(idPasajero);

					factoryArrastrameTripDAO.getPasajeroArrastrameViajeRepository().delete(objPasajeroArrastrameViaje);

					arrastrameTrip.setPasajeroArrastrameViaje(objPasajeroArrastrameViaje);
				}
			}else {
				throw new LogicaImplException("No se puede actualizar PasajeroArrastrameViaje, identificador ya existe");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	public ArrastrameTrip listarPasajeroArrastrameViajexidViaje(PasajeroArrastrameViaje objPasajeroArrastrameViaje) throws LogicaImplException{

		ArrastrameTrip arrastrameTrip = new ArrastrameTrip();

		try {
			List<PasajeroArrastrameViaje> listaPasajeroArrastrameViaje = factoryArrastrameTripDAO.getPasajeroArrastrameViajeRepository().findAll();

			if(listaPasajeroArrastrameViaje !=null && !listaPasajeroArrastrameViaje.isEmpty()){
				arrastrameTrip.setListaPasajeroArrastrameViaje(listaPasajeroArrastrameViaje);
			}else {
				throw new LogicaImplException("No existe lista de PasajeroArrastrameViaje");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

		return arrastrameTrip;
	}

	public void limpiarCacheLocal() {
		evictBuscarTipoViajeRecomendado();
		evictListarTodoTipoViajeRecomendado();
		evictBuscarTipoVehiculoViaje();
		evictListarTodoTipoVehiculoViaje();
		evictBuscarTipoViaje();
		evictListarTodoTipoViaje();
		evictBuscarTipoStatusViaje();
		evictListarTodoTipoStatusViaje();
		evictBuscarClasificacionPasajero();
		evictListarTodoClasificacionPasajero();
		evictBuscarRolPasajero();
		evictListarTodoRolPasajero();
		evictBuscarDescuentoxCodigoDescuento();
		evictBuscarPasajeroArrastramexIdUsuario();
	}
	
	@CacheEvict(value="buscarTipoViajeRecomendado", allEntries=true)
	private void evictBuscarTipoViajeRecomendado() {
		System.out.println(factoryApiProperties.getCache().getEvictBuscarTipoViajeRecomendado());
	}
	
	@CacheEvict(value="listarTodoTipoViajeRecomendado", allEntries=true)
	private void evictListarTodoTipoViajeRecomendado() {
		System.out.println(factoryApiProperties.getCache().getEvictListarTodoTipoViajeRecomendado());
	}
	
	@CacheEvict(value="buscarTipoVehiculoViaje", allEntries=true)
	private void evictBuscarTipoVehiculoViaje() {
		System.out.println(factoryApiProperties.getCache().getEvictBuscarTipoVehiculoViaje());
	}
	
	@CacheEvict(value="listarTodoTipoVehiculoViaje", allEntries=true)
	private void evictListarTodoTipoVehiculoViaje() {
		System.out.println(factoryApiProperties.getCache().getEvictListarTodoTipoVehiculoViaje());
	}
	
	@CacheEvict(value="buscarTipoViaje", allEntries=true)
	private void evictBuscarTipoViaje() {
		System.out.println(factoryApiProperties.getCache().getEvictBuscarTipoViaje());
	}
	
	@CacheEvict(value="listarTodoTipoViaje", allEntries=true)
	private void evictListarTodoTipoViaje() {
		System.out.println(factoryApiProperties.getCache().getEvictListarTodoTipoViaje());
	}
	
	@CacheEvict(value="buscarTipoStatusViaje", allEntries=true)
	private void evictBuscarTipoStatusViaje() {
		System.out.println(factoryApiProperties.getCache().getEvictBuscarTipoStatusViaje());
	}
	
	@CacheEvict(value="listarTodoTipoStatusViaje", allEntries=true)
	private void evictListarTodoTipoStatusViaje() {
		System.out.println(factoryApiProperties.getCache().getEvictListarTodoTipoStatusViaje());
	}
	
	@CacheEvict(value="buscarClasificacionPasajero", allEntries=true)
	private void evictBuscarClasificacionPasajero() {
		System.out.println(factoryApiProperties.getCache().getEvictBuscarClasificacionPasajero());
	}
	
	@CacheEvict(value="listarTodoClasificacionPasajero", allEntries=true)
	private void evictListarTodoClasificacionPasajero() {
		System.out.println(factoryApiProperties.getCache().getEvictListarTodoClasificacionPasajero());
	}
	
	@CacheEvict(value="buscarRolPasajero", allEntries=true)
	private void evictBuscarRolPasajero() {
		System.out.println(factoryApiProperties.getCache().getEvictBuscarRolPasajero());
	}
	
	@CacheEvict(value="listarTodoRolPasajero", allEntries=true)
	private void evictListarTodoRolPasajero() {
		System.out.println(factoryApiProperties.getCache().getEvictListarTodoRolPasajero());
	}
	
	@CacheEvict(value="buscarDescuentoxCodigoDescuento", allEntries=true)
	private void evictBuscarDescuentoxCodigoDescuento() {
		System.out.println(factoryApiProperties.getCache().getEvictBuscarDescuentoxCodigoDescuento());
	}
	
	@CacheEvict(value="buscarPasajeroArrastramexIdUsuario", allEntries=true)
	private void evictBuscarPasajeroArrastramexIdUsuario() {
		System.out.println(factoryApiProperties.getCache().getEvictBuscarPasajeroArrastramexIdUsuario());
	}
}
