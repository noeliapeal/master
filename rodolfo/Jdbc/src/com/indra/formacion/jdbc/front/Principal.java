package com.indra.formacion.jdbc.front;

import java.util.Scanner;

import com.indra.formacion.jdbc.exception.CustomException;
import com.indra.formacion.jdbc.model.Libro;
import com.indra.formacion.jdbc.service.ILibroService;
import com.indra.formacion.jdbc.service.LibroServiceFactory;

public class Principal {

	public void menu() throws CustomException {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Título? ");
			String titulo = scanner.nextLine();
			System.out.println("Autor? ");
			String autor = scanner.nextLine();
			System.out.println("Precio? ");
			String sprecio = scanner.nextLine();
			
			Libro libro = new Libro(titulo, autor, Float.parseFloat(sprecio));
			ILibroService libroService = LibroServiceFactory.createLibroService();
			libroService.agregarLibro(libro);
			
			System.out.println(libroService.obtenerLibros());
		}
	}
	
	public static void main(String[] args) throws CustomException {
		new Principal().menu();
	}

}
