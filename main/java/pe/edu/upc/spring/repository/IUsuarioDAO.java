package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Usuario;

@Repository
public interface IUsuarioDAO extends JpaRepository<Usuario, Integer> {

	@Query("from Usuario u where u.nameUsuario like %:nameUsuario%")
	List<Usuario> buscarNombre(@Param("nameUsuario") String nameUsuario);
	

}
