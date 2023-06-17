package com.bolsadeideas.springboot.app.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@RequestMapping(value = "administrador/admin")
	public String listar(@RequestParam(name="page", defaultValue="0") int page, Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 8);
		
		Page<Cliente> clientes = clienteService.findAllByVendedorIsnull(pageRequest);
		
		PageRender<Cliente> pageRender = new PageRender<Cliente>("/administrador/admin", clientes);
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		return "administrador/admin";
	}


	@RequestMapping(value = "administrador/mis_solicitudes")
	public String misSolicitudes(Model model, @AuthenticationPrincipal User user) {
		model.addAttribute("user", user.getUsername());
		model.addAttribute("clientes", clienteService.findAllByVendedor(user.getUsername()));
		return "administrador/mis_solicitudes";
	}

	@RequestMapping(value = "/apply-now")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		return "apply-now";
	}

	@RequestMapping(value = "administrador/form")
	public String crear_nuevo_cliente(Map<String, Object> model, @AuthenticationPrincipal User user) {
		String usuario = user.getUsername();
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("user", usuario);
		return "administrador/form";
	}

	@RequestMapping(value = "administrador/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash, @AuthenticationPrincipal User user) {

		Cliente cliente = null;

		if (id > 0) {
			cliente = clienteService.findOne(id);
			if (cliente == null) {
				flash.addFlashAttribute("error", "El ID del cliente no existe en la BBDD!");
				model.put("user", user.getUsername());
				return "redirect:administrador/admin";
			}
		} else {

			flash.addFlashAttribute("error", "El ID del cliente no puede ser cero!");
			model.put("user", user.getUsername());
			return "administrador/admin";

		}

		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		model.put("user", user.getUsername());
		return "administrador/form";
	}

	@RequestMapping(value = "administrador/ver_mas/{id}")
	public String ver_mas(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash, @AuthenticationPrincipal User user) {

		Cliente cliente = null;

		if (id > 0) {
			cliente = clienteService.findOne(id);
			if (cliente == null) {
				flash.addFlashAttribute("error", "El ID del cliente no existe en la BBDD!");
				model.put("user", user.getUsername());
				return "redirect:/administrador/mis_solicitudes";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del cliente no puede ser cero!");
			model.put("user", user.getUsername());
			return "administrador/mis_solicitudes";

		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		model.put("user", user.getUsername());
		return "administrador/ver_mas";
	}

	@RequestMapping(value = "/solicitud")
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "apply-now";
		}

		String mensajeFlash = (cliente.getIdCliente() != null) ? "Cliente editado con éxito!" : "Cliente creado con éxito!";

		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/thank-you";
	}

	@RequestMapping(value = "/administrador/nueva_solicitud")
	public String guardar_adm(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status, @AuthenticationPrincipal User user) {
		if (result.hasErrors()) {
			model.addAttribute("default_state", "EN PROCESO");
			model.addAttribute("user", user.getUsername());
			return "/administrador/form";
		}

		String mensajeFlash = (cliente.getIdCliente() != null) ? "Solicitud editada con éxito!" : "Solicitud creada con éxito!";

		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/administrador/mis_solicitudes";
	}



	@RequestMapping(value = "/administrador/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con éxito!");
		}
		return "redirect:/listar";
	}

	@ModelAttribute("provincia")
	public List<String> provincias(){

		return Arrays.asList("BUENOS AIRES", "CATAMARCA", "CHACO", "CHUBUT", "CORDOBA","CORRIENTES",
							"ENTRE RIOS","FORMOSA", "JUJUY", "LA PAMPA", "LA RIOJA", "MENDOZA", "MISIONES",
				"NEUQUEN", "RIO NEGRO", "SALTA", "SAN JUAN", "SAN LUIS", "SANTA CRUZ", "SANTA FE", "SANTIAGO DEL ESTERO",
				"TIERRA DEL FUEGO", "TUCUMAN");

	}

	@ModelAttribute("situacion_laboral")
	public List<String> situacion_laboral(){

		return Arrays.asList("Asig. Univ. por Hijo", "Desocupado", "Empleado Publico", "Jubilado", "Pensionado",
							"Resp. Inscripto");

	}

	@ModelAttribute("canal")
	public List<String> canal(){

		return Arrays.asList("FACEBOOK", "INSTAGRAM", "PAGINA WEB", "LOCAL", "FOLLETO", "OTRO");

	}

	@ModelAttribute("estado")
	public List<String> estado(){

		return Arrays.asList("RECIBIDO","CARGADO", "EN PROCESO", "RECHAZADO", "ANULADO", "TERMINADO", "APROBADO");

	}

	@ModelAttribute("financiador")
	public List<String> financiador(){

		return Arrays.asList("SIN FINANCIADOR","BANCO A", "BANCO B", "BANCO C", "BANCO D", "BANCO F");

	}






}