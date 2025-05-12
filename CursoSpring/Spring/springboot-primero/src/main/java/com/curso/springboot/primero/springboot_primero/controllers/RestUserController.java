package com.curso.springboot.primero.springboot_primero.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.springboot.primero.springboot_primero.entities.User;
import com.curso.springboot.primero.springboot_primero.entities.DTO.UserDTO;


@RestController
@RequestMapping("/api")
public class RestUserController {

    @GetMapping("/primeRest-map")
    public Map<String, Object> primeMap() {
        Map<String, Object> body = new HashMap<>();

        User user = new User("Silvano", "Ortiz");

        body.put("title", "Nuevo mensaje");
        body.put("user", user);

        return body;
    }
    
    @GetMapping("/primeRest")
    public UserDTO prime() {
        UserDTO userDTO= new UserDTO();

        User user = new User("Silvano", "Ortiz");
        userDTO.setUser(user);
        userDTO.setTitle("Nuevo mensaje");

        return userDTO;
    }

    @GetMapping("/primeRest-List")
    public List<User> list(){
        User user = new User("Silvano", "Ortiz");
        User user2 = new User("Alexis", "López");
        User user3 = new User("Nirvana", "Ortiz");

        List<User> users = Arrays.asList(user, user2,  user3);

        /*List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);
        users.add(user3);*/

        return users;
    }
}
