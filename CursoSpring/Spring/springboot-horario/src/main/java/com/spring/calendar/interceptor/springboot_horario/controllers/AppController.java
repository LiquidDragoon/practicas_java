package com.spring.calendar.interceptor.springboot_horario.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class AppController {

    @GetMapping("/foo")
    public ResponseEntity<?> foo() {
        Map<String, Object> data = new HashMap<>();
        data.put("title", "Bienvenidos al sistema de atención");
        data.put("date", new Date());

        return ResponseEntity.ok(data);
    }

}
