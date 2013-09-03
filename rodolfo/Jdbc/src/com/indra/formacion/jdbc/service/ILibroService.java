package com.indra.formacion.jdbc.service;

import java.util.List;

import com.indra.formacion.jdbc.exception.CustomException;
import com.indra.formacion.jdbc.model.Libro;

public interface ILibroService {
	public void agregarLibro(Libro libro) throws CustomException;
	public List<Libro> obtenerLibros() throws CustomException;
}
