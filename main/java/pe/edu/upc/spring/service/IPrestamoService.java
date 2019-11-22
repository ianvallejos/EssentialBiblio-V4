package pe.edu.upc.spring.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Prestamo;

public interface IPrestamoService {

	public boolean insertar(Prestamo prestamo);
	public boolean modificar(Prestamo prestamo);
	public void eliminar(int idPrestamo);
	public Optional<Prestamo> listarId(int idPrestamo);
	List<Prestamo> listar();
	List<Prestamo> buscarUsuario(int idUsuario);
	List<Prestamo> buscarPrestamo(int idPrestamo);
	List<Prestamo> findByFechaDevolucion(Date fechaDevolucion);
}
