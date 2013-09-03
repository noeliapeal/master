package com.indra.formacion.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T, K> {
	public K agregar(T obj) throws SQLException;
	public void modificar(T obj) throws SQLException;
	public void eliminar(K clave) throws SQLException;
	public T obtener(K clave) throws SQLException;
	public List<T> obtenerTodos() throws SQLException;
}
