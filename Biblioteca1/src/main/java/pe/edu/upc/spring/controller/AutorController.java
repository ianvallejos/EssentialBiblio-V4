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

import pe.edu.upc.spring.model.Autor;
import pe.edu.upc.spring.service.IAutorService;

@Controller
@RequestMapping("/autor")
public class AutorController {
	
	@Autowired
	private IAutorService aService;
	
	@RequestMapping("/bienvenido")
	public String irAutorBienvenido() {
		return "bienvenido";		
	}
	
	@RequestMapping("/")
	public String irAutor(Map<String, Object> model) {
		model.put("listaAutores", aService.listar());
		return "listAutor";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("autor", new Autor());
		return "autor";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Autor objAutor, 
			BindingResult binRes, Model model ) throws ParseException 
	{
		if (binRes.hasErrors()) {
			return "autor";
		}
		else {
			boolean flag = aService.insertar(objAutor);
			if (flag) {
				return "redirect:/autor/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/autor/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Autor objAutor, 
			BindingResult binRes, Model model, RedirectAttributes objRedir ) 
					throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/autor/listar";
		}
		else {
			boolean flag = aService.modificar(objAutor);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				return "redirect:/autor/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/autor/irRegistrar";
			}			
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Autor> objAutor= aService.listarId(id);
		if (objAutor == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "redirect:/autor/listar";
		}
		else {
			model.addAttribute("autor", objAutor);
			return "autor";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				aService.eliminar(id);
				model.put("listaAutor", aService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se puede eliminar un Autor asignado");
			model.put("listaAutores", aService.listar());
		}
		return "listAutor";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaAutores", aService.listar());
		return "listAutor";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Autor autor) throws ParseException {
		aService.listarId(autor.getIdAutor());
		return "listAutor";		
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Autor autor) throws ParseException {
		List<Autor> listaAutores;
	    autor.setNombreAutor(autor.getNombreAutor());
		listaAutores = aService.buscarNombre(autor.getNombreAutor());
		
		
		if (listaAutores.isEmpty()) {
			model.put("mensaje", "No se encontro");						
		}
		model.put("listaAutores", listaAutores);
		return "buscar";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("autor", new Autor());
		return "buscar";
	}
		
}


