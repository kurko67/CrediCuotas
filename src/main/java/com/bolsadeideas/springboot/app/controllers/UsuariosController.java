package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.dao.IUsuarioDao;
import com.bolsadeideas.springboot.app.models.entity.Rol;
import com.bolsadeideas.springboot.app.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
public class UsuariosController {

    @Autowired
    private IUsuarioDao usuarioDao;
    @RequestMapping(value = "administrador/usuarios")
    public String ver_usuarios(Model model){
        model.addAttribute("lista_users", usuarioDao.findAll());
        System.out.println(usuarioDao.findAll());
        return "administrador/usuarios";
    }

    @RequestMapping(value = "administrador/editar_usuario/{id}")
    public String editar_usuario(@PathVariable(value = "id") Long id, RedirectAttributes flash, Model model){

        Usuario usuario = null;
        usuario = usuarioDao.getOne(id);


        if(usuario.getUsername().equals(null)){

            flash.addFlashAttribute("error", "El usuario no existe");
            return "redirect:/administrador/usuarios";

        }else{

            if (id > 0){

                model.addAttribute("usuario", usuario);
                return "/administrador/editar_usuario";

            }else{

                flash.addFlashAttribute("error", "El usuario no existe");
                return "redirect:/administrador/usuarios";
            }
        }

    }

    @RequestMapping(value = "/administrador/guardar_usuario")
    public String guardarUser(@Valid Usuario usuario, BindingResult result, RedirectAttributes flash, SessionStatus status,
                              @RequestParam(name = "password") String newPasword,
                              @RequestParam(name = "roles", required = false) String roles,
                              @RequestParam(name = "idUsuario") String idUsuario){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setPassword(encoder.encode(newPasword));

        //usuarioDao.save(usuario);
        usuarioDao.UpdatePassword(encoder.encode(newPasword),usuario.getIdUsuario());
        status.setComplete();
        flash.addFlashAttribute("success", "Usuario editado con exito");
        return "redirect:/administrador/usuarios";
    }

    @ModelAttribute("roles")
    public List<String> roles(){

        return Arrays.asList("ROLE_ADMIN","ROLE_USER");

    }

       /*

                para comparar las contraseñas
                BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
                enconder.matches(password de html, usuario.getPassword());

                */

}
