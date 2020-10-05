package com.mayab.calidad.dao;

public interface DAO {
	
	boolean addAlumno(Alumno a);
	boolean deleteAlumno(Alumno a);
	boolean updatePromedioAlumno(Alumno a, int nuevoPromedio);
	int getNumAlumnos();
	Alumno getAlumno(int id);
}
