package com.conecta.sqlserver.conecta_sqlserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conecta.sqlserver.conecta_sqlserver.entities.Product;
import com.conecta.sqlserver.conecta_sqlserver.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("api/findAll")
    public List<Product> showAllProducts(){
        return productService.getProducts();
    }

}
