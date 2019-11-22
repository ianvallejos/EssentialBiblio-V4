package pe.edu.upc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Usuarioo;


@Repository
public interface IUsuariooDao extends JpaRepository<Usuarioo, Long> {

	public Usuarioo findByUsername(String username);

}
