package com.indra.formacion.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.indra.formacion.jdbc.model.Libro;

public class LibroDao extends BaseDao implements ILibroDao {

	@Override
	public Integer agregar(Libro libro) throws SQLException {
		Integer id = null;
		String sql = "INSERT INTO libro(titulo, autor, precio) VALUES(?, ?, ?)";
		
		PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, libro.getTitulo());
		pstmt.setString(2, libro.getAutor());
		pstmt.setFloat(3, libro.getPrecio());
		
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if (rs.next())
			id = rs.getInt(1);

		return id;
	}

	@Override
	public void modificar(Libro libro) throws SQLException {
		String sql = "UPDATE libro SET titulo = ?, autor = ?, precio= ? WHERE id = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, libro.getTitulo());
		pstmt.setString(2, libro.getAutor());
		pstmt.setFloat(3, libro.getPrecio());
		pstmt.setInt(4, libro.getId());
		
		pstmt.executeUpdate();
	}

	@Override
	public void eliminar(Integer clave) throws SQLException {
		String sql = "DELETE FROM libro WHERE id = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, clave);
		
		pstmt.executeUpdate();
	}

	@Override
	public Libro obtener(Integer clave) throws SQLException {
		Libro libro = null;
		
		String sql = "SELECT * FROM libro WHERE id = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, clave);
		
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			libro = new Libro(rs.getString("titulo"), rs.getString("autor"), rs.getFloat("precio"));
			libro.setId(rs.getInt("id"));
		}
			
		return libro;
	}

	@Override
	public List<Libro> obtenerTodos() throws SQLException {
		List<Libro> libros = new LinkedList<Libro>();
		
		String sql = "SELECT * FROM libro";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Libro l = new Libro(rs.getString("titulo"), rs.getString("autor"), rs.getFloat("precio"));
			l.setId(rs.getInt("id"));
			
			libros.add(l);
		}
			
		return libros;
	}

}
