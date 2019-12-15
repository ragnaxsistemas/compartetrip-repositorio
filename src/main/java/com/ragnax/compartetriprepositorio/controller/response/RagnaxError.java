package com.ragnax.compartetriprepositorio.controller.response;

import java.io.Serializable;

public class RagnaxError implements Serializable {
	
	private static final long serialVersionUID = -1360021176413807221L;
	
	private int codigo;
	private String mensaje;

	public RagnaxError() {
	}
	
	public RagnaxError(String mensaje) {
		super();
		this.mensaje = mensaje;
	}
	
	public RagnaxError(int codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
