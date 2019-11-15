package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Usuario;

public interface IUsuarioService {
	
	public boolean insertar(Usuario usuario);
	public boolean modificar(Usuario usuario);
	public void eliminar(int idUsuario);
	public Optional<Usuario> listarId(int idUsuario);
	List<Usuario> listar();
	List<Usuario> buscarNombre(String nameUsuario);

}
