package com.bolsadeideas.springboot.app.controllers;


import com.bolsadeideas.springboot.app.models.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChartController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/chart")
    public String generateChart(Model model) throws IOException {



        return "/administrador/chart";
    }
}

