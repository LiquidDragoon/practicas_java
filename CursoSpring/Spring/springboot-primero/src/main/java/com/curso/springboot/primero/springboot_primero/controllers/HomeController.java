package com.curso.springboot.primero.springboot_primero.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    
    @GetMapping({"", "/", "/home"})
    public String home() {
        return "redirect:/primeList";
        //return "forward:/primeList";
    }
    
}
