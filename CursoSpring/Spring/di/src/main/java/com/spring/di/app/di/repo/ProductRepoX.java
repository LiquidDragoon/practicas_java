package com.spring.di.app.di.repo;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.di.app.di.model.Product;

@Repository("productX")
public class ProductRepoX implements ProductRepo{

    @Override
    public List<Product> findAll() {
        return Collections.singletonList(new Product(12L, "Memoria RAM 32 GB", 1200.00));
    }

    @Override
    public Product findById(Long id) {
        return new Product(12L, "Memoria RAM 32 GB", 1200.00);
    }
    
}
