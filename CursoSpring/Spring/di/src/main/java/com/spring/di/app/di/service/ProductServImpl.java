    package com.spring.di.app.di.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.spring.di.app.di.model.Product;
import com.spring.di.app.di.repo.ProductRepo;

@Service
public class ProductServImpl implements ProductServ{

     //Opción de dejar sin la anotación @Autowired creando en su lugar un constructor
    public ProductServImpl(ProductRepo pRepo) {
        this.pRepo = pRepo;
    }

    
    @Autowired
    private Environment environment;
    private ProductRepo pRepo;

    
    
    public List<Product> findAll(){
        //Ejemplo de función en flecha
        return pRepo.findAll().stream().map(p ->{
            //La función en flecha ejecuta este código sin la necesidad de crear un método nuevo
            Double newPrice = p.getPrice()*environment.getProperty("config.price.tags", Double.class);

            Product newProd = (Product) p.clone();
            newProd.setPrice(newPrice);
            return newProd;

            /* p.setPrice(newPrice);
            return p; */
        }).collect(Collectors.toList());
    }

    public Product findById(Long id){
        return pRepo.findById(id);
    }
}
