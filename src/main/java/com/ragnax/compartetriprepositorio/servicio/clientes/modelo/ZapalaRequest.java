package com.ragnax.compartetriprepositorio.servicio.clientes.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class ZapalaRequest implements Serializable{

	private static final long serialVersionUID = 5379307160546001781L;
	
	private LocalDateTime localDateTime;
	private List<String> listaString; 
	private List<Integer> listaInteger;
	
	public ZapalaRequest() {
		super();
	}
	
	public ZapalaRequest(LocalDateTime localDateTime) {
		super();
		this.localDateTime = localDateTime;
	}
	
	public ZapalaRequest(List<String> listaString) {
		super();
		this.listaString = listaString;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public List<String> getListaString() {
		return listaString;
	}

	public void setListaString(List<String> listaString) {
		this.listaString = listaString;
	}

	public List<Integer> getListaInteger() {
		return listaInteger;
	}

	public void setListaInteger(List<Integer> listaInteger) {
		this.listaInteger = listaInteger;
	}
	
}
