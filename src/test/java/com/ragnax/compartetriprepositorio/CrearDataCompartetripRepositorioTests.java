package com.ragnax.compartetriprepositorio;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ragnax.compartetriprepositorio.entidad.ClasificacionPasajero;
import com.ragnax.compartetriprepositorio.entidad.Descuento;
import com.ragnax.compartetriprepositorio.entidad.DescuentoViaje;
import com.ragnax.compartetriprepositorio.entidad.PasajeroArrastrame;
import com.ragnax.compartetriprepositorio.entidad.PasajeroArrastrameViaje;
import com.ragnax.compartetriprepositorio.entidad.RolPasajero;
import com.ragnax.compartetriprepositorio.entidad.TipoStatusViaje;
import com.ragnax.compartetriprepositorio.entidad.TipoVehiculoViaje;
import com.ragnax.compartetriprepositorio.entidad.TipoViaje;
import com.ragnax.compartetriprepositorio.entidad.TipoViajeRecomendado;
import com.ragnax.compartetriprepositorio.entidad.Viaje;
import com.ragnax.compartetriprepositorio.entidad.ViajeRecomendado;
import com.ragnax.compartetriprepositorio.exception.LogicaImplException;
import com.ragnax.compartetriprepositorio.servicio.CompartetripService;



@SpringBootTest
class CrearDataCompartetripRepositorioTests {
	
	@Autowired
	private CompartetripService compartetripService;
	

	@DisplayName("Test Junit 5 + Spring @Autowired Integration")
	@Test
	public void crearArrastrameGruaEntities() throws LogicaImplException, ParseException {
		crearTipoViajeRecomendadoTest();
		crearTipoVehiculoViajeTest();
		crearTipoViajeTest();
		crearTipoStatusViajeTest();
		crearClasificacionPasajeroTest();
		crearRolPasajeroTest();
		crearDescuentoTest();
		crearPasajeroArrastrameTest();
		crearViajeTest();
		crearDescuentoViajeTest();
		crearViajeRecomendadoTest();
		crearPasajeroArrastrameViajeTest();
		//		crearStatusEventoAsistenciaTest();
	}

	public void crearTipoViajeRecomendadoTest() throws LogicaImplException {

		List<TipoViajeRecomendado> listaTipoViajeRecomendado = null; 

		try{
			listaTipoViajeRecomendado = compartetripService.listarTodoTipoViajeRecomendado();
		}catch(Exception e){

		}

		if(listaTipoViajeRecomendado == null) {
			TipoViajeRecomendado[] arreglo = new TipoViajeRecomendado[2]; 

			int i=0;

			arreglo[i] =  new TipoViajeRecomendado(1, "recomendado"); i = i+1;
			arreglo[i] =  new TipoViajeRecomendado(2, "destacado"); i = i+1;

			for(int j=0; j< arreglo.length; j++){
				if(arreglo[j]!=null){
					try{
						compartetripService.crearTipoViajeRecomendado(arreglo[j]);
					}catch(Exception e){
						System.out.println("rechazo["+j+"] ->"+arreglo[j].getNombreTipoViajeRecomendado());
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void crearTipoVehiculoViajeTest() throws LogicaImplException {

		List<TipoVehiculoViaje> listaTipoVehiculoViaje = null; 

		try{
			listaTipoVehiculoViaje = compartetripService.listarTodoTipoVehiculoViaje();
		}catch(Exception e){

		}

		if(listaTipoVehiculoViaje == null) {
			TipoVehiculoViaje[] arreglo = new TipoVehiculoViaje[3]; 

			int i=0;

			arreglo[i] =  new TipoVehiculoViaje(1, "camioneta", 1); i = i+1;
			arreglo[i] =  new TipoVehiculoViaje(2, "grua pequeña", 1); i = i+1;
			arreglo[i] =  new TipoVehiculoViaje(3, "grua grande", 1);

			for(int j=0; j< arreglo.length; j++){
				if(arreglo[j]!=null){
					try{
						compartetripService.crearTipoVehiculoViaje(arreglo[j]);
					}catch(Exception e){
						System.out.println("rechazo["+j+"] ->"+arreglo[j].getNombreTipoVehiculoViaje());
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void crearTipoViajeTest() throws LogicaImplException {

		List<TipoViaje> listaTipoViaje = null; 

		try{
			listaTipoViaje = compartetripService.listarTodoTipoViaje();
		}catch(Exception e){

		}

		if(listaTipoViaje == null) {
			TipoViaje[] arreglo = new TipoViaje[4]; 

			int i=0;

			arreglo[i] =  new TipoViaje(null, "inicio"); i = i+1;
			arreglo[i] =  new TipoViaje(null, "enproceso"); i = i+1;
			arreglo[i] =  new TipoViaje(null, "finalizado"); i = i+1;
			arreglo[i] =  new TipoViaje(null, "terminado");

			for(int j=0; j< arreglo.length; j++){
				if(arreglo[j]!=null){
					try{
						compartetripService.crearTipoViaje(arreglo[j]);
					}catch(Exception e){
						System.out.println("rechazo["+j+"] ->"+arreglo[j].getNombreTipoViaje());
						e.printStackTrace();
					}
				}
			}
		}


	}

	public void crearTipoStatusViajeTest() throws LogicaImplException {

		List<TipoStatusViaje> listaTipoStatusViaje = null; 

		try{
			listaTipoStatusViaje = compartetripService.listarTodoTipoStatusViaje();
		}catch(Exception e){

		}

		if(listaTipoStatusViaje == null) {
			TipoStatusViaje[] arreglo = new TipoStatusViaje[2]; 

			int i=0;

			arreglo[i] =  new TipoStatusViaje(1,"subterraneo"); i = i+1;
			arreglo[i] =  new TipoStatusViaje(2,"camino despejado");

			for(int j=0; j< arreglo.length; j++){
				if(arreglo[j]!=null){
					try{
						compartetripService.crearTipoStatusViaje(arreglo[j]);
					}catch(Exception e){
						System.out.println("rechazo["+j+"] ->"+arreglo[j].getNombreTipoStatusViaje());
						e.printStackTrace();
					}
				}
			}
		}


	}

	public void crearClasificacionPasajeroTest() throws LogicaImplException {

		List<ClasificacionPasajero> listaClasificacionPasajero = null; 

		try{
			listaClasificacionPasajero = compartetripService.listarTodoClasificacionPasajero();
		}catch(Exception e){

		}

		if(listaClasificacionPasajero == null) {
			ClasificacionPasajero[] arreglo = new ClasificacionPasajero[5]; 

			int i=0;

			arreglo[i] =  new ClasificacionPasajero("moto", 1); i = i+1;
			arreglo[i] =  new ClasificacionPasajero("vehiculo hasta 1000Kg", 1); i = i+1;
			arreglo[i] =  new ClasificacionPasajero("vehiculo hasta 2000Kg", 1); i = i+1;
			arreglo[i] =  new ClasificacionPasajero("vehiculo hasta 5000Kg", 1);

			for(int j=0; j< arreglo.length; j++){
				if(arreglo[j]!=null){
					try{
						compartetripService.crearClasificacionPasajero(arreglo[j]);
					}catch(Exception e){
						System.out.println("rechazo["+j+"] ->"+arreglo[j].getNombreClasificacionPasajero());
						e.printStackTrace();
					}
				}
			}
		}

	}

	public void crearRolPasajeroTest() throws LogicaImplException {
		List<RolPasajero> listaRolPasajero = null; 

		try{
			listaRolPasajero = compartetripService.listarTodoRolPasajero();
		}catch(Exception e){

		}

		if(listaRolPasajero == null) {
			RolPasajero[] arreglo = new RolPasajero[2]; 

			int i=0;

			arreglo[i] =  new RolPasajero(1, "atascadas"); i = i+1;
			arreglo[i] =  new RolPasajero(2, "con movimiento");

			for(int j=0; j< arreglo.length; j++){
				if(arreglo[j]!=null){
					try{
						compartetripService.crearRolPasajero(arreglo[j]);
					}catch(Exception e){
						System.out.println("rechazo["+j+"] ->"+arreglo[j].getNombreRolPasajero());
						e.printStackTrace();
					}
				}
			}
		}

	}

	public void crearDescuentoTest() throws LogicaImplException, ParseException {

		List<Descuento> listaDescuento = null; 

		try{
			listaDescuento = compartetripService.listarTodoDescuento();
		}catch(Exception e){

		}

		if(listaDescuento == null) {
			Descuento[] arreglo = new Descuento[3]; 

			int i=0;
			
			Date fechaInicial = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-10-31 12:56:11");
			Date fechaFinal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-12-05 11:09:37");
			
			arreglo[i] =  new Descuento("A", new Timestamp(fechaInicial.getTime()), new Timestamp(fechaFinal.getTime()), 5); i = i+1;

			arreglo[i] =  new Descuento("B", new Timestamp(fechaInicial.getTime()), new Timestamp(fechaFinal.getTime()), 5); i = i+1;

			arreglo[i] =  new Descuento("C", new Timestamp(fechaInicial.getTime()), new Timestamp(fechaFinal.getTime()), 5); i = i+1;

			for(int j=0; j< arreglo.length; j++){
				if(arreglo[j]!=null){
					try{
						compartetripService.crearDescuento(arreglo[j]);
					}catch(Exception e){
						System.out.println("rechazo["+j+"] ->"+arreglo[j].getCodigoDescuento());
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void crearPasajeroArrastrameTest() throws LogicaImplException {

		List<PasajeroArrastrame> listaPasajeroArrastrame = null; 

		try{
			listaPasajeroArrastrame = compartetripService.listarTodoPasajeroArrastrame();
			
			
		}catch(Exception e){

		}

		if(listaPasajeroArrastrame == null) {
			List<ClasificacionPasajero> listaClasificacionPasajero = compartetripService.listarTodoClasificacionPasajero();
			
			List<RolPasajero> listaRolPasajero = compartetripService.listarTodoRolPasajero();
			
			//Mismo Usuario, mismo tipo, 
			PasajeroArrastrame pasajeroArrastrameA = new PasajeroArrastrame(1, listaClasificacionPasajero.get(0),
					listaRolPasajero.get(0), "");
			
			PasajeroArrastrame pasajeroArrastrameB = new PasajeroArrastrame(1, listaClasificacionPasajero.get(1),
					listaRolPasajero.get(1), "");
			
			pasajeroArrastrameA = compartetripService.crearPasajeroArrastrame(pasajeroArrastrameA);

			pasajeroArrastrameB = compartetripService.crearPasajeroArrastrame(pasajeroArrastrameB);
		}

	}

	public void crearViajeTest() throws LogicaImplException {

		 
		List<Viaje> listaViaje =  null;

		try{
			listaViaje = compartetripService.listarTodoViaje();
		}catch(Exception e){

		}

		if(listaViaje == null) {
			
			List<PasajeroArrastrame> listaPasajeroArrastrame = compartetripService.listarTodoPasajeroArrastrame();
			
			List<TipoViaje> listaTipoViaje = compartetripService.listarTodoTipoViaje();
			
			List<TipoVehiculoViaje> listaTipoVehiculoViaje = compartetripService.listarTodoTipoVehiculoViaje();
			
			List<TipoViajeRecomendado> listaTipoViajeRecomendado = compartetripService.listarTodoTipoViajeRecomendado();
			
			Viaje viajeA =  new Viaje(listaPasajeroArrastrame.get(0), listaTipoViaje.get(0), listaTipoVehiculoViaje.get(0), listaTipoViajeRecomendado.get(0),
						"4", "", "", "", "", "", new BigDecimal(10000));
			
			Viaje viajeB =  new Viaje(listaPasajeroArrastrame.get(1), listaTipoViaje.get(1), listaTipoVehiculoViaje.get(1), listaTipoViajeRecomendado.get(1),
					"3", "", "", "", "", "", new BigDecimal(20000));

			compartetripService.crearViaje(listaPasajeroArrastrame.get(0).getIdPasajeroArrastrame() ,viajeA);

			compartetripService.crearViaje(listaPasajeroArrastrame.get(1).getIdPasajeroArrastrame() ,viajeB);

		}

	}

	public void crearDescuentoViajeTest() throws LogicaImplException {
		List<DescuentoViaje> listaDescuentoViaje = null; 
		
		try{
			List<Viaje> listaViaje = compartetripService.listarTodoViaje();
			
			DescuentoViaje metDescuentoViaje = new DescuentoViaje();
			
			metDescuentoViaje.setIdViaje(listaViaje.get(0));
			
			listaDescuentoViaje = compartetripService.listarDescuentoViajexidViaje(metDescuentoViaje);
		}catch(Exception e){

		}

		if(listaDescuentoViaje == null) {
			
			List<Descuento> listaDescuento = compartetripService.listarTodoDescuento();
			
			List<Viaje> listaViaje = compartetripService.listarTodoViaje();
			

			DescuentoViaje descuentoViajeA =  new DescuentoViaje(listaDescuento.get(0), listaViaje.get(0)); 

			DescuentoViaje descuentoViajeB =  new DescuentoViaje(listaDescuento.get(1), listaViaje.get(1));

			compartetripService.crearDescuentoViaje(descuentoViajeA);

			compartetripService.crearDescuentoViaje(descuentoViajeB);
		}

	}
	
	public void crearViajeRecomendadoTest() throws LogicaImplException {
		List<ViajeRecomendado> listaViajeRecomendado = null; 
		
		try{
			List<TipoViajeRecomendado> listaTipoViajeRecomendado = compartetripService.listarTodoTipoViajeRecomendado();
			
			ViajeRecomendado metViajeRecomendado = new ViajeRecomendado();
			
			metViajeRecomendado.setIdTipoViajeRecomendado(listaTipoViajeRecomendado.get(0));
			
			listaViajeRecomendado = compartetripService.listarViajeRecomendadoxidTipoViajeRecomendado(metViajeRecomendado);
		}catch(Exception e){

		}

		if(listaViajeRecomendado == null) {
			
			List<Viaje> listaViaje = compartetripService.listarTodoViaje();
			
			List<TipoViajeRecomendado> listaTipoViajeRecomendado = compartetripService.listarTodoTipoViajeRecomendado();

			ViajeRecomendado viajeRecomendadoA =  new ViajeRecomendado(listaViaje.get(0), listaTipoViajeRecomendado.get(0)); 

			ViajeRecomendado viajeRecomendadoB =  new ViajeRecomendado(listaViaje.get(1), listaTipoViajeRecomendado.get(1));

			compartetripService.crearViajeRecomendado(viajeRecomendadoA);

			compartetripService.crearViajeRecomendado(viajeRecomendadoB);
		}

	}

	public void crearPasajeroArrastrameViajeTest() throws LogicaImplException {
		List<PasajeroArrastrameViaje> listaPasajeroArrastrameViaje = null; 
		
		try{
			List<Viaje> listaViaje = compartetripService.listarTodoViaje();
			
			PasajeroArrastrameViaje metPasajeroArrastrameViaje = new PasajeroArrastrameViaje(listaViaje.get(0));
			
			listaPasajeroArrastrameViaje = compartetripService.listarPasajeroArrastrameViajexidViaje(metPasajeroArrastrameViaje);
		}catch(Exception e){

		}

		if(listaPasajeroArrastrameViaje == null) {
			
			List<Viaje> listaViaje = compartetripService.listarTodoViaje();
			
			List<PasajeroArrastrame> listaPasajeroArrastrame = compartetripService.listarTodoPasajeroArrastrame();

			PasajeroArrastrameViaje pasajeroArrastrameViajeA =  new PasajeroArrastrameViaje(listaViaje.get(0), listaPasajeroArrastrame.get(0)); 

			PasajeroArrastrameViaje pasajeroArrastrameViajeB =  new PasajeroArrastrameViaje(listaViaje.get(1), listaPasajeroArrastrame.get(1));

			compartetripService.crearPasajeroArrastrameViaje(pasajeroArrastrameViajeA);

			compartetripService.crearPasajeroArrastrameViaje(pasajeroArrastrameViajeB);
		}


	}

}
