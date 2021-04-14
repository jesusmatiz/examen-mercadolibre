package com.mercadolibre.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@ComponentScan("com.mercadolibre.app")
@EnableAutoConfiguration
@CrossOrigin("*")
@SpringBootApplication
public class ExamenMercadoLibreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamenMercadoLibreApplication.class, args);
	}

}
