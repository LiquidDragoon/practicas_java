package com.conecta.sqlserver.conecta_sqlserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LoginController {

    @PostMapping("demo")
    public String welcome(@RequestBody String entity) {
        return "Bienvenido al endpoint protegido";
    }
    

}
