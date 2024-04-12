package com.bolsadeideas.springboot.app.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class DownloadController {

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<byte[]> downloadExcel(@PathVariable String fileName) throws IOException {
        String filePath = "C:/credicuota/" + fileName;

        Path path = Paths.get(filePath);
        byte[] content = Files.readAllBytes(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData(fileName, fileName);
        headers.setContentLength(content.length);

        return new ResponseEntity<>(content, headers, org.springframework.http.HttpStatus.OK);
    }
}
