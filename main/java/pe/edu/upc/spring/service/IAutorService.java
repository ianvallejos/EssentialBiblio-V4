package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Autor;

public interface IAutorService {

	public boolean insertar(Autor autor);
	public boolean modificar(Autor autor);
	public void eliminar(int idAutor);
	public Optional<Autor> listarId(int idAutor);
	List<Autor> listar();
	List<Autor> buscarNombre(String nombreAutor);
	
}