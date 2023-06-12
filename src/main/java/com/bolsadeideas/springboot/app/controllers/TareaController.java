package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.dao.IUsuarioDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Tarea;
import com.bolsadeideas.springboot.app.models.entity.Usuario;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.models.service.ITareaService;
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
import java.util.Map;

@Controller
@SessionAttributes("tarea")
public class TareaController {
    @Autowired
    private IClienteService clienteService;
    @Autowired
    private ITareaService tareaService;

    @Autowired
    private IUsuarioDao usuarioDao;

    @GetMapping("administrador/tareas/{idcliente}")
    public String crear_tareas(@PathVariable(value = "idcliente") Long idcliente, Model model, RedirectAttributes flash, @AuthenticationPrincipal User user){

        Cliente cliente = clienteService.findOne(idcliente);
        Usuario usuario = usuarioDao.findByUsername(user.getUsername());

        if(cliente == null){
            flash.addFlashAttribute("error", "El cliente ingresado no existe en la base de datos");
            return "redirect:/administrador/mis_solicitudes";
        }

        Tarea tarea = new Tarea();
        tarea.setCliente(cliente);
        tarea.setUsername(usuario);

        model.addAttribute("tarea", tarea);

        return "administrador/tareas";

    }

    @PostMapping("administrador/tareas")
    public String guardarTareas(@Valid Tarea tareas, BindingResult result,
                                RedirectAttributes flash, SessionStatus status){

        if(result.hasErrors()){
            return "/administrador/tareas";
        }

        tareas.setUpdateAt(new Date());
        tareaService.save(tareas);
        status.setComplete();
        flash.addFlashAttribute("success", "Tarea creada con exito");
        return "redirect:/administrador/ver_mas/" + tareas.getCliente().getIdCliente();

    }

    @RequestMapping(value = "administrador/listar_tareas")
    public String ver_mas(Model model, RedirectAttributes flash, @AuthenticationPrincipal User user) {

        Usuario usuario = usuarioDao.findByUsername(user.getUsername());

        model.addAttribute("user", user.getUsername());
        model.addAttribute("tareas", tareaService.findAllByUsernameOrderByDesc(usuario.getIdUsuario()));
        return "administrador/listar_tareas";
    }

}
