package pe.edu.upc.spring.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Mantenimiento;

@Repository
public interface IMantenimientoDAO extends JpaRepository<Mantenimiento, Integer>
{
	
	@Query("from Mantenimiento m where m.libro.nombreLibro like %:nombreLibro%")
	List<Mantenimiento> buscarNombre(@Param("nombreLibro") String nombreLibro);
}
