package com.curso.springboot.primero.springboot_primero.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.curso.springboot.primero.springboot_primero.entities.User;


@Controller
public class UserController {

    @GetMapping("/prime")
    public String prime(Model mdl) {
        User user = new User("Silvano", "Ortiz");
        user.setEmail("silrocha@hotmail.com");
        mdl.addAttribute("title", "Nuevo mensaje");
        mdl.addAttribute("user", user);

        return "prime";
    }
    
    @GetMapping("/primeList")
    public String getMethodName(ModelMap mdlMp) {

       /*User user = new User("Silvano", "Ortiz", "silorcha@gmail.com");
        User user2 = new User("Alexis", "López");
        User user3 = new User("Nirvana", "Ortiz", "nirvana@gmail.com");

        List<User> users = Arrays.asList(user, user2,  user3);

        mdlMp.addAttribute("users", users);*/
        mdlMp.addAttribute("title", "Listado de usuarios");
        return "list";
    }
    
    @ModelAttribute("users")
    public List<User> userModel() {

        User user = new User("Silvano", "Ortiz", "silorcha@gmail.com");
        User user2 = new User("Alexis", "López");
        User user3 = new User("Nirvana", "Ortiz", "nirvana@gmail.com");

        List<User> users = Arrays.asList(user, user2,  user3);

        return users;
    }

}
