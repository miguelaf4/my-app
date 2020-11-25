package com.mayab.calidad.dao;

import java.util.HashMap;

public class DAOFake implements DAO {
	private HashMap<Integer, Alumno> alumnos;
	
	public DAOFake() {
		alumnos = new HashMap<Integer, Alumno>();
	}
	
	public boolean addAlumno(Alumno a) {
		int numeroInicial = getNumAlumnos();
		alumnos.put(a.getId(),a); 
		if(getNumAlumnos() > numeroInicial) return true;
		else return false;
	}

	public boolean deleteAlumno(int id) {
		alumnos.remove(id);
		return true;
	}

	public boolean updatePromedioAlumno(int id, int nuevoPromedio) {
		alumnos.get(id).setCalificacion(nuevoPromedio);
		return true;
	}

	public Alumno getAlumno(int id) {
		return alumnos.get(id);
	}
	
	public int getNumAlumnos() {
		return alumnos.size();
	}
	

}
