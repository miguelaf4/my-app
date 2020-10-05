package com.mayab.calidad.dao;

import java.util.HashMap;

public class DAOFake implements DAO {
	private HashMap<Integer, Alumno> alumnos;
	
	public DAOFake() {
		alumnos = new HashMap<Integer, Alumno>();
	}
	public boolean addAlumno(Alumno a) {
		int numeroInicial = getAlumnos();
		alumnos.put(a.getId(),a); 
		if(getAlumnos() > numeroInicial) return true;
		else return false;
	}

	public void deleteAlumno(Alumno a) {
		alumnos.remove(a.getId());
	}

	public void updatePromedioAlumno(Alumno a, int nuevoPromedio) {
		alumnos.get(a.getId()).setCalificacion(nuevoPromedio);
	}

	public int getAlumnos() {
		return alumnos.size();
	}

	public Alumno getAlumno(int id) {
		return alumnos.get(id);
	}
	

}
