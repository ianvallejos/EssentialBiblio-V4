package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.Map;
import java.util.Optional;
import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import pe.edu.upc.spring.model.Mantenimiento;
import pe.edu.upc.spring.service.IMantenimientoService;
import pe.edu.upc.spring.service.ILibroService;

@Controller
@RequestMapping("/mantenimiento")
public class MantenimientoController {
	
	@Autowired
	private IMantenimientoService mService;
	
	@Autowired
	private ILibroService lService;
	
	@RequestMapping("/bienvenido")
	public String irMantenimientoBienvenido() {
		return "bienvenido";		
	}
	
	@RequestMapping("/")
	public String irLibro(Map<String, Object> model) {
		model.put("listaMantenimientos",mService.listar());
		return "listMantenimiento";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("mantenimiento", new Mantenimiento());
		model.addAttribute("listaLibros", lService.listar());
		return "mantenimiento";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Mantenimiento objMantenimiento, 
			BindingResult binRes, Model model ) throws ParseException 
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaLibros", lService.listar());
			return "mantenimiento";
		}
		else {
			boolean flag = mService.insertar(objMantenimiento);
			if (flag) {
				return "redirect:/mantenimiento/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/mantenimiento/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Mantenimiento objMantenimiento, 
			BindingResult binRes, Model model, RedirectAttributes objRedir ) 
					throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/mantenimiento/listar";
		}
		else {
			boolean flag = mService.modificar(objMantenimiento);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				return "redirect:/mantenimiento/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/mantenimiento/irRegistrar";
			}			
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Mantenimiento> objMantenimiento= mService.listarId(id);
		if (objMantenimiento == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "redirect:/mantenimiento/listar";
		}
		else {
		
			model.addAttribute("listaLibros", lService.listar());
			if (objMantenimiento.isPresent())
				objMantenimiento.ifPresent(o -> model.addAttribute("mantenimiento", o));			

			return "mantenimiento";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				mService.eliminar(id);
				model.put("listaMantenimiento", mService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se puede eliminar un Libro asignado");
			model.put("listaMantenimientos", mService.listar());
		}
		return "listMantenimiento";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaMantenimientos", mService.listar());
		return "listMantenimiento";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Mantenimiento mantenimiento) throws ParseException {
		mService.listarId(mantenimiento.getIdMantenimiento());
		return "listMantenimiento";		
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Mantenimiento mantenimiento) throws ParseException {
		List<Mantenimiento> listaMantenimientos;
		mantenimiento.setObserMante(mantenimiento.getObserMante());
		listaMantenimientos = mService.buscarNombre(mantenimiento.getObserMante());
		
		
		if (listaMantenimientos.isEmpty()) {
			model.put("mensaje", "No se encontro");						
		}
		model.put("listaLibros", listaMantenimientos);
		return "buscarMantenimiento";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("mantenimiento", new Mantenimiento());
		return "buscarMantenimiento";
	}
		
}