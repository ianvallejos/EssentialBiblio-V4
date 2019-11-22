package pe.edu.upc.spring.controller;

import java.text.ParseException;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.service.IUsuarioService;
import pe.edu.upc.spring.service.IPrestamoService;
import pe.edu.upc.spring.model.Prestamo;
import pe.edu.upc.spring.service.IEjemplarService;


@Controller
@RequestMapping("/prestamo")
public class PrestamoController {

	@Autowired
	private IUsuarioService uService;
	
	@Autowired
	private IPrestamoService pService;
	
	@Autowired
	private IEjemplarService eService;	
	
	@RequestMapping("/bienvenido")
	public String irMantenimientoPrestamo() {
		return "bienvenido";		
	}
	@RequestMapping("/")
	public String irPrestamo(Map<String, Object> model) {
		model.put("listaPrestamo", pService.listar());
		return "listPrestamo";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("prestamo", new Prestamo());
		model.addAttribute("listaUsuarios", uService.listar());
		model.addAttribute("listaEjemplares", eService.listar());
		return "prestamo";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Prestamo objPrestamo, 
			BindingResult binRes, Model model ) throws ParseException 
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsuarios", uService.listar());
			model.addAttribute("listaEjemplares", eService.listar());
			return "prestamo";
		}
		else {
			objPrestamo.setPrecio(4* objPrestamo.getCantidad());
			boolean flag = pService.insertar(objPrestamo);
			if (flag) {
				
				return "redirect:/prestamo/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/prestamo/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Prestamo objPrestamo, 
			BindingResult binRes, Model model, RedirectAttributes objRedir ) 
					throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/prestamo/listar";
		}
		else {
			boolean flag = pService.modificar(objPrestamo);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				return "redirect:/prestamo/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/prestamo/listar";
			}			
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar (@PathVariable int id, Model model, 
			RedirectAttributes objRedir) 
	{
		
		Optional<Prestamo> objPrestamo = pService.listarId(id);
						
		if (objPrestamo == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "redirect:/prestamo/listar";
		}
		else {
			
						
			model.addAttribute("listaUsuarios", uService.listar());
			model.addAttribute("listaEjemplares", eService.listar());
			if (objPrestamo.isPresent())
				objPrestamo.ifPresent(o -> model.addAttribute("prestamo", o));			

			return "prestamo";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		if (id!=null && id>0) {
			pService.eliminar(id);
			model.put("listaPrestamos", pService.listar());
		}
		return "listPrestamo";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaPrestamos", pService.listar());
		return "listPrestamo";
	}
	//
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Prestamo prestamo) throws ParseException {
		pService.listarId(prestamo.getIdPrestamo());
		return "listPrestamo";		
	}
	
	
}
