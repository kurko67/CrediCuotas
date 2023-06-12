package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.dao.IUsuarioDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Observacion;
import com.bolsadeideas.springboot.app.models.entity.Tarea;
import com.bolsadeideas.springboot.app.models.entity.Usuario;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.models.service.IObservacionService;
import com.bolsadeideas.springboot.app.models.service.ITareaService;
import com.bolsadeideas.springboot.app.models.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;


@Controller
@SessionAttributes("observacion")
public class AdminController {


    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IObservacionService observacionService;

    @Autowired
    private IUsuarioDao usuarioDao;



    @RequestMapping("/administrador")
    public String index_admin(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user.getUsername());
        return "administrador/admin";
    }

    @RequestMapping("/administrador/estadisticas")
    public String estadisticas(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user.getUsername());
        return "administrador/estadisticas";
    }

    @RequestMapping("/administrador/tareas")
    public String tareas(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user.getUsername());
        return "administrador/tareas";
    }

    @RequestMapping("/administrador/usuarios")
    public String usuarios(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user.getUsername());
        return "administrador/usuarios";
    }

    @GetMapping("administrador/observacion/{idcliente}")
    public String crear_observacion(@PathVariable(value = "idcliente") Long idcliente,Model model, RedirectAttributes flash){

        Cliente cliente = clienteService.findOne(idcliente);

        if(cliente == null){
            flash.addFlashAttribute("error", "El cliente ingresado no existe en la base de datos");
            return "redirect:/administrador/mis_solicitudes";
        }

        Observacion observacion = new Observacion();
        observacion.setCliente(cliente);

        model.addAttribute("observacion", observacion);

        return "administrador/observacion";

    }

    @PostMapping("administrador/observacion")
    public String guardarObservacion(@Valid Observacion observacion, BindingResult result,
                                     RedirectAttributes flash, SessionStatus status){

        if(result.hasErrors()){
            return "/administrador/observacion";
        }

        observacionService.save(observacion);
        status.setComplete();
        flash.addFlashAttribute("success", "Observacion creada con exito");
        return "redirect:/administrador/ver_mas/" + observacion.getCliente().getIdCliente();

    }





}
