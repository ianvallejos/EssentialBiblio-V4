package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.LibroAutor;

@Repository
public interface ILibroAutorDAO extends JpaRepository<LibroAutor, Integer>
{
	@Query("from LibroAutor la where la.libro.nombreLibro like %:nombreLibro%")
	List<LibroAutor> buscarNombreLibro(@Param("nombreLibro") String nombreLibro);
	
	@Query("from LibroAutor la where la.autor.nombreAutor like %:nombreAutor%")
	List<LibroAutor> buscarNombreAutor(@Param("nombreAutor") String nombreAutor);
}
