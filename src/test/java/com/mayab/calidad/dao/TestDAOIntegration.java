package com.mayab.calidad.dao;

import static org.junit.Assert.*;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.DatabaseUnitException;
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
import java.sql.SQLException;

public class TestDAOIntegration extends DBTestCase{

	public TestDAOIntegration(String name) {
		super(name);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver");        
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/alumnos");        
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
	public void testInsertCount() throws SQLException {
		Alumno a = new Alumno(9,"Leonel", 24, 9, "leonel@gmail.com");
		AlumnoDAOMysql dao = new AlumnoDAOMysql();
		IDatabaseConnection con = null;
		int actualRows = 0;
		try {
			con = getConnection();
			actualRows = con.getRowCount("Alumno");
			dao.addAlumno(a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(actualRows+1, con.getRowCount("Alumno"));
	}
	
	@Test
	public void testCreate() throws SQLException, DatabaseUnitException{
		Alumno a = new Alumno(6,"Leonel", 24, 9, "leonel@gmail.com");
		AlumnoDAOMysql dao = new AlumnoDAOMysql();
		dao.addAlumno(a);		
		ITable expectedTable = null;
		ITable actualTable = null;
		try{
			IDataSet databaseDataSet = getConnection().createDataSet();
			actualTable = databaseDataSet.getTable("Alumno");
			//Leemos los datos del archivo esperado
			//InputStream xmlFile = getClass().getResourceAsStream("src/resources/insert_result.xml");
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/insert_result.xml"));
			expectedTable = expectedDataSet.getTable("Alumno");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	@Test
	public void testRetreive() throws NumberFormatException, DataSetException {
		Alumno a = new Alumno(5,"Ernestoo", 23, 10, "ernestooo@gmail.com");
		AlumnoDAOMysql dao = new AlumnoDAOMysql();
		dao.addAlumno(a);
		Alumno alumnoEsperado = null;
		Alumno alumnoActual = null;
		try {
			alumnoActual = dao.getAlumno(5);
			IDataSet expectedDataSet = getDataSet();
			ITable expectedTable = expectedDataSet.getTable("Alumno");
			alumnoEsperado = new Alumno(Integer.parseInt(expectedTable.getValue(0,"idAlumno").toString()) ,expectedTable.getValue(0, "nombre").toString() 
				,Integer.parseInt(expectedTable.getValue(0, "edad").toString()) ,Integer.parseInt(expectedTable.getValue(0, "calificacion").toString())
				,expectedTable.getValue(0, "email").toString());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		assertEquals(alumnoActual.getId(),alumnoEsperado.getId());//AlumnoEsperado
		assertEquals(alumnoActual.getCalificacion(),alumnoEsperado.getCalificacion());
		assertEquals(alumnoActual.getEdad(),alumnoEsperado.getEdad());
		assertEquals(alumnoActual.getEmail(),alumnoEsperado.getEmail());
		assertEquals(alumnoActual.getNombre(),alumnoEsperado.getNombre());
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
			expectedTable = expectedDataSet.getTable("Alumno");
			expectedGrade = Integer.parseInt(expectedTable.getValue(1, "calificacion").toString());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		assertEquals(alumnoActual.getCalificacion(),expectedGrade);
	}
	
	//@Test
	public void testDelete() throws DatabaseUnitException {
		Alumno alumno1 = new Alumno(5,"Ernesto", 23, 10, "ernestooo@gmail.com");
		Alumno alumno2 = new Alumno(6,"Leonel", 22, 9, "leonel@gmail.com");
		AlumnoDAOMysql dao = new AlumnoDAOMysql();
		dao.addAlumno(alumno1);	
		dao.addAlumno(alumno2);	
		ITable expectedTable = null;
		ITable actualTable = null;
		try{
			dao.deleteAlumno(alumno2.getId());
			IDataSet databaseDataSet = getDataSet();
			actualTable = databaseDataSet.getTable("Alumno");
			IDataSet expectedDataSet = getConnection().createDataSet();
			expectedTable = expectedDataSet.getTable("Alumno");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new File("src/resources/alumno_init.xml"));
	}
}