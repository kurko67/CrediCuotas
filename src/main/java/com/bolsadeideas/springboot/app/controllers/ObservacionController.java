package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class ObservacionController {


    @RequestMapping(value = "/administradorrm")
    public String crear_nuevo_ce(Map<String, Object> model, @AuthenticationPrincipal User user) {
        String usuario = user.getUsername();
        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("user", usuario);
        return "/administrador/form";
    }



}
