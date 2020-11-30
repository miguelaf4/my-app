package com.mayab.calidad.dao;

import static org.junit.Assert.*;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
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
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/Alumnos");        
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");        
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
		try {
			int actualRows = con.getRowCount("alumno");
			dao.addAlumno(a);
			assertEquals(actualRows+1, con.getRowCount("alumno"));
			dao.deleteAlumno(a.getId());
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
			dao.deleteAlumno(a.getId());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testRetreive() throws NumberFormatException, DataSetException {
		Alumno alumnoActual = new Alumno(5,"Ernestoo", 23, 10, "ernestooo@gmail.com");
		AlumnoDAOMysql dao = new AlumnoDAOMysql();
		dao.addAlumno(alumnoActual);
		IDataSet expectedDataSet = null;
		ITable expectedTable = null;
		try {
			alumnoActual = dao.getAlumno(5);
			expectedDataSet = getConnection().createDataSet();
			expectedTable = expectedDataSet.getTable("alumno");

			Alumno alumnoEsperado = new Alumno(Integer.parseInt(expectedTable.getValue(0,"idAlumno").toString()) ,expectedTable.getValue(0, "nombre").toString() 
				,Integer.parseInt(expectedTable.getValue(0, "edad").toString()) ,Integer.parseInt(expectedTable.getValue(0, "calificacion").toString())
				,expectedTable.getValue(0, "email").toString());
			assertEquals(alumnoActual.getId(),alumnoEsperado.getId());
			assertEquals(alumnoActual.getCalificacion(),alumnoEsperado.getCalificacion());
			assertEquals(alumnoActual.getEdad(),alumnoEsperado.getEdad());
			assertEquals(alumnoActual.getEmail(),alumnoEsperado.getEmail());
			assertEquals(alumnoActual.getNombre(),alumnoEsperado.getNombre());
			dao.deleteAlumno(alumnoActual.getId());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	//@Test
	public void testUpdate() {
		Alumno alumnoActual = new Alumno(6,"Leonel", 24, 5, "leonel@gmail.com");
		AlumnoDAOMysql dao = new AlumnoDAOMysql();
		dao.addAlumno(alumnoActual);	
		dao.updatePromedioAlumno(alumnoActual.getId(), 9);
		IDataSet expectedDataSet = null;
		ITable expectedTable = null;
		int expectedGrade = 0;
		try {
			alumnoActual = dao.getAlumno(6);
			expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/insert_result.xml"));
			expectedTable = expectedDataSet.getTable("alumno");
			expectedGrade = Integer.parseInt(expectedTable.getValue(1, "calificacion").toString());
			assertEquals(alumnoActual.getCalificacion(),expectedGrade);
			dao.deleteAlumno(alumnoActual.getId());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	//@Test
	public void testDelete() {
		Alumno alumno1 = new Alumno(5,"Ernesto", 23, 10, "ernestooo@gmail.com");
		Alumno alumno2 = new Alumno(6,"Leonel", 22, 9, "leonel@gmail.com");
		AlumnoDAOMysql dao = new AlumnoDAOMysql();
		dao.addAlumno(alumno1);	
		dao.addAlumno(alumno2);	
		try{
			dao.deleteAlumno(alumno2.getId());
			IDataSet databaseDataSet = getConnection().createDataSet();
			ITable actualTable = databaseDataSet.getTable("alumno");
			IDataSet expectedDataSet = getConnection().createDataSet();
			ITable expectedTable = expectedDataSet.getTable("alumno");
			Assertion.assertEquals(expectedTable, actualTable);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		//InputStream xmlFile = getClass().getResourceAsStream("src/resources/alumno_init.xml");
		//return new FlatXmlDataSetBuilder().build(xmlFile);
		return new FlatXmlDataSetBuilder().build(new File("src/resources/alumno_init.xml"));
	}
}