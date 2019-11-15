package pe.edu.upc.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.spring.model.Prestamo;


public interface IPrestamoDAO extends JpaRepository<Prestamo, Integer>{

	@Query("from Prestamo p where p.usuario.idUsuario like %:idUsuario%")
	List<Prestamo> buscarUsuario(@Param("idUsuario") int idUsuario);
	
	@Query("from Prestamo p where p.idPrestamo like %:idPrestamo%")
	List<Prestamo> buscarPrestamo(@Param("idPrestamo") int idPrestamo);
	
	
	List<Prestamo> findByFechaDevolucion(Date fechaDevolucion);
}
