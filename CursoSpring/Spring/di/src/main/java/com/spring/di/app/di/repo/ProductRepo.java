package com.spring.di.app.di.repo;

import java.util.List;

import com.spring.di.app.di.model.Product;


public interface ProductRepo {
    List<Product> findAll();
    Product findById(Long id);
}
