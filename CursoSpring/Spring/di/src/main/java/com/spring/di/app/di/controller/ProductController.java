package com.spring.di.app.di.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.di.app.di.model.Product;
import com.spring.di.app.di.service.ProductServ;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductServ pServ;

    @GetMapping()
    public List<Product> list(){
        return pServ.findAll();
    }
    
    @GetMapping("/{id}")
    public Product show(@PathVariable Long id) {
        return pServ.findById(id);
    }
    
}
