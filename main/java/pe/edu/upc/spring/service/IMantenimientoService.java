package pe.edu.upc.spring.service;


import java.util.List;
import java.util.Optional;


import pe.edu.upc.spring.model.Mantenimiento;

public interface IMantenimientoService {

	public boolean insertar(Mantenimiento mantenimiento);
	public boolean modificar(Mantenimiento mantenimiento);
	public void eliminar(int idMantenimiento);
	public Optional<Mantenimiento> listarId(int idMantenimiento);
	List<Mantenimiento> listar();
	List<Mantenimiento> buscarNombre(String nombreLibro);
	
	
}