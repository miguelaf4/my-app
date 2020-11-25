package com.mayab.calidad.dao;

public interface DAO {
	
	boolean addAlumno(Alumno a);
	boolean deleteAlumno(int id);
	boolean updatePromedioAlumno(int id, int nuevoPromedio);
	int getNumAlumnos();
	Alumno getAlumno(int id);
}
