package com.ragnax.compartetriprepositorio.servicio.utilidades;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.ragnax.compartetriprepositorio.entidad.PasajeroArrastrame;
import com.ragnax.compartetriprepositorio.entidad.Viaje;

public class ArrastrameTripUtilidades {
	
	public static Date agregarHoras(Date fecha, int horas) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.HOUR, horas);
		return calendar.getTime();
	}
	
	public static String convertirTimestamptoStr(Timestamp dateTimetamp, String strFormat) {
		
		Date dateUtil = new Date(dateTimetamp.getTime());
		
		return new SimpleDateFormat(strFormat).format(dateUtil);
		
		
	}
	
	public static Timestamp convertirStrFormatYYYY_MM_DDTHH_MM_SSToTimestamp(String sdfDate){

		Date dateUtil = convertirStrFormatYYYY_MM_DDTHH_MM_SSToDate(sdfDate);
		
		return  new Timestamp(dateUtil.getTime());

	}
	
	public static Date convertirStrFormatYYYY_MM_DDTHH_MM_SSToDate(String sFecha) {
	
		java.util.Date fechaRetorno = null;

		try{
			long miliHoy = 0;

			int ano1 = Integer.parseInt(sFecha.substring(0, 4));
			int mes1 = Integer.parseInt(sFecha.substring(5, 7));
			int dia1 = Integer.parseInt(sFecha.substring(8, 10));
			int hora1 = Integer.parseInt(sFecha.substring(11, 13));
			int minuto1 = Integer.parseInt(sFecha.substring(14, 16));
			int seg1 = Integer.parseInt(sFecha.substring(17, 19));

			GregorianCalendar c = new GregorianCalendar(ano1, mes1-1, dia1, hora1, minuto1, seg1);

			miliHoy = c.getTime().getTime();

			fechaRetorno = new java.util.Date(miliHoy);
		}catch(Exception e){
			fechaRetorno = new java.util.Date();
		}

		return fechaRetorno;
	}
	
	
	public static List<String> crearListaCadenaCodigoNegocio(PasajeroArrastrame objPasajeroArrastrame, Viaje objViaje){

		List<String> listaCadena = new ArrayList<String>(); 

		String codigoNegocio = objPasajeroArrastrame.getIdRolPasajero().getIdRolPasajero()+""+
				objPasajeroArrastrame.getIdClasificacionPasajero().getIdClasificacionPasajero()+
					objViaje.getIdTipoViaje().getIdTipoViaje()+
						objViaje.getIdTipoVehiculoViaje().getIdTipoVehiculoViaje()+
							objViaje.getIdTipoViajeRecomendado().getIdTipoViajeRecomendado();
		codigoNegocio = codigoNegocio.trim();
		
		codigoNegocio = codigoNegocio.replace("\\s", "").replace(" ", "");
		
		codigoNegocio = codigoNegocio.toLowerCase();

		listaCadena.add(codigoNegocio);

		return listaCadena;

	}
	
	public static List<String> convertirStrFechaConFormatToTimestamp(String sFecha, String formato){

		List<String> listaCadena = new ArrayList<String>(); 

		listaCadena.add(sFecha);

		listaCadena.add(formato);

		return listaCadena;

	}

//	public static String obtenerCodigoViaje(PasajeroArrastrame objPasajeroArrastrame, Viaje objViaje){
//
//		try {
//			if(objPasajeroArrastrame!=null && objPasajeroArrastrame.getIdRolPasajero().getIdRolPasajero()!=null &&
//					objPasajeroArrastrame.getIdClasificacionPasajero().getIdClasificacionPasajero()>0 &&
//						objViaje.getIdTipoViaje().getIdTipoViaje()>0 &&
//							objViaje.getIdTipoVehiculoViaje().getIdTipoVehiculoViaje()>0 &&
//								objViaje.getIdTipoViajeRecomendado().getIdTipoViajeRecomendado()>0) {
//				//Validar que no exista la combinacion del, negocio, tipo de fee y el nombre de ese cargo.
//
//				String codigoNegocio = objPasajeroArrastrame.getIdRolPasajero().getIdRolPasajero()+""+
//						objPasajeroArrastrame.getIdClasificacionPasajero().getIdClasificacionPasajero()+
//							objViaje.getIdTipoViaje().getIdTipoViaje()+
//								objViaje.getIdTipoVehiculoViaje().getIdTipoVehiculoViaje()+
//									objViaje.getIdTipoViajeRecomendado().getIdTipoViajeRecomendado();
//				codigoNegocio = codigoNegocio.trim();
//				codigoNegocio = codigoNegocio.replace("\\s", "").replace(" ", "");
//				codigoNegocio = codigoNegocio.toLowerCase();
//				
//				codigoNegocio = Encriptar1_1.generarCodigoByNumeroByEncodear(codigoNegocio, objViaje.getStimeInicioViaje());
//				
//				return codigoNegocio;
//			}
//
//		} catch (Exception e) {
//			return null;
//		}
//
//		return null;
//	}
	


} 
