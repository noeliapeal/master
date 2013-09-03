package com.indra.formacion.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GeneratedKey {
	public static final String URL_BD = "jdbc:mysql://localhost/test";
	public static final String USUARIO_BD = "root";
	public static final String CLAVE_BD = "";
	
	static {
		try {	
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Error cargando el driver");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		Connection con = DriverManager.getConnection(URL_BD, USUARIO_BD, CLAVE_BD);
		String sql = "INSERT INTO libro(titulo, autor, precio) " +
				"VALUES(?, ?, ?)";
		
		/*
		Statement stmt = con.createStatement();
		stmt.executeUpdate("INSERT ...", Statement.RETURN_GENERATED_KEYS);
		*/
		
		PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, "titulo");
		pstmt.setString(2, "autor");
		pstmt.setFloat(3, 33);

		System.out.println("Insertado : " + pstmt.executeUpdate());
		
		ResultSet rs = pstmt.getGeneratedKeys();
		int id = -1;
		if (rs.next()) 
			id = rs.getInt(1);
		
		System.out.println("Y el id del nuevo libro es : " + id);
		
		
	}

}
