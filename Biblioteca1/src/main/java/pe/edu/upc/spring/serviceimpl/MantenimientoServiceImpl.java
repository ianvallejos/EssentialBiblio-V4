package pe.edu.upc.spring.serviceimpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.edu.upc.spring.model.Mantenimiento;
import pe.edu.upc.spring.repository.IMantenimientoDAO;
import pe.edu.upc.spring.service.IMantenimientoService;

@Service
public class MantenimientoServiceImpl implements IMantenimientoService {

	@Autowired
	private IMantenimientoDAO mMantenimiento;

	@Override
	@Transactional
	public boolean insertar(Mantenimiento mantenimiento) {
		Mantenimiento objMantenimiento = mMantenimiento.save(mantenimiento);
		if (objMantenimiento == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean modificar(Mantenimiento mantenimiento) {
		boolean flag = false;
		try {
			mMantenimiento.save(mantenimiento);
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idMantenimiento) {

		mMantenimiento.deleteById(idMantenimiento);

	}

	
	@Override
	@Transactional(readOnly=true)
	public Optional<Mantenimiento> listarId(int idMantenimiento) {
		return mMantenimiento.findById(idMantenimiento);
	}

	
	@Override
	public List<Mantenimiento>buscarNombre(String nombreLibro ) {

		return mMantenimiento.buscarNombre(nombreLibro);

	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Mantenimiento> listar() {
		return mMantenimiento.findAll();
	}

}