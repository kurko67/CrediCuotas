package com.bolsadeideas.springboot.app.controllers;


import com.bolsadeideas.springboot.app.models.dao.IUsuarioDao;
import com.bolsadeideas.springboot.app.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;


@Controller
public class LoginController {

    @Autowired
    private IUsuarioDao usuarioDao;

    @GetMapping("/login")
    public String login(@RequestParam(value="error",required = false ) String error,
                        Model model, Principal principal, RedirectAttributes flash){


        if(principal != null){
            flash.addFlashAttribute("info", "Hola de nuevo: " + principal.getName().toString());
            return "redirect:/administrador/admin";
        }

        if(error != null){
            model.addAttribute("error", "Error: Nombre de usuario o contraseña incorrecta o usuario no habilitado.");
        }


        return "login";

    }


}
