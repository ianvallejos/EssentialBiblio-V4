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

import pe.edu.upc.spring.model.Libro;
import pe.edu.upc.spring.service.ILibroService;

@Controller
@RequestMapping("/libro")
public class LibroController {
	
	@Autowired
	private ILibroService lService;
	
	@RequestMapping("/bienvenido")
	public String irLibroBienvenido() {
		return "bienvenido";		
	}
	
	@RequestMapping("/")
	public String irLibro(Map<String, Object> model) {
		model.put("listaLibros", lService.listar());
		return "listLibro";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("libro", new Libro());
		return "libro";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Libro objLibro, 
			BindingResult binRes, Model model ) throws ParseException 
	{
		if (binRes.hasErrors()) {
			return "libro";
		}
		else {
			boolean flag = lService.insertar(objLibro);
			if (flag) {
				return "redirect:/libro/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/libro/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Libro objLibro, 
			BindingResult binRes, Model model, RedirectAttributes objRedir ) 
					throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/libro/listar";
		}
		else {
			boolean flag = lService.modificar(objLibro);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				return "redirect:/libro/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/libro/irRegistrar";
			}			
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Libro> objLibro= lService.listarId(id);
		if (objLibro == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "redirect:/libro/listar";
		}
		else {
			model.addAttribute("libro", objLibro);
			return "libro";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				lService.eliminar(id);
				model.put("listaLibro", lService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se puede eliminar un Libro asignado");
			model.put("listaAutores", lService.listar());
		}
		return "listLibro";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaLibros", lService.listar());
		return "listLibro";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Libro libro) throws ParseException {
		lService.listarId(libro.getIdLibro());
		return "listAutor";		
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
		return "buscarLibro";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("libro", new Libro());
		return "buscarLibro";
	}
		
}