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
public class CompartetripServiceImpl implements CompartetripService {
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
	public TipoViajeRecomendado crearTipoViajeRecomendado(TipoViajeRecomendado objTipoViajeRecomendado) throws LogicaImplException{
		
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

				return buscarTipoViajeRecomendado(objTipoViajeRecomendado); 

			}else {
				throw new LogicaImplException("No se puede actualizar TipoViajeRecomendado, parametros inválidos");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

	}

	public TipoViajeRecomendado actualizarTipoViajeRecomendado(Integer id, TipoViajeRecomendado objTipoViajeRecomendado) throws LogicaImplException{

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

				return buscarTipoViajeRecomendado(objTipoViajeRecomendado); 
			}
			else {
				throw new LogicaImplException("No se puede actualizar TipoViajeRecomendado, parametros ya existen en un identificador distinto");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}

	@Cacheable(value="buscarTipoViajeRecomendado")
	public TipoViajeRecomendado buscarTipoViajeRecomendado(TipoViajeRecomendado objTipoViajeRecomendado) throws LogicaImplException{

		try {	

			Optional<TipoViajeRecomendado> optPerTipoViajeRecomendado = factoryArrastrameTripDAO.getTipoViajeRecomendadoRepository().findById(objTipoViajeRecomendado.getIdTipoViajeRecomendado());

			/***Si existe reemplazar por el nuevo*/
			if(optPerTipoViajeRecomendado!=null && optPerTipoViajeRecomendado.isPresent()){

				return optPerTipoViajeRecomendado.get();

			}else {
				throw new LogicaImplException("No existe TipoViajeRecomendado con identificador:" +objTipoViajeRecomendado.getIdTipoViajeRecomendado());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		
	}	

	//	@Cacheable(value="listarTodoTipoViajeRecomendado")
	public List<TipoViajeRecomendado> listarTodoTipoViajeRecomendado() throws LogicaImplException{


		try {
			List<TipoViajeRecomendado> listaTipoViajeRecomendado = factoryArrastrameTripDAO.getTipoViajeRecomendadoRepository().findAll();

			if(listaTipoViajeRecomendado !=null && !listaTipoViajeRecomendado.isEmpty()){
				return listaTipoViajeRecomendado;
			}else {
				throw new LogicaImplException("No existe lista de TipoViajeRecomendado");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}

	/***********************************************************/
	/******TipoVehiculoViaje TipoVehiculoViaje TipoVehiculoViaje TipoVehiculoViaje *****/
	/***********************************************************/
	public TipoVehiculoViaje crearTipoVehiculoViaje(TipoVehiculoViaje objTipoVehiculoViaje) throws LogicaImplException{

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

				return buscarTipoVehiculoViaje(objTipoVehiculoViaje); 

			}else {
				throw new LogicaImplException("No se puede actualizar TipoVehiculoViaje, parametros inválidos");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

	}

	public TipoVehiculoViaje actualizarTipoVehiculoViaje(Integer id, TipoVehiculoViaje objTipoVehiculoViaje) throws LogicaImplException{

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

				return buscarTipoVehiculoViaje(objTipoVehiculoViaje); 
			}
			else {
				throw new LogicaImplException("No se puede actualizar TipoVehiculoViaje, parametros ya existen en un identificador distinto");
			}


		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

	}

	//	@Cacheable(value="buscarTipoVehiculoViaje")
	public TipoVehiculoViaje buscarTipoVehiculoViaje(TipoVehiculoViaje objTipoVehiculoViaje) throws LogicaImplException{

		try {	

			Optional<TipoVehiculoViaje> optPerTipoVehiculoViaje = factoryArrastrameTripDAO.getTipoVehiculoViajeRepository().findById(objTipoVehiculoViaje.getIdTipoVehiculoViaje());

			/***Si existe reemplazar por el nuevo*/
			if(optPerTipoVehiculoViaje!=null && optPerTipoVehiculoViaje.isPresent()){

				return optPerTipoVehiculoViaje.get();

			}else {
				throw new LogicaImplException("No existe TipoVehiculoViaje con identificador:" +objTipoVehiculoViaje.getIdTipoVehiculoViaje());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}	

	@Cacheable(value="listarTodoTipoVehiculoViaje")
	public List<TipoVehiculoViaje> listarTodoTipoVehiculoViaje() throws LogicaImplException{

		try {
			List<TipoVehiculoViaje> listaTipoVehiculoViaje = factoryArrastrameTripDAO.getTipoVehiculoViajeRepository().findAll();

			if(listaTipoVehiculoViaje !=null && !listaTipoVehiculoViaje.isEmpty()){
				return listaTipoVehiculoViaje;
			}else {
				throw new LogicaImplException("No existe lista de TipoVehiculoViaje");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

	}

	/***********************************************************/
	/******TipoViaje TipoViaje TipoViaje TipoViaje *****/
	/***********************************************************/

	public TipoViaje crearTipoViaje(TipoViaje objTipoViaje) throws LogicaImplException{

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

				return buscarTipoViaje(objTipoViaje); 

				
			}else {
				throw new LogicaImplException("No se puede actualizar TipoViaje, parametros inválidos");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}

	public TipoViaje actualizarTipoViaje(Integer id, TipoViaje objTipoViaje) throws LogicaImplException{


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

				return buscarTipoViaje(objTipoViaje); 
			}
			else {
				throw new LogicaImplException("No se puede actualizar TipoViaje, parametros ya existen en un identificador distinto");
			}


		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

	}

	@Cacheable(value="buscarTipoViaje")
	public TipoViaje buscarTipoViaje(TipoViaje objTipoViaje) throws LogicaImplException{

		try {	

			Optional<TipoViaje> optPerTipoViaje = factoryArrastrameTripDAO.getTipoViajeRepository().findById(objTipoViaje.getIdTipoViaje());

			/***Si existe reemplazar por el nuevo*/
			if(optPerTipoViaje!=null && optPerTipoViaje.isPresent()){

				return optPerTipoViaje.get();

			}else {
				throw new LogicaImplException("No existe TipoViaje con identificador:" +objTipoViaje.getIdTipoViaje());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}	

	@Cacheable(value="listarTodoTipoViaje")
	public List<TipoViaje> listarTodoTipoViaje() throws LogicaImplException{

		try {
			List<TipoViaje> listaTipoViaje = factoryArrastrameTripDAO.getTipoViajeRepository().findAll();

			if(listaTipoViaje !=null && !listaTipoViaje.isEmpty()){
				return listaTipoViaje;
			}else {
				throw new LogicaImplException("No existe lista de TipoViaje");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

	}

	public TipoStatusViaje crearTipoStatusViaje(TipoStatusViaje objTipoStatusViaje) throws LogicaImplException{

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

				return buscarTipoStatusViaje(objTipoStatusViaje); 

			}else {
				throw new LogicaImplException("No se puede actualizar TipoStatusViaje, parametros inválidos");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}

	public TipoStatusViaje actualizarTipoStatusViaje(Integer id, TipoStatusViaje objTipoStatusViaje) throws LogicaImplException{


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
				
				return buscarTipoStatusViaje(objTipoStatusViaje); 
			}
			else {
				throw new LogicaImplException("No se puede actualizar TipoStatusViaje, parametros ya existen en un identificador distinto");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}

	@Cacheable(value="buscarTipoStatusViaje")
	public TipoStatusViaje buscarTipoStatusViaje(TipoStatusViaje objTipoStatusViaje) throws LogicaImplException{

		try {	

			Optional<TipoStatusViaje> optPerTipoStatusViaje = factoryArrastrameTripDAO.getTipoStatusViajeRepository().findById(objTipoStatusViaje.getIdTipoStatusViaje());

			/***Si existe reemplazar por el nuevo*/
			if(optPerTipoStatusViaje!=null && optPerTipoStatusViaje.isPresent()){

				return optPerTipoStatusViaje.get();

			}else {
				throw new LogicaImplException("No existe TipoStatusViaje con identificador:" +objTipoStatusViaje.getIdTipoStatusViaje());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		
	}	

	@Cacheable(value="listarTodoTipoStatusViaje")
	public List<TipoStatusViaje> listarTodoTipoStatusViaje() throws LogicaImplException{

		try {
			List<TipoStatusViaje> listaTipoStatusViaje = factoryArrastrameTripDAO.getTipoStatusViajeRepository().findAll();

			if(listaTipoStatusViaje !=null && !listaTipoStatusViaje.isEmpty()){
				return listaTipoStatusViaje;
			}else {
				throw new LogicaImplException("No existe lista de TipoStatusViaje");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

	}

	/***********************************************************/
	/******ClasificacionPasajero ClasificacionPasajero ClasificacionPasajero ClasificacionPasajero *****/
	/***********************************************************/

	public ClasificacionPasajero crearClasificacionPasajero(ClasificacionPasajero objClasificacionPasajero) throws LogicaImplException{

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

						return buscarClasificacionPasajero(objClasificacionPasajero); 

					}else {
						throw new LogicaImplException("No se puede crear ClasificacionPasajero, parametros ya existen en identificador valido");
					}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

	}

	public ClasificacionPasajero actualizarClasificacionPasajero(Integer id, ClasificacionPasajero objClasificacionPasajero) throws LogicaImplException{

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

				return buscarClasificacionPasajero(objClasificacionPasajero); 
			}
			else {
				throw new LogicaImplException("No se puede actualizar ClasificacionPasajero, parametros ya existen en un identificador distinto");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

	}

	@Cacheable(value="buscarClasificacionPasajero")
	public ClasificacionPasajero buscarClasificacionPasajero(ClasificacionPasajero objClasificacionPasajero) throws LogicaImplException{

		try {	

			Optional<ClasificacionPasajero> optPerClasificacionPasajero = factoryArrastrameTripDAO.getClasificacionPasajeroRepository().findById(objClasificacionPasajero.getIdClasificacionPasajero());

			/***Si existe reemplazar por el nuevo*/
			if(optPerClasificacionPasajero!=null && optPerClasificacionPasajero.isPresent()){

				return optPerClasificacionPasajero.get();

			}else {
				throw new LogicaImplException("No existe ClasificacionPasajero con identificador:" +objClasificacionPasajero.getIdClasificacionPasajero());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}	

	@Cacheable(value="listarTodoClasificacionPasajero")
	public List<ClasificacionPasajero> listarTodoClasificacionPasajero() throws LogicaImplException{

		try {
			List<ClasificacionPasajero> listaClasificacionPasajero = factoryArrastrameTripDAO.getClasificacionPasajeroRepository().findAll();

			if(listaClasificacionPasajero !=null && !listaClasificacionPasajero.isEmpty()){
				return listaClasificacionPasajero;
			}else {
				throw new LogicaImplException("No existe lista de ClasificacionPasajero");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}

	/***********************************************************/
	/******RolPasajero RolPasajero RolPasajero RolPasajero *****/
	/***********************************************************/

	public RolPasajero crearRolPasajero(RolPasajero objRolPasajero) throws LogicaImplException{

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

						return buscarRolPasajero(objRolPasajero); 
					}else {
						throw new LogicaImplException("No se puede crear RolPasajero, parametros ya existen en identificador valido");
					}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}

	public RolPasajero actualizarRolPasajero(Integer id, RolPasajero objRolPasajero) throws LogicaImplException{


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

				return buscarRolPasajero(objRolPasajero);
			}
			else {
				throw new LogicaImplException("No se puede actualizar RolPasajero, parametros ya existen en un identificador distinto");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

	}

	@Cacheable(value="buscarRolPasajero")
	public RolPasajero buscarRolPasajero(RolPasajero objRolPasajero) throws LogicaImplException{

		try {	

			Optional<RolPasajero> optPerRolPasajero = factoryArrastrameTripDAO.getRolPasajeroRepository().findById(objRolPasajero.getIdRolPasajero());

			/***Si existe reemplazar por el nuevo*/
			if(optPerRolPasajero!=null && optPerRolPasajero.isPresent()){

				return optPerRolPasajero.get();

			}else {
				throw new LogicaImplException("No existe RolPasajero con identificador:" +objRolPasajero.getIdRolPasajero());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}	

	@Cacheable(value="listarTodoRolPasajero")
	public List<RolPasajero> listarTodoRolPasajero() throws LogicaImplException{

		try {
			List<RolPasajero> listaRolPasajero = factoryArrastrameTripDAO.getRolPasajeroRepository().findAll();

			if(listaRolPasajero !=null && !listaRolPasajero.isEmpty()){
				return listaRolPasajero;
			}else {
				throw new LogicaImplException("No existe lista de RolPasajero");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}

	/*******************************************/
	/****** Descuento Descuento Descuento ******/
	/*******************************************/
	public Descuento crearDescuento(Descuento objDescuento) throws LogicaImplException{

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

				return buscarDescuentoxCodigoDescuento(objDescuento); 

			}else {
				throw new LogicaImplException("No se puede crear Descuento, parametros ya existen en identificador valido");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}

	public Descuento actualizarDescuento(Integer idUsuario, Descuento objDescuento) throws LogicaImplException {

		try {
			Pageable pageByNombreDescuento = PageRequest.of(0, 1, Sort.by("codigoDescuento").descending());

			Page<Descuento> pageCodigoDescuento  = factoryArrastrameTripDAO.getDescuentoRepository().
					findByCodigoDescuento(objDescuento.getCodigoDescuento(), pageByNombreDescuento);

			if(pageCodigoDescuento.getContent().isEmpty()){

				objDescuento.setIdDescuento(idUsuario);

				factoryArrastrameTripDAO.getDescuentoRepository().save(objDescuento);

				return buscarDescuentoxCodigoDescuento(objDescuento);
			}else {
				throw new LogicaImplException("No se puede crear Descuento, parametros ya existen en identificador valido");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

	}

	@Cacheable(value="buscarDescuentoxCodigoDescuento")
	public Descuento buscarDescuentoxCodigoDescuento(Descuento objDescuento) throws LogicaImplException{

		try {
			Pageable pageByNombreDescuento = PageRequest.of(0, 1, Sort.by("codigoDescuento").descending());

			Page<Descuento> pageCodigoDescuento  = factoryArrastrameTripDAO.getDescuentoRepository().
					findByCodigoDescuento(objDescuento.getCodigoDescuento(), pageByNombreDescuento);
			/***Si existe reemplazar por el nuevo*/
			if(!pageCodigoDescuento.isEmpty()){

				return pageCodigoDescuento.getContent().get(0);

			}else {
				throw new LogicaImplException("No existe TipoNegocio con usuario:" +objDescuento.getCodigoDescuento());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}	

	public List<Descuento> listarTodoDescuento() throws LogicaImplException{

		try {
			List<Descuento> listaDescuento = factoryArrastrameTripDAO.getDescuentoRepository().findAll();

			if(listaDescuento !=null && !listaDescuento.isEmpty()){
				return listaDescuento;
			}else {
				throw new LogicaImplException("No existe lista de Descuento");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}

	public List<Descuento> listarDescuentoxFechaFinDescuento(String sFechaInicial, String sFechaFinal) throws LogicaImplException{

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
				return listaDescuento;
			}else {
				throw new LogicaImplException("No existe lista de Descuento");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}

	/***********************************************************/
	/******PasajeroArrastrame PasajeroArrastrame ***********/
	/***********************************************************/

	public PasajeroArrastrame crearPasajeroArrastrame(PasajeroArrastrame objPasajeroArrastrame) throws LogicaImplException{

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

						return buscarPasajeroArrastramexIdUsuario(objPasajeroArrastrame); 

					}else {
						throw new LogicaImplException("No se puede crear PasajeroArrastrame, parametros ya existen en identificador valido");
					}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

	}

	public PasajeroArrastrame actualizarPasajeroArrastrame(Integer idUsuario, PasajeroArrastrame objPasajeroArrastrame) throws LogicaImplException{


		try {

			Pageable pageByNombrePasajeroArrastrame = PageRequest.of(0, 1, Sort.by("idUsuario").descending());

			Page<PasajeroArrastrame> pageIdUsuarioArrastrame  = factoryArrastrameTripDAO.getPasajeroArrastrameRepository().
					findByIdUsuario(idUsuario, pageByNombrePasajeroArrastrame);

			if(pageIdUsuarioArrastrame.getContent().isEmpty()){

				objPasajeroArrastrame.setIdPasajeroArrastrame(idUsuario);

				factoryArrastrameTripDAO.getPasajeroArrastrameRepository().save(objPasajeroArrastrame);

				return buscarPasajeroArrastramexIdUsuario(objPasajeroArrastrame); 
			}else {
				throw new LogicaImplException("No se puede actualizar PasajeroArrastrame, parametros incorrectos");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}

	@Cacheable(value="buscarPasajeroArrastramexIdUsuario")
	public PasajeroArrastrame buscarPasajeroArrastramexIdUsuario(PasajeroArrastrame objPasajeroArrastrame) throws LogicaImplException{

		try {
			Pageable pageByNombrePasajeroArrastrame = PageRequest.of(0, 1, Sort.by("idUsuario").descending());

			Page<PasajeroArrastrame> pageIdUsuarioArrastrame  = factoryArrastrameTripDAO.getPasajeroArrastrameRepository().
					findByIdUsuario(objPasajeroArrastrame.getIdUsuario(), pageByNombrePasajeroArrastrame);
			/***Si existe reemplazar por el nuevo*/
			if(!pageIdUsuarioArrastrame.isEmpty()){

				return pageIdUsuarioArrastrame.getContent().get(0);

			}else {
				throw new LogicaImplException("No existe PasajeroArrastrame con usuario:" +objPasajeroArrastrame.getIdUsuario());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}	

	public List<PasajeroArrastrame> listarTodoPasajeroArrastrame() throws LogicaImplException{

		try {
			List<PasajeroArrastrame> listaPasajeroArrastrame = factoryArrastrameTripDAO.getPasajeroArrastrameRepository().findAll();

			if(listaPasajeroArrastrame !=null && !listaPasajeroArrastrame.isEmpty()){
				return listaPasajeroArrastrame;
			}else {
				throw new LogicaImplException("No existe lista de PasajeroArrastrame");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

	}

	/***********************************************************/
	/******Viaje Viaje *****************************************/
	/***********************************************************/
	public Viaje generarCodigoViaje(Viaje objViaje) throws LogicaImplException{

		try {
			
			PasajeroArrastrame objPasajeroArrastrame = buscarPasajeroArrastramexIdUsuario(objViaje.getIdPasajeroArrastrame());
			
			if(buscarRolPasajero(objPasajeroArrastrame.getIdRolPasajero()).getIdRolPasajero() > 0 && 
					buscarClasificacionPasajero(objPasajeroArrastrame.getIdClasificacionPasajero()).getIdClasificacionPasajero() > 0){
				
				
				String codigoViaje = zapalaClienteRest.generarCodigoByNumeroByEncodear(new ZapalaRequest(
						ArrastrameTripUtilidades.crearListaCadenaCodigoNegocio(objPasajeroArrastrame, objViaje))).getCodigoGenerado();

				/**Buscar si el codigo existe*/
				Pageable pageByCodigoDesc = PageRequest.of(0, 1, Sort.by("codigoViaje").descending());
				/*****Buscar el ProductoFeeComision por codigo *****/
				Page<Viaje> pageCodigoViaje  = factoryArrastrameTripDAO.getViajeRepository().findByCodigoViaje(codigoViaje, pageByCodigoDesc);

				if(pageCodigoViaje.isEmpty()) {

					objViaje.setCodigoViaje(codigoViaje);

					return objViaje;
				}else {
					throw new LogicaImplException("No se puede crear codigo de viaje, datos de validacion erroneos");
				}
			}else {
				throw new LogicaImplException("No se puede crear codigo de viaje, datos de validacion erroneos");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

	}

//	public ArrastrameTrip crearViaje(String idPasajeroArrastrame, Date dateViaje, Viaje objViaje) throws LogicaImplException {
	public Viaje crearViaje(Integer idPasajeroArrastrame, Viaje objViaje) throws LogicaImplException {


		try {
			//
			String sFechaViaje = ArrastrameTripUtilidades.convertirTimestamptoStr(objViaje.getTimeInicioViaje(), factoryApiProperties.getConfigdata().getFechayyyyMMddTHHmmssZ());
			
			List<Viaje> listaViaje =  listarViajexTimeInicioMismoInstanteViaje(sFechaViaje);
			
			PasajeroArrastrame objPasajeroArrastrame = (listaViaje!=null) ?
					
					buscarPasajeroArrastramexIdUsuario(new PasajeroArrastrame(idPasajeroArrastrame)) : null;
					
					
					
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

//						Viaje codViaje = buscarViajexCodigoViaje(objViaje).getViaje(); 
//
//						arrastrameTrip = crearStatusViaje(new StatusViaje(tsInicial, objViaje));
//
//						arrastrameTrip.setViaje(codViaje);
						
						crearStatusViaje(new StatusViaje(tsInicial, objViaje));
						
						return buscarViajexCodigoViaje(objViaje); 


					}else {
						throw new LogicaImplException("No se puede crear viaje, datos de creacion son erroneos");
					}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

	}

	public Viaje actualizarViaje(Integer idUsuario, Viaje objViaje) throws LogicaImplException{

		try {

			Pageable pageByNombreViaje = PageRequest.of(0, 1, Sort.by("codigoViaje").descending());

			Page<Viaje> pageCodigoViaje  = factoryArrastrameTripDAO.getViajeRepository().
					findByCodigoViaje(objViaje.getCodigoViaje(), pageByNombreViaje);

			if(pageCodigoViaje.getContent().isEmpty()){

				objViaje.setIdViaje(idUsuario);

				factoryArrastrameTripDAO.getViajeRepository().save(objViaje);

				return objViaje;
			}else {
				throw new LogicaImplException("No se puede actualizar viaje, datos de actualizacion son erroneos");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}

	public Viaje buscarViajexCodigoViaje(Viaje objViaje) throws LogicaImplException{

		try {
			Pageable pageByNombreViaje = PageRequest.of(0, 1, Sort.by("codigoViaje").descending());

			Page<Viaje> pageCodigoViaje  = factoryArrastrameTripDAO.getViajeRepository().
					findByCodigoViaje(objViaje.getCodigoViaje(), pageByNombreViaje);
			/***Si existe reemplazar por el nuevo*/
			if(!pageCodigoViaje.isEmpty()){

				return pageCodigoViaje.getContent().get(0);

			}else {
				throw new LogicaImplException("No existe Viaje con usuario:" +objViaje.getCodigoViaje());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}
	
	/*Fecha en Formato yyyyMMddTHHmmssZ */
	public List<Viaje> listarViajexTimeInicioMismoInstanteViaje(String sFechaViaje) throws LogicaImplException {

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

				return null;

			}else {
				return listaViaje;
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}



	public List<Viaje> listarTodoViaje() throws LogicaImplException{

		try {
			List<Viaje> listaViaje = factoryArrastrameTripDAO.getViajeRepository().findAll();

			if(listaViaje !=null && !listaViaje.isEmpty()){
				return listaViaje;
			}else {
				throw new LogicaImplException("No existe lista de Viaje");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}

	/****************************************************/
	/****** PasajeroArrastrame  PasajeroArrastrame ******/
	/****************************************************/
	public StatusViaje crearStatusViaje(StatusViaje objStatusViaje) throws LogicaImplException{

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

				return pageStatusViaje.getContent().get(0);
			}else {
				throw new LogicaImplException("No se puede crear StatusViaje, parametros ya existen en identificador valido");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}

	public List<StatusViaje> listarStatusViajexIdViaje(StatusViaje objStatusViaje) throws LogicaImplException {

		try {
			//Como la lista es pequeña, obtener ultimo status service por idViaje... ordenar descendiente para obtener el ultimo
			List<StatusViaje> listaStatusViaje  = factoryArrastrameTripDAO.getStatusViajeRepository().findByIdViaje(objStatusViaje.getIdViaje());

			/***Busqueda por nombre existe en un tipoStatusViaje No existe. o solo existe en el pageNombreTipoStatusViaje.idTipoStatusViaje = id 
			//... solo actualizar estado****/
			if(listaStatusViaje!=null){

				return listaStatusViaje;
			}
			else {
				throw new LogicaImplException("No existe StatusViaje para codigo de negocio:" +objStatusViaje.getIdViaje().getCodigoViaje());
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

	}

	/****************************************************/
	/****** DescuentoViaje  DescuentoViaje **************/
	/****************************************************/
	public DescuentoViaje crearDescuentoViaje(DescuentoViaje objDescuentoViaje) throws LogicaImplException {
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

				return buscarDescuentoViajexIdDescuentoxIdViaje(objDescuentoViaje); 


			}else {
				throw new LogicaImplException("No se puede crear DescuentoViaje, parametros ya existen en identificador valido");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}

	public DescuentoViaje buscarDescuentoViajexIdDescuentoxIdViaje(DescuentoViaje objDescuentoViaje) throws LogicaImplException{

		try {

			if(objDescuentoViaje.getIdDescuento().getIdDescuento()>0 && objDescuentoViaje.getIdViaje().getIdViaje()>0) {

				DescuentoViaje descuentoViaje  = factoryArrastrameTripDAO.getDescuentoViajeRepository().
						findByIdDescuentoAndIdViaje(objDescuentoViaje.getIdDescuento(), objDescuentoViaje.getIdViaje());

				return descuentoViaje;

			}else {
				throw new LogicaImplException("No existe DescuentoViaje con descuento:" +objDescuentoViaje.getIdDescuento().getIdDescuento());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	
	}	

	//Enviar un mensaje de alguna forma e indicar que se reomovio de la lista
	public DescuentoViaje eliminarDescuentoViaje(Integer idUsuario, DescuentoViaje objDescuentoViaje) throws LogicaImplException{

		try {

			if(objDescuentoViaje.getIdDescuento().getIdDescuento()>0 && objDescuentoViaje.getIdViaje().getIdViaje()>0) {

				DescuentoViaje descuentoViaje  = factoryArrastrameTripDAO.getDescuentoViajeRepository().
						findByIdDescuentoAndIdViaje(objDescuentoViaje.getIdDescuento(), objDescuentoViaje.getIdViaje());

				if(descuentoViaje!=null){
					factoryArrastrameTripDAO.getDescuentoViajeRepository().delete(descuentoViaje);
					return descuentoViaje;
				}else {
					throw new LogicaImplException("No se puede actualizar DescuentoViaje, identificador ya existe");
				}
			}else {
				throw new LogicaImplException("No se puede actualizar DescuentoViaje, identificador ya existe");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}

	public List<DescuentoViaje> listarDescuentoViajexidViaje(DescuentoViaje objDescuentoViaje) throws LogicaImplException {

		try {
			List<DescuentoViaje> listaDescuentoViaje = factoryArrastrameTripDAO.getDescuentoViajeRepository().findAll();

			if(listaDescuentoViaje !=null && !listaDescuentoViaje.isEmpty()){
				return listaDescuentoViaje;
			}else {
				throw new LogicaImplException("No existe lista de DescuentoViaje");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

	}


	/****************************************************/
	/****** DescuentoViaje  DescuentoViaje **************/
	/****************************************************/
	public ViajeRecomendado crearViajeRecomendado(ViajeRecomendado objViajeRecomendado) throws LogicaImplException {
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

				return buscarViajeRecomendadoxIdViaje(objViajeRecomendado); 
			}else {
				throw new LogicaImplException("No se puede crear DescuentoViaje, parametros ya existen en identificador valido");
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}

	public ViajeRecomendado buscarViajeRecomendadoxIdViaje(ViajeRecomendado objViajeRecomendado) throws LogicaImplException{

		try {

			if(objViajeRecomendado.getIdViaje().getIdViaje()>0 && objViajeRecomendado.getIdViaje().getIdViaje()>0) {

				Pageable pageByIdViajeRecomendado = PageRequest.of(0, 1, Sort.by("idViaje").descending());

				Page<ViajeRecomendado> pageViajeRecomendado = factoryArrastrameTripDAO.getViajeRecomendadoRepository().findByIdViaje(objViajeRecomendado.getIdViaje(), pageByIdViajeRecomendado);

				return pageViajeRecomendado.getContent().get(0);

			}else {
				throw new LogicaImplException("No existe DescuentoViaje con descuento:" +objViajeRecomendado.getIdViaje().getIdViaje());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
		
	}	

	//Enviar un mensaje de alguna forma e indicar que se reomovio de la lista
	public List<ViajeRecomendado> listarViajeRecomendadoxidTipoViajeRecomendado(ViajeRecomendado objViajeRecomendado) throws LogicaImplException {

		try {

			if(objViajeRecomendado.getIdTipoViajeRecomendado().getIdTipoViajeRecomendado()>0 && objViajeRecomendado.getIdTipoViajeRecomendado().getIdTipoViajeRecomendado()>0) {

				List<ViajeRecomendado> listaViajeRecomendado  = factoryArrastrameTripDAO.getViajeRecomendadoRepository().
						findByIdTipoViajeRecomendado(objViajeRecomendado.getIdTipoViajeRecomendado());

				if(listaViajeRecomendado!=null){
					return listaViajeRecomendado;
				}else {
					throw new LogicaImplException("No se puede obtener lista de ViajeRecomendado, parametros incorrectos");
				}
			}else {
				throw new LogicaImplException("No se puede obtener lista de ViajeRecomendado, parametros incorrectos");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

	}
	/****************************************************/
	/****** PasajeroArrastrame  PasajeroArrastrame ******/
	/****************************************************/

	//Un Pasajero Activo de la asociado a un Viaje.
	public PasajeroArrastrameViaje crearPasajeroArrastrameViaje(PasajeroArrastrameViaje objPasajeroArrastrameViaje) throws LogicaImplException {

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
								listarPasajeroArrastrameViajexidViaje(objPasajeroArrastrameViaje);
						
						PasajeroArrastrameViaje retPasajeroArrastrameViaje = null;
						
						if(listaPasajeroArrastrameViaje!=null) {
							for(PasajeroArrastrameViaje pav :listaPasajeroArrastrameViaje) {
								retPasajeroArrastrameViaje = pav;
								break;
							}
							return retPasajeroArrastrameViaje;
						}else {
							throw new LogicaImplException("No se puede crear PasajeroArrastrameViaje, parametros ya existen en identificador valido");
						}
					}else {
						throw new LogicaImplException("No se puede crear PasajeroArrastrameViaje, parametros ya existen en identificador valido");
					}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}

	public PasajeroArrastrameViaje actualizarPasajeroArrastrameViaje (Integer idPasajero, PasajeroArrastrameViaje objPasajeroArrastrameViaje) throws LogicaImplException{

		try {

			if(objPasajeroArrastrameViaje.getIdViaje()!=null && objPasajeroArrastrameViaje.getIdViaje().getIdViaje()!=null) {


				Pageable pageByIdViaje = PageRequest.of(0, 1, Sort.by("idViaje").descending());

				Page<PasajeroArrastrameViaje> pageIdViaje  = factoryArrastrameTripDAO.getPasajeroArrastrameViajeRepository().
						findByIdViaje(objPasajeroArrastrameViaje.getIdViaje(), pageByIdViaje);

				if(pageIdViaje.getContent().isEmpty()){

					objPasajeroArrastrameViaje.setIdPasajeroArrastrameViaje(idPasajero);

					factoryArrastrameTripDAO.getPasajeroArrastrameViajeRepository().save(objPasajeroArrastrameViaje);

					return objPasajeroArrastrameViaje;
				
				}else {
					throw new LogicaImplException("No se puede actualizar PasajeroArrastrameViaje, identificador ya existe");
				}
			
			}else {
				throw new LogicaImplException("No se puede actualizar PasajeroArrastrameViaje, identificador ya existe");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

	}

	public PasajeroArrastrameViaje eliminarPasajeroArrastrameViaje(Integer idPasajero, PasajeroArrastrameViaje objPasajeroArrastrameViaje) throws LogicaImplException{

		try {

			if(objPasajeroArrastrameViaje.getIdViaje()!=null && objPasajeroArrastrameViaje.getIdViaje().getIdViaje()!=null) {


				Pageable pageByIdViaje = PageRequest.of(0, 1, Sort.by("idViaje").descending());

				Page<PasajeroArrastrameViaje> pageIdViaje  = factoryArrastrameTripDAO.getPasajeroArrastrameViajeRepository().
						findByIdViaje(objPasajeroArrastrameViaje.getIdViaje(), pageByIdViaje);

				if(pageIdViaje.getContent().isEmpty()){

					objPasajeroArrastrameViaje.setIdPasajeroArrastrameViaje(idPasajero);

					factoryArrastrameTripDAO.getPasajeroArrastrameViajeRepository().delete(objPasajeroArrastrameViaje);
					
					return objPasajeroArrastrameViaje;
					
				}else {
					throw new LogicaImplException("No se puede eliminar PasajeroArrastrameViaje, identificador ya existe");
				}
			}else {
				throw new LogicaImplException("No se puede eliminar PasajeroArrastrameViaje, identificador ya existe");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}

	}

	public List<PasajeroArrastrameViaje> listarPasajeroArrastrameViajexidViaje(PasajeroArrastrameViaje objPasajeroArrastrameViaje) throws LogicaImplException{

		try {
			List<PasajeroArrastrameViaje> listaPasajeroArrastrameViaje = factoryArrastrameTripDAO.getPasajeroArrastrameViajeRepository().findAll();

			if(listaPasajeroArrastrameViaje !=null && !listaPasajeroArrastrameViaje.isEmpty()){
				return listaPasajeroArrastrameViaje;
			}else {
				throw new LogicaImplException("No existe lista de PasajeroArrastrameViaje");
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
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
