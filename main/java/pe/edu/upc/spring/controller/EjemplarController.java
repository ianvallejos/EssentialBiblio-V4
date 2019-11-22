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

import pe.edu.upc.spring.model.Ejemplar;
import pe.edu.upc.spring.model.Libro;
import pe.edu.upc.spring.service.IEjemplarService;
import pe.edu.upc.spring.service.ILibroService;

@Controller
@RequestMapping("/ejemplar")
public class EjemplarController {
	
	@Autowired
	private IEjemplarService eService;
	
	@Autowired
	private ILibroService lService;
	
	@RequestMapping("/bienvenido")
	public String irEjemplarBienvenido() {
		return "bienvenido";		
	}
	
	@RequestMapping("/")
	public String irEjemplar(Map<String, Object> model) {
		model.put("listaEjemplares",eService.listar());
		return "listEjemplar";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("ejemplar", new Ejemplar());
		model.addAttribute("listaLibros", lService.listar());
		return "ejemplar";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Ejemplar objEjemplar, 
			BindingResult binRes, Model model ) throws ParseException 
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaLibros", lService.listar());
			return "ejemplar";
		}
		else {
			boolean flag = eService.insertar(objEjemplar);
			if (flag) {
				return "redirect:/ejemplar/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/ejemplar/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Ejemplar objEjemplar, 
			BindingResult binRes, Model model, RedirectAttributes objRedir ) 
					throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/ejemplar/listar";
		}
		else {
			boolean flag = eService.modificar(objEjemplar);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				return "redirect:/ejemplar/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/ejemplar/listar";
			}			
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Ejemplar> objEjemplar= eService.listarId(id);
		if (objEjemplar == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "redirect:/ejemplar/listar";
		}
		else {
		
			model.addAttribute("listaLibros", lService.listar());
			if (objEjemplar.isPresent())
				objEjemplar.ifPresent(o -> model.addAttribute("ejemplar", o));			

			return "ejemplar";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				eService.eliminar(id);
				model.put("listaEjemplar", eService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se puede eliminar un Libro asignado");
			model.put("listaEjemplares", eService.listar());
		}
		return "listEjemplar";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaEjemplares", eService.listar());
		return "listEjemplar";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Ejemplar ejemplar) throws ParseException {
		eService.listarId(ejemplar.getIdEjemplar());
		return "listEjemplar";		
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Libro libro) throws ParseException {
		List<Libro> listaLibros;
		libro.setNombreLibro(libro.getNombreLibro());
		listaLibros = lService.buscarNombre(libro.getNombreLibro());
		
		if (listaLibros.isEmpty()) {
			model.put("mensaje", "No se encontro");						
		}
		model.put("listaLibros", listaLibros);
		return "buscarEjemplar";		
	}
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("libro", new Libro());
		return "buscarEjemplar";
	}
		
}