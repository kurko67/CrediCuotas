package com.bolsadeideas.springboot.app.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdminController {


    @RequestMapping("/administrador")
    public String index_admin(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user.getUsername());
        return "administrador/admin";
    }


}
