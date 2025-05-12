package com.spring.di.app.di.repo;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
//import org.springframework.web.context.annotation.SessionScope;

import com.spring.di.app.di.model.Product;

//@SessionScope
@Primary
@Repository("productList")
public class ProductRepoImpl implements ProductRepo{

    private List<Product> data;

    public ProductRepoImpl() {
        this.data = Arrays.asList(
            new Product(1L, "Control Genérico", 245.00),
            new Product(2L, "Fuente de poder", 700.00),
            new Product(3L, "Audífonos bluetooth", 300.00),
            new Product(4L, "Dock de carga", 500.00)
        );
    }

    public List<Product> findAll(){
        return data;
    }

    public Product findById(Long id){
        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}
