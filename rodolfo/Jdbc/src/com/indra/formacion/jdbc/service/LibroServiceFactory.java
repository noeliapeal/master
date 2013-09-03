package com.indra.formacion.jdbc.service;

public abstract class LibroServiceFactory {
	public static ILibroService createLibroService() {
		return new LibroServiceImpl();
	}
}
