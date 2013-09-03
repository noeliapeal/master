package com.indra.formacion.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDao {
	public static final String URL_BD = "jdbc:mysql://localhost/test";
	public static final String USUARIO_BD = "root";
	public static final String CLAVE_BD = "";
	protected Connection con;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Error cargando el driver");
			e.printStackTrace();
		}
	}
	
	public BaseDao() {
		try {
			con = DriverManager.getConnection(URL_BD, USUARIO_BD, CLAVE_BD);
		} catch (SQLException e) {
			System.err.println("Error pidiendo la conexión a la BD");
			e.printStackTrace();
		}
	}
}
