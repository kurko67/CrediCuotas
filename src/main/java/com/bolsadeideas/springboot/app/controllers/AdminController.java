package com.bolsadeideas.springboot.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdminController {


    @RequestMapping("/administrador")
    public String index_admin() {

        return "administrator/admin";
    }


}
