package com.indra.formacion.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Test {
	public static final String URL_BD = "jdbc:mysql://localhost/test";
	public static final String USUARIO_BD = "root";
	public static final String CLAVE_BD = "";
	
	static {
		try {	/**
			 * @param args
			 */

			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Error cargando el driver");
			e.printStackTrace();
		}
	}
	
	public static void imprimir(Statement stmt, String sql) throws SQLException {
		ResultSet rs = stmt.executeQuery(sql);
		
		System.out.println();
		while (rs.next()) {
			String retorno = "(id: " + rs.getInt("id");
			retorno += ", nombre: " + rs.getString("titulo");
			retorno += ", autor: " + rs.getString("autor");
			retorno += ", precio: " + rs.getFloat("precio") + ")";
			
			System.out.println(retorno);
		}
	}
	
	public static void main(String[] args) throws SQLException {
		Connection con = DriverManager.getConnection(URL_BD, USUARIO_BD, CLAVE_BD); // Abro la conexión
		imprimir(con.createStatement(), "SELECT * FROM libro"); // Ejecuto una operación
		
		// INSERTANDO
		Statement stmt = con.createStatement();
		stmt.executeUpdate("INSERT INTO libro(titulo, autor, precio) " +
				"VALUES('Título JDBC', 'Autor JDBC', 33)");
		imprimir(con.createStatement(), "SELECT * FROM libro"); // Ejecuto una operación

		// ACTUALIZANDO
		stmt = con.createStatement();
		stmt.executeUpdate("UPDATE libro SET titulo = 'Título modificado' " +
				"WHERE id = 1");
		imprimir(con.createStatement(), "SELECT * FROM libro"); // Ejecuto una operación
		
		// ELIMINANDO
		stmt = con.createStatement();
		stmt.executeUpdate("DELETE FROM libro WHERE id = 5");
		imprimir(con.createStatement(), "SELECT * FROM libro"); // Ejecuto una operación

		// Obteniendo 1
		int id = 1;
		imprimir(con.createStatement(), "SELECT * FROM libro WHERE id = " + id); // Ejecuto una operación

		PreparedStatement pstmt = con.prepareStatement("SELECT * FROM libro WHERE id = ?");
		pstmt.setInt(1, id); // => Reemplazando el id por 1
		ResultSet rs = pstmt.executeQuery();
		System.out.println();
		while (rs.next()) { System.out.println(rs.getString(2)); /* Título */ }

		pstmt = con.prepareStatement("SELECT * FROM lector WHERE fecha_nacimiento < ?");
		pstmt.setDate(1, new java.sql.Date(new Date().getTime())); // => Reemplazando el id por 1
		
		rs = pstmt.executeQuery();
		System.out.println();
		while (rs.next()) { System.out.println(rs.getString(2)); /* Título */ }

		con.close(); // Cierro la conexión
	}

}
