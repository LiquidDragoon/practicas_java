package com.spring.error.springboot_error.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.spring.error.springboot_error.models.Error;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Error> exceptionByZero(Exception ex){
        Error error = new Error();

        error.setError("Error divición entre 0");
        error.setFecha(new Date());
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Error> notFoundException(NoResourceFoundException ex){
        Error error = new Error();

        error.setError("No se ha encontrado la API");
        error.setFecha(new Date());
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(NumberFormatException.class)
    public Map<String, String> numberFormatException(Exception ex){
        Map<String, String> error = new HashMap<>();
        error.put("date", new Date().toString());
        error.put("error", "Dato inválido o incorrecto, no tiene formato de número");
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value()+"");

        return error;
    }
}
