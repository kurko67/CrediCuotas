package com.bolsadeideas.springboot.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {


    @GetMapping("/administrador")
    public String index_admin() {

        return "/administrator/administrator";
    }


}
