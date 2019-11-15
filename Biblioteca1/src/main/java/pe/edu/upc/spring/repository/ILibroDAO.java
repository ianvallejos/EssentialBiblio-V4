package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Libro;

@Repository
public interface ILibroDAO extends JpaRepository<Libro, Integer>
{
	@Query("from Libro l where l.nombreLibro like %:nombreLibro%")
	List<Libro> buscarNombre(@Param("nombreLibro") String nombreLibro);
}
