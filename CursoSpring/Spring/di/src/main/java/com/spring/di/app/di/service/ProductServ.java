package com.spring.di.app.di.service;

import java.util.List;

import com.spring.di.app.di.model.Product;

public interface ProductServ {
    List<Product> findAll();
    Product findById(Long id);
}
