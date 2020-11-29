package com.mayab.calidad.dao;

import static org.junit.Assert.*;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;

public class TestDAOIntegration extends DBTestCase{

	public TestDAOIntegration(String name) {
		super(name);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver");        
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/mysqljdbc");        
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "travis");        
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, ""); 
	}
	
	@Before
	public void setUp() throws Exception{
		super.setUp();
		IDatabaseConnection con = getConnection();
		try {
			DatabaseOperation.CLEAN_INSERT.execute(con, getDataSet());
		}finally {
			con.close();
		}
	}
	
	@Test
	public void testInsertCount() {
		Alumno a = new Alumno(9,"Leonel", 24, 9, "leonel@gmail.com");
		AlumnoDAOMysql dao = new AlumnoDAOMysql();
		IDatabaseConnection con;
		try {
			con = getConnection();
			int actualRows = con.getRowCount("alumno");
			dao.addAlumno(a);
			assertEquals(actualRows+1, con.getRowCount("alumno"));
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreate() {
		Alumno a = new Alumno(6,"Leonel", 24, 9, "leonel@gmail.com");
		AlumnoDAOMysql dao = new AlumnoDAOMysql();
		dao.addAlumno(a);		
		try{
			IDataSet databaseDataSet = getConnection().createDataSet();
			ITable actualTable = databaseDataSet.getTable("alumno");
			//Leemos los datos del archivo esperado
			//InputStream xmlFile = getClass().getResourceAsStream("src/resources/insert_result.xml");
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/insert_result.xml"));
			ITable expectedTable = expectedDataSet.getTable("alumno");
			// Assert actual database table match expected table
			Assertion.assertEquals(expectedTable, actualTable);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testRetreive() {
		Alumno alumnoActual = new Alumno(20,"Ernesto", 24, 9, "leonel@gmail.com");
		AlumnoDAOMysql dao = new AlumnoDAOMysql();
		dao.addAlumno(alumnoActual);	
		Alumno alumnoEsperado = dao.getAlumno(20);
		assertEquals(alumnoActual.getId(),alumnoEsperado.getId());
		assertEquals(alumnoActual.getCalificacion(),alumnoEsperado.getCalificacion());
		assertEquals(alumnoActual.getEdad(),alumnoEsperado.getEdad());
		assertEquals(alumnoActual.getEmail(),alumnoEsperado.getEmail());
		assertEquals(alumnoActual.getNombre(),alumnoEsperado.getNombre());
	}
	
	@Test
	public void testUpdate() {
		Alumno alumnoActual = new Alumno(30,"Ernesto Vera", 20, 5, "leonel@gmail.com");
		AlumnoDAOMysql dao = new AlumnoDAOMysql();
		dao.addAlumno(alumnoActual);	
		dao.updatePromedioAlumno(alumnoActual.getId(), 10);
		alumnoActual = dao.getAlumno(alumnoActual.getId());
		assertEquals(alumnoActual.getCalificacion(),10);
	}
	
	@Test
	public void testDelete() {
		Alumno alumno1 = new Alumno(31,"Leonel Vera", 21, 7, "LV@gmail.com");
		Alumno alumno2 = new Alumno(32,"Ernesto Sosa", 22, 8, "ES@gmail.com");
		AlumnoDAOMysql dao = new AlumnoDAOMysql();
		dao.addAlumno(alumno1);	
		dao.addAlumno(alumno2);	
		int numeroAlumnos = dao.getNumAlumnos();
		dao.deleteAlumno(32);
		assertEquals(numeroAlumnos-1,dao.getNumAlumnos());
		dao.deleteAlumno(31);
		assertEquals(numeroAlumnos-2,dao.getNumAlumnos());
	}
	
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		//InputStream xmlFile = getClass().getResourceAsStream("src/resources/alumno_init.xml");
		//return new FlatXmlDataSetBuilder().build(xmlFile);
		return new FlatXmlDataSetBuilder().build(new File("src/resources/alumno_init.xml"));
	}
}
