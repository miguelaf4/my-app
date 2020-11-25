package com.mayab.calidad.dao;

public class AlumnoDriver {

	public static void main(String[] args) {
		AlumnoDAOMysql alumnoDB = new AlumnoDAOMysql();
		Alumno a = new Alumno(3,"Leonel", 23, 10, "leonel@gmail.com");
		Alumno b = new Alumno(5,"Ernesto", 24, 10, "ernesto@gmail.com");
		System.out.println(alumnoDB.addAlumno(a));
		System.out.println(alumnoDB.addAlumno(b));
		System.out.println(alumnoDB.getNumAlumnos());
		System.out.println(alumnoDB.deleteAlumno(a.getId()));
		System.out.println(alumnoDB.getNumAlumnos());
		System.out.println(alumnoDB.updatePromedioAlumno(b.getId(), 8));
		System.out.println(alumnoDB.getAlumno(5).getCalificacion());
	}
}
