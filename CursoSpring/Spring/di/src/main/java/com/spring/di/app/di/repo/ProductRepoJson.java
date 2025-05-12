package com.spring.di.app.di.repo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.di.app.di.model.Product;

public class ProductRepoJson implements ProductRepo{

    private List<Product> list;

    public ProductRepoJson() {
        Resource resource = new ClassPathResource("json/product.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            list = Arrays.asList(objectMapper.readValue(resource.getFile(), Product[].class));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        return list;
    }

    @Override
    public Product findById(Long id) {
        //La función en flecha ejecuta este código sin la necesidad de crear un método nuevo
        return list.stream().filter(p -> 
        //Cuando sólo se ejecuta una línea no es necesario agregar las llaves "{}" y el resultado será un return
            p.getId().equals(id)
        ).findFirst().orElseThrow();
    }

}
