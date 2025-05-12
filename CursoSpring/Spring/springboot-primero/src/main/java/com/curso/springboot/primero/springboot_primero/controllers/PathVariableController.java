package com.curso.springboot.primero.springboot_primero.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.springboot.primero.springboot_primero.entities.User;
import com.curso.springboot.primero.springboot_primero.entities.DTO.ParamDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/var")
public class PathVariableController {

    @Value("${config.code}")
    private Integer code;
    @Value("${config.username}")
    private String username;
    /*@Value("${config.message}")
    private String message;*/
    @Value("${config.listOfValue}")
    private List<String> listOfValue;

    @Value("#{'${config.listOfValue}'.toUpperCase().split(',')}")
    private List<String> valueList;

    @Value("#{${config.valuesMap}}")
    private Map<String, Object> valuesMap;

    @Value("#{${config.valuesMap}.product}")
    private String product;

    @Value("#{${config.valuesMap}.price}")
    private String price;

    @Autowired
    private Environment env;

    @GetMapping("/vars/{message}")
    public ParamDTO vars(@PathVariable String message) {

        ParamDTO param = new ParamDTO();
        param.setMessage(message);
        return param;
    }
    
    @GetMapping("/mix/{id}/{desc}")
    public Map<String, Object> mixPathVar(@PathVariable String id, @PathVariable String desc) {
        Map<String, Object> json = new HashMap<>();

        json.put("id", id);
        json.put("desc", desc);

        return json;
    }

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        //TODO: process POST request
        user.setName(user.getName().toUpperCase());
        
        return user;
    }
    
    @GetMapping("/values")
    public Map<String, Object> values(@Value("${config.message}") String message) {
        Map<String, Object> json = new HashMap<>();
        json.put("code", code);
        json.put("username", username);
        json.put("message", message);
        json.put("message2", env.getProperty("config.message"));
        json.put("listOfValue", listOfValue);
        json.put("valueList", valueList);
        json.put("valueMap", valuesMap);
        json.put("product", product);
        json.put("price", price);

        return json;
    }
    

}
