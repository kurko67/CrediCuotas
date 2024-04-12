package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.view.xlsx.ExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/export")
public class ExportController {

    private final ExcelExporter excelExporter;

    @Autowired
    private IClienteService clienteService;

    public ExportController(ExcelExporter excelExporter) {
        this.excelExporter = excelExporter;
    }

    @GetMapping("/excel")
    public String exportToExcel(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
                                @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {

        List<Cliente> data = clienteService.findBycreateAtBetween(fechaInicio, fechaFin);
        //List<Cliente> data = clienteService.findAll();
        crearDirectorio();
        String filePath = "C:/credicuota/excel.xlsx"; // Ruta y nombre del archivo Excel que se generará.

        try {
            excelExporter.exportToExcel(data, filePath);
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de errores
        }

        return "redirect:/download/excel.xlsx"; // Redirige a una URL que descargará el archivo.
    }

    public void crearDirectorio() {
        File directorio = new File("C:/credicuota");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
    }


}
