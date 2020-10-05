package com.mayab.calidad.dao;

public class Alumno {
	
	private int id;
	private String nombre;
	private int edad;
	private int calificacion;
	private String email;
	
	public Alumno(int id, String nombre, int edad, int calificacion, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.calificacion = calificacion;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
		
}
