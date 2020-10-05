package com.mayab.calidad;

public class Usuario {

	String password;
	
	public Usuario(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	
	public String revisarPass(String comparador) {
		if(comparador.length() < 12) return "Error, minimo 12 caracteres";
		else return "correcto";
	}
}
