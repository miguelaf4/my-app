package com.mayab.calidad.dao;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.ArgumentMatcher.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class TestDAOUnit {
	private DAOFake fake;
	HashMap<Integer, Alumno> dbFake = new HashMap<Integer, Alumno>();
	
	@Before
	public void setupMocks() {
		fake = mock(DAOFake.class);
	}

	@Test
	public void testCreate() {
		when(fake.addAlumno(Mockito.any(Alumno.class))).thenAnswer(new Answer<Boolean> () {
			public Boolean answer(InvocationOnMock invocation) throws Throwable{
				Alumno arg = (Alumno) invocation.getArguments()[0];
				int numeroActual = dbFake.size();
				dbFake.put(arg.getId(), arg);
				if(dbFake.size()>numeroActual)return true;
				else return false;
			}
		});
		assertEquals(fake.addAlumno(new Alumno(5, "Leonel", 24, 10, "leonel@gmail.com")), true);
	}
	
	@Test
	public void testRetrieve() {
		when(fake.addAlumno(Mockito.any(Alumno.class))).thenAnswer(new Answer<Boolean> () {
			public Boolean answer(InvocationOnMock invocation) throws Throwable{
				Alumno arg = (Alumno) invocation.getArguments()[0];
				int numeroActual = dbFake.size();
				dbFake.put(arg.getId(), arg);
				if(dbFake.size()>numeroActual)return true;
				else return false;
			}
		});
		when(fake.getAlumno(anyInt())).thenAnswer(new Answer<Alumno> () {
			public Alumno answer(InvocationOnMock invocation) throws Throwable{
				int arg = (Integer) invocation.getArguments()[0];
				return dbFake.get(arg);
			}
		});
		Alumno alumnoActual = new Alumno(10, "Ernesto", 23, 9, "leonel@gmail.com");
		fake.addAlumno(alumnoActual);
		Alumno alumnoEsperado = fake.getAlumno(10);
		assertEquals(alumnoActual.getId(),alumnoEsperado.getId());
		assertEquals(alumnoActual.getCalificacion(),alumnoEsperado.getCalificacion());
		assertEquals(alumnoActual.getEdad(),alumnoEsperado.getEdad());
		assertEquals(alumnoActual.getEmail(),alumnoEsperado.getEmail());
		assertEquals(alumnoActual.getNombre(),alumnoEsperado.getNombre());
	}
	
	@Test
	public void testUpdate() {
		when(fake.addAlumno(Mockito.any(Alumno.class))).thenAnswer(new Answer<Boolean> () {
			public Boolean answer(InvocationOnMock invocation) throws Throwable{
				Alumno arg = (Alumno) invocation.getArguments()[0];
				int numeroActual = dbFake.size();
				dbFake.put(arg.getId(), arg);
				if(dbFake.size()>numeroActual)return true;
				else return false;
			}
		});
		when(fake.updatePromedioAlumno(anyInt(), anyInt())).thenAnswer(new Answer<Boolean> () {
			public Boolean answer(InvocationOnMock invocation) throws Throwable{
				int id = (Integer) invocation.getArguments()[0];
				int nuevoPromedio = (Integer) invocation.getArguments()[1];
				try {
				dbFake.get(id).setCalificacion(nuevoPromedio);
				return true;
				}catch(Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		});
		when(fake.getAlumno(anyInt())).thenAnswer(new Answer<Alumno> () {
			public Alumno answer(InvocationOnMock invocation) throws Throwable{
				int arg = (Integer) invocation.getArguments()[0];
				return dbFake.get(arg);
			}
		});
		Alumno alumnoActual = new Alumno(15, "Ernesto", 22, 8, "leonel@gmail.com");
		fake.addAlumno(alumnoActual);
		fake.updatePromedioAlumno(15, 10);
		assertEquals(fake.getAlumno(15).getCalificacion(),10);
	}
	
	@Test
	public void testDelete() {
		when(fake.addAlumno(Mockito.any(Alumno.class))).thenAnswer(new Answer<Boolean> () {
			public Boolean answer(InvocationOnMock invocation) throws Throwable{
				Alumno arg = (Alumno) invocation.getArguments()[0];
				int numeroActual = dbFake.size();
				dbFake.put(arg.getId(), arg);
				if(dbFake.size()>numeroActual)return true;
				else return false;
			}
		});
		when(fake.deleteAlumno(anyInt())).thenAnswer(new Answer<Boolean> () {
			public Boolean answer(InvocationOnMock invocation) throws Throwable{
				int id = (Integer) invocation.getArguments()[0];
				int numeroActual = dbFake.size();
				dbFake.remove(id);
				if(dbFake.size()<numeroActual)return true;
				else return false;
			}
		});
		Alumno alumnoActual = new Alumno(20, "Ernesto", 21, 7, "leonel@gmail.com");
		fake.addAlumno(alumnoActual);
		assertEquals(fake.deleteAlumno(20),true);
	}

}
