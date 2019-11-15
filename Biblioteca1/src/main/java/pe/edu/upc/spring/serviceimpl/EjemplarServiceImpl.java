package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Ejemplar;
import pe.edu.upc.spring.repository.IEjemplarDAO;
import pe.edu.upc.spring.service.IEjemplarService;

@Service
public class EjemplarServiceImpl implements IEjemplarService {

	@Autowired
	private IEjemplarDAO dEjemplar;

	@Override
	@Transactional
	public boolean insertar(Ejemplar ejemplar) {
		Ejemplar objEjemplar = dEjemplar.save(ejemplar);
		if (objEjemplar == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Ejemplar ejemplar) {
		boolean flag = false;
		try {
			dEjemplar.save(ejemplar);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idEjemplar) {

		dEjemplar.deleteById(idEjemplar);

	}

	
	@Override
	@Transactional(readOnly=true)
	public Optional<Ejemplar> listarId(int idEjemplar) {
		return dEjemplar.findById(idEjemplar);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Ejemplar> buscarNombre(String nombreLibro) {

		return dEjemplar.buscarNombre(nombreLibro);

	}

	
	@Override
	@Transactional(readOnly=true)
	public List<Ejemplar> listar() {
		return dEjemplar.findAll();
	}

}