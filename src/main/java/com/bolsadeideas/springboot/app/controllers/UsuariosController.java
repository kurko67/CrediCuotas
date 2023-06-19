package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.dao.IUsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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


}
