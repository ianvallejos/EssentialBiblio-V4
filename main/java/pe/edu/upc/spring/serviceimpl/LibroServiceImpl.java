package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Libro;
import pe.edu.upc.spring.repository.ILibroDAO;
import pe.edu.upc.spring.service.ILibroService;

@Service
public class LibroServiceImpl implements ILibroService {

	@Autowired
	private ILibroDAO dLibro;

	@Override
	@Transactional
	public boolean insertar(Libro libro) {
		Libro objLibro = dLibro.save(libro);
		if (objLibro == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Libro libro) {
		boolean flag = false;
		try {
			dLibro.save(libro);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idLibro) {

		dLibro.deleteById(idLibro);

	}

	
	@Override
	@Transactional(readOnly=true)
	public Optional<Libro> listarId(int idLibro) {
		return dLibro.findById(idLibro);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Libro> buscarNombre(String nombreLibro) {

		return dLibro.buscarNombre(nombreLibro);

	}

	
	@Override
	@Transactional(readOnly=true)
	public List<Libro> listar() {
		return dLibro.findAll();
	}

}
