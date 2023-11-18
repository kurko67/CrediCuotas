package com.bolsadeideas.springboot.app.controllers;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.bolsadeideas.springboot.app.models.dao.IUsuarioDao;
import com.bolsadeideas.springboot.app.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private Environment environment;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private IUsuarioDao usuarioDao;

	@RequestMapping(value = "/administrador/admin")
	public String listar(@RequestParam(name="page", defaultValue="0") int page, Model model, @AuthenticationPrincipal User user, HttpServletRequest request, HttpServletResponse response, RedirectAttributes flash) {

		Usuario usuario = usuarioDao.findByUsername(user.getUsername());

		System.out.println(usuario.getHabilitado());

		if(usuario.getHabilitado() == false){

			//cerrar la sesion
			new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
			return "redirect:/login?error=1"; // Redirige a la página de inicio de sesión u otra página apropiada.

		}


		
		Pageable pageRequest = PageRequest.of(page, 8);
		Page<Cliente> clientes = clienteService.findAllByVendedorIsnull(pageRequest);
		
		PageRender<Cliente> pageRender = new PageRender<Cliente>("/administrador/admin", clientes);

		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		model.addAttribute("user", user.getUsername());
		return "administrador/admin";
	}

	@RequestMapping(value = "/administrador/busqueda-estado")
	public String busquedaClienteByEstado(@RequestParam(name="estado", defaultValue="") String estado ,@RequestParam(name="page", defaultValue="0") int page, Model model, @AuthenticationPrincipal User user) {

		Pageable pageRequest = PageRequest.of(page, 8);

		if (estado.equals("todos")){
				Page<Cliente> clientes = clienteService.findAllByVendedorTodos(user.getUsername(),pageRequest);
				PageRender<Cliente> pageRender = new PageRender<Cliente>("/administrador/mis_solicitudes", clientes);
				model.addAttribute("clientes", clientes);
				model.addAttribute("page", pageRender);
				model.addAttribute("user", user.getUsername());

		}else{
				Page<Cliente> clientes = clienteService.findAllByVendedorAndEstado(estado, user.getUsername(),pageRequest);
				PageRender<Cliente> pageRender = new PageRender<Cliente>("/administrador/mis_solicitudes", clientes);
				model.addAttribute("clientes", clientes);
				model.addAttribute("page", pageRender);
				model.addAttribute("user", user.getUsername());

			}

		return "administrador/mis_solicitudes";

		}

	@RequestMapping(value = "/administrador/resultado_busqueda")
	public String ControlbusquedaClienteByEstado(@RequestParam(name="estado", defaultValue="") String estado ,@RequestParam(name="page", defaultValue="0") int page, Model model, @AuthenticationPrincipal User user) {

		String rol = "";

		for(int i=0; i < user.getAuthorities().size(); i++){
			rol = user.getAuthorities().toString();
		}

		Pageable pageRequest = PageRequest.of(page, 10);

		if (estado.equals("todos")){

			if(rol.equals("[ROLE_ADMIN]")){
				Page<Cliente> clientes = clienteService.findAllByIdOrdeByDesc(pageRequest);
				PageRender<Cliente> pageRender = new PageRender<Cliente>("/administrador/resultado_busqueda", clientes);
				model.addAttribute("estado", estado);
				model.addAttribute("clientes", clientes);
				model.addAttribute("page", pageRender);
				model.addAttribute("user", user.getUsername());

			}else{
				Page<Cliente> clientes = clienteService.findAllByVendedorTodos(user.getUsername(),pageRequest);
				PageRender<Cliente> pageRender = new PageRender<Cliente>("/administrador/resultado_busqueda", clientes);
				model.addAttribute("estado", estado);
				model.addAttribute("clientes", clientes);
				model.addAttribute("page", pageRender);
				model.addAttribute("user", user.getUsername());
			}


			return "administrador/resultado_busqueda";

		}else{

			if(rol.equals("[ROLE_ADMIN]")){
				Page<Cliente> clientes = clienteService.findAllByEstado(estado,pageRequest);
				PageRender<Cliente> pageRender = new PageRender<Cliente>("/administrador/resultado_busqueda", clientes);
				model.addAttribute("estado", estado);
				model.addAttribute("clientes", clientes);
				model.addAttribute("page", pageRender);
				model.addAttribute("user", user.getUsername());
			}else{
				Page<Cliente> clientes = clienteService.findAllByVendedorAndEstado(estado, user.getUsername(),pageRequest);
				PageRender<Cliente> pageRender = new PageRender<Cliente>("/administrador/resultado_busqueda", clientes);
				model.addAttribute("estado", estado);
				model.addAttribute("clientes", clientes);
				model.addAttribute("page", pageRender);
				model.addAttribute("user", user.getUsername());
			}


			return "administrador/resultado_busqueda";
		}



	}

	@RequestMapping(value = "administrador/busqueda")
	public String busquedaCliente(@RequestParam(name="query", defaultValue="") String cuil ,@RequestParam(name="page", defaultValue="0") int page, Model model, @AuthenticationPrincipal User user) {

		Pageable pageRequest = PageRequest.of(page, 8);
		Page<Cliente> clientes = clienteService.findAllByVendedorAndCuit(cuil, user.getUsername(),pageRequest);

		PageRender<Cliente> pageRender = new PageRender<Cliente>("/administrador/mis_solicitudes", clientes);

		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		model.addAttribute("user", user.getUsername());
		return "administrador/mis_solicitudes";
	}

	@RequestMapping(value = "administrador/busqueda-control")
	public String busquedaControl(@RequestParam(name="query", defaultValue="") String cuil ,@RequestParam(name="page", defaultValue="0") int page, Model model, @AuthenticationPrincipal User user) {

		Pageable pageRequest = PageRequest.of(page, 8);
		Page<Cliente> clientes = clienteService.ControlfindAllByCuil(cuil, pageRequest);

		PageRender<Cliente> pageRender = new PageRender<Cliente>("/administrador/control", clientes);

		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		model.addAttribute("user", user.getUsername());
		return "administrador/control";
	}

	@RequestMapping(value = "/administrador/abusqueda")
	public String busquedaClienteVendedorNull(@RequestParam(name="query", defaultValue="") String cuil ,@RequestParam(name="page", defaultValue="0") int page, Model model, @AuthenticationPrincipal User user) {

		Pageable pageRequest = PageRequest.of(page, 8);
		Page<Cliente> clientes = clienteService.findAllByCuitAndVendedorIsNull(cuil,pageRequest);

		PageRender<Cliente> pageRender = new PageRender<Cliente>("/administrador/admin", clientes);

		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		model.addAttribute("user", user.getUsername());
		return "administrador/admin";
	}





	@RequestMapping(value = "/administrador/mis_solicitudes")
	public String misSolicitudes(@RequestParam(name="page", defaultValue="0") int page, Model model, @AuthenticationPrincipal User user) {

		Pageable pageRequest = PageRequest.of(page, 8);
		Page<Cliente> clientes = clienteService.findAllByVendedor(user.getUsername(), pageRequest);

		PageRender<Cliente> pageRender = new PageRender<Cliente>("/administrador/mis_solicitudes", clientes);

		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		model.addAttribute("user", user.getUsername());

		return "administrador/mis_solicitudes";
	}

	@RequestMapping(value = "/administrador/control")
	public String adminControlSolicitudes(@RequestParam(name="page", defaultValue="0") int page, Model model, @AuthenticationPrincipal User user) {

		Pageable pageRequest = PageRequest.of(page, 8);
		Page<Cliente> clientes = clienteService.findAllByIdOrdeByDesc(pageRequest);

		PageRender<Cliente> pageRender = new PageRender<Cliente>("/administrador/control", clientes);

		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		model.addAttribute("user", user.getUsername());

		return "administrador/control";
	}

	@RequestMapping(value = "/")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		return "index";
	}

	@RequestMapping(value = "/administrador/form")
	public String crear_nuevo_cliente(Map<String, Object> model, @AuthenticationPrincipal User user) {
		String usuario = user.getUsername();
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("user", usuario);
		return "administrador/form";
	}

	@RequestMapping(value = "/administrador/form/{id}")
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

		List<Usuario> usuarios = usuarioDao.findAll();

		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		model.put("user", user.getUsername());
		model.put("usuarios", usuarios);
		return "administrador/form";
	}

	@RequestMapping(value = "/administrador/ver_mas/{id}")
	public String ver_mas(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash, @AuthenticationPrincipal User user) {

		Cliente cliente = null;
		cliente = clienteService.findOne(id);

		if(cliente.getVendedor() == null){
			System.out.println("El cliente no tiene asignado un vendedor");
			cliente.setVendedor(user.getUsername());
		}

		String rol = "";

		for(int i=0; i < user.getAuthorities().size(); i++){
			rol = user.getAuthorities().toString();
		}

		model.put("role", rol);

		if (cliente == null){
			flash.addFlashAttribute("error", "El cliente o solicitud no existe");
			model.put("user", user.getUsername());
			return "redirect:/administrador/mis_solicitudes";
		}else{
			// si el que genero la orden no es el mismo usuario que lo quiere ver, no lo puede ver
			if(!cliente.getVendedor().equals(user.getUsername())){
				// pero si es un admin si lo puede ver
				if (rol.equals("[ROLE_ADMIN]")){
					System.out.println("es admin");
					if (id > 0){

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
				flash.addFlashAttribute("info", "La solicitud que intentar ver pertenece a otro usuario");
				model.put("user", user.getUsername());
				return "redirect:/administrador/mis_solicitudes";
			}else{
				if (id > 0) {

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

		}


	}

	@RequestMapping(value = "/solicitud")
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status,
						  @RequestParam("g-recaptcha-response") String recaptchaResponse) {

		String secretKey = environment.getProperty("recaptcha.secretKey");
		String verificationUrl = "https://www.google.com/recaptcha/api/siteverify";
		String params = "secret=" + secretKey + "&response=" + recaptchaResponse;

		System.out.println(recaptchaResponse);

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(verificationUrl, null, String.class, params);

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "index";
		}

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			String mensajeFlash = (cliente.getIdCliente() != null) ? "Cliente editado con éxito!" : "Cliente creado con éxito!";
			clienteService.save(cliente);
			status.setComplete();
			flash.addFlashAttribute("success", "¡Solicitud registrada correctamente!");
			return "redirect:/";

		} else {
			// Verificación fallida
			return "/";
		}


	}

	@RequestMapping(value = "/administrador/nueva_solicitud")
	public String guardar_adm(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status,
							  @AuthenticationPrincipal User user, @RequestParam(name="estado") String estado) {

		if (result.hasErrors()) {
			model.addAttribute("default_state", "EN PROCESO");
			model.addAttribute("user", user.getUsername());
			return "administrador/form";
		}

		String mensajeFlash = (cliente.getIdCliente() != null) ? "Solicitud editada con éxito!" : "Solicitud creada con éxito!";

		if(estado.equals("LIQUIDADO") || estado.equals("RECHAZADO")){
			cliente.setClosedAt(new Date());
		}

		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);

		String rol = "";

		for(int i=0; i < user.getAuthorities().size(); i++){
			rol = user.getAuthorities().toString();
		}

		if (rol.equals("[ROLE_ADMIN]")){
			return "redirect:/administrador/control";
		}else{
			return "redirect:/administrador/mis_solicitudes";
		}


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

		return Arrays.asList("EMPLEADO PRIVADO", "EMPLEADO PUBLICO NACIONAL", "EMPLEADO PUBLICO PROVINCIAL", "JUBILADO Y/O PENSIONADO","FUERZAS ARMADAS", "RETIRADO DE LA FUERZA", "PENSION GRACIABLE" );

	}

	@ModelAttribute("canal")
	public List<String> canal(){

		return Arrays.asList("FACEBOOK", "INSTAGRAM", "PAGINA WEB", "LOCAL", "FOLLETO", "OTRO");

	}

	@ModelAttribute("estado")
	public List<String> estado(){

		return Arrays.asList("SIN ESTADO","ENVIADO", "RESP. DE DISPONIBLE", "OBSERVADO", "RECHAZADO", "LIQUIDADO");

	}

	@ModelAttribute("financiador")
	public List<String> financiador(){

		return Arrays.asList("SIN FINANCIADOR","ARGENPESOS","BANCO COINAG", "CALABRIA","COORDINAR", "CRISTAL","DINERO FULL","GERMANUS","GRAN COOPERATIVA", "OK CREDITOS","REGIONAL");

	}








}