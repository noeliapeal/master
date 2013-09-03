package com.indra.formacion.jdbc.service;

import java.sql.SQLException;
import java.util.List;

import com.indra.formacion.jdbc.dao.ILibroDao;
import com.indra.formacion.jdbc.dao.LibroDao;
import com.indra.formacion.jdbc.exception.CustomException;
import com.indra.formacion.jdbc.model.Libro;

class LibroServiceImpl implements ILibroService {
	private ILibroDao libroDao;
	
	public LibroServiceImpl() {
		libroDao = new LibroDao();
	}

	@Override
	public void agregarLibro(Libro libro) throws CustomException {
		// TODO: Agregar más código de lógica de datos...
		try {
			libroDao.agregar(libro);
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	public List<Libro> obtenerLibros() throws CustomException {
		// TODO: Agregar más código de lógica de datos...
		try {
			return libroDao.obtenerTodos();
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new CustomException(e.getMessage());
		}
	}

}
