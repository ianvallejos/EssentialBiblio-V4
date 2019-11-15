package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Autor;
import pe.edu.upc.spring.repository.IAutorDAO;
import pe.edu.upc.spring.service.IAutorService;

@Service
public class AutorServiceImpl implements IAutorService {

	@Autowired
	private IAutorDAO aAutor;

	@Override
	@Transactional
	public boolean insertar(Autor autor) {
		Autor objAutor = aAutor.save(autor);
		if (objAutor == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Autor autor) {
		boolean flag = false;
		try {
			aAutor.save(autor);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idAutor) {

		aAutor.deleteById(idAutor);

	}

	
	@Override
	@Transactional(readOnly=true)
	public Optional<Autor> listarId(int idAutor) {
		return aAutor.findById(idAutor);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Autor> buscarNombre(String nombreAutor) {

		return aAutor.buscarNombre(nombreAutor);

	}

	
	@Override
	@Transactional(readOnly=true)
	public List<Autor> listar() {
		return aAutor.findAll();
	}

}

