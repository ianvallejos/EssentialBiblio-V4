package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Ejemplar;

public interface IEjemplarService {

	public boolean insertar(Ejemplar ejemplar);
	public boolean modificar(Ejemplar ejemplar);
	public void eliminar(int idEjemplar);
	public Optional<Ejemplar> listarId(int idEjemplar);
	List<Ejemplar> listar();
	List<Ejemplar> buscarNombre(String nombreEjemplar);
	
	
}