package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.LibroAutor;

public interface ILibroAutorService {

	public boolean insertar(LibroAutor libroautor);
	public boolean modificar(LibroAutor libroautor);
	public Optional<LibroAutor> listarId(int idAutor);
	List<LibroAutor> listar();
	List<LibroAutor> buscarNombreAutor(String nombreAutor);
	List<LibroAutor> buscarNombreLibro(String nombreLibro);
	
}