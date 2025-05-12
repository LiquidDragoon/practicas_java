package com.curso.springboot.primero.springboot_primero.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.springboot.primero.springboot_primero.entities.DTO.ParamDTO;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/params")
public class RequestParamController {

    @GetMapping("/foo")
    public ParamDTO foo(@RequestParam(required = false, defaultValue = "Vacío", name = "mensaje") String message) {

        ParamDTO param = new ParamDTO();
        param.setMessage(message);

        return param;
    }
    
    @GetMapping("/bar")
    public ParamDTO bar(@RequestParam String text, @RequestParam Integer code) {
        
        ParamDTO param = new ParamDTO();
        param.setMessage(text);
        param.setCode(code);
        return param;
    }
    
    @GetMapping("/request")
    public ParamDTO request(HttpServletRequest request) {
        Integer code = 0;
        ParamDTO param = new ParamDTO();
        try {
            code = Integer.parseInt(request.getParameter("code"));
        } catch (Exception e) {
            // TODO: handle exception
        }

        param.setCode(code);
        param.setMessage(request.getParameter("message"));

        return param;
    }
    
}
