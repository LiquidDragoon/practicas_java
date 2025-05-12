package com.conecta.sqlserver.conecta_sqlserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conecta.sqlserver.conecta_sqlserver.entities.Product;
import com.conecta.sqlserver.conecta_sqlserver.repository.ProductRepository;

@Service
public class ProductServiceJPA implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

}
