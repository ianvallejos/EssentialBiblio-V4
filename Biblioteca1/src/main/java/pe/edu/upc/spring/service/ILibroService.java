package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Libro;

public interface ILibroService {

	public boolean insertar(Libro libro);
	public boolean modificar(Libro libro);
	public void eliminar(int idLibro);
	public Optional<Libro> listarId(int idLibro);
	List<Libro> listar();
	List<Libro> buscarNombre(String nombreLibro);
	
	
}