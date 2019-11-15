package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Autor;

@Repository
public interface IAutorDAO extends JpaRepository<Autor, Integer>
{
	@Query("from Autor a where a.nombreAutor like %:nombreAutor%")
	List<Autor> buscarNombre(@Param("nombreAutor") String nombreAutor);
	

}
