package com.bolsadeideas.springboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@SpringBootApplication
@ServletComponentScan
public class SpringBootDataJpaApplication extends SpringBootServletInitializer {

	// Esto es para iniciar el server
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(SpringBootDataJpaApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}




}
