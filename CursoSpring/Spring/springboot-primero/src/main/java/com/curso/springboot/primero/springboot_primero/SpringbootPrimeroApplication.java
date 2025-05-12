package com.curso.springboot.primero.springboot_primero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:values.properties")
public class SpringbootPrimeroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootPrimeroApplication.class, args);
	}

}
