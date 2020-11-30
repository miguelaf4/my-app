package com.mayab.calidad.dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class AlumnoDAOMysql implements DAO {
	
	public Connection getConection() {
		 String url = "jdbc:mysql://localhost:3306/alumnos?useSSL=false";
	        String user = "root";
	        String password = "";
	        Connection con = null;

	        try {
	        	con = DriverManager.getConnection(url, user, password);
	        } catch (SQLException ex) {
	            
	            Logger lgr = Logger.getLogger(AlumnoDAOMysql.class.getName());
	            lgr.log(Level.SEVERE, ex.getMessage(), ex);
	        } 
	        return con;
		
	}
	
	

	public boolean addAlumno(Alumno a) {
		Connection con = getConection();
		try {
			PreparedStatement ps = con.prepareStatement("insert ignore into Alumno(idAlumno,nombre,edad,calificacion,email) values (?,?,?,?,?)");
			ps.setInt(1, a.getId());
			ps.setString(2, a.getNombre());
			ps.setInt(3, a.getEdad());
			ps.setInt(4, a.getCalificacion());
			ps.setString(5, a.getEmail());
			ps.execute();
			con.close();
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean deleteAlumno(int id) {
		Connection con = getConection();
		try {
			PreparedStatement ps = con.prepareStatement("delete from Alumno WHERE idAlumno=?");
			ps.setInt(1, id);
			ps.execute();
			con.close();
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean updatePromedioAlumno(int id , int nuevoPromedio) {
		Connection con = getConection();
		try {
			PreparedStatement ps = con.prepareStatement("update Alumno SET calificacion=? WHERE idAlumno=?");
			ps.setInt(1, nuevoPromedio);
			ps.setInt(2, id);
			ps.execute();
			con.close();
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public int getNumAlumnos() {
		Connection con = getConection();
		try {
			PreparedStatement ps = con.prepareStatement("select count(*) from Alumno");
			ResultSet result = ps.executeQuery();
			result.next();
			int r = result.getInt(1);
			con.close();
			return r;
		}catch(Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	public Alumno getAlumno(int id) {
		Connection con = getConection();
		Alumno a = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from Alumno WHERE idAlumno=?");
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			result.next();
			a = new Alumno(id, result.getString(2), result.getInt(3), result.getInt(4), result.getString(5));
			con.close();
			return a;
		}catch(Exception ex) {
			ex.printStackTrace();
			return a;
		}
	}


}