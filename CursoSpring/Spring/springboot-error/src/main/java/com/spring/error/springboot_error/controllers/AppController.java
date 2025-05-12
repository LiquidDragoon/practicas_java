package com.spring.error.springboot_error.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class AppController {

    @GetMapping("/app")
    public String Index(){
        //int x = 100/0;

        int value = Integer.parseInt("x");

        System.out.println(value);
        return "200";
    }
    
}
