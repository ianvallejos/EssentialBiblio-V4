package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Ejemplar;

@Repository
public interface IEjemplarDAO extends JpaRepository<Ejemplar, Integer>
{
	@Query("from Ejemplar e where e.libro.nombreLibro like %:nombreLibro%")
	List<Ejemplar> buscarNombre(@Param("nombreLibro") String nombreLibro);
}
