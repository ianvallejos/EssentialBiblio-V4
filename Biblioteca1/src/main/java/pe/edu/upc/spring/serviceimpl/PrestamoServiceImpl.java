package pe.edu.upc.spring.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Prestamo;

import pe.edu.upc.spring.repository.IPrestamoDAO;
import pe.edu.upc.spring.service.IPrestamoService;

@Service
public class PrestamoServiceImpl implements IPrestamoService{

	@Autowired
	private IPrestamoDAO pPrestamo;

	@Override
	@Transactional
	public boolean insertar(Prestamo prestamo) {
		Prestamo objPrestamo = pPrestamo.save(prestamo);
		if (objPrestamo == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Prestamo prestamo) {
		boolean flag = false;
		try {
			pPrestamo.save(prestamo);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idPrestamo) {

		pPrestamo.deleteById(idPrestamo);

	}

	@Override
	public Optional<Prestamo> listarId(int idPrestamo) {
		return pPrestamo.findById(idPrestamo);		
	}

	
	@Override
	public List<Prestamo> listar() {

		return pPrestamo.findAll();

	}
	
	@Override
	public List<Prestamo> buscarUsuario(int idUsuario){
		return pPrestamo.buscarUsuario(idUsuario);
	}
	
	@Override
	public List<Prestamo> buscarPrestamo(int idPrestamo){
		return pPrestamo.buscarPrestamo(idPrestamo);
	}
	
	@Override
	public List<Prestamo> findByFechaDevolucion(Date fechaDevolucion){
		return pPrestamo.findByFechaDevolucion(fechaDevolucion);
	}

}
