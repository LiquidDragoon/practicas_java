package com.spring.di.factura.springboot_difactura;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import com.spring.di.factura.springboot_difactura.models.Item;
import com.spring.di.factura.springboot_difactura.models.Product;

@Configuration
@PropertySource("classpath:data.properties")
public class AppConfig {

    @Bean
    List<Item> itemsInvoice() {

        Product p1 = new Product("Control inalámbrico", 800.00);
        Product p2 = new Product("Estuche rígido", 400.00);

        return Arrays.asList(new Item(p1, 3), new Item(p2, 1));
    }

    @Bean
    @Primary
    List<Item> itemsInvoiceOfficina(){

        Product p1 = new Product("LapTop Asus TUF A17", 18000.00);
        Product p2 = new Product("Escritorio plegable", 1700.00);
        Product p3 = new Product("Mouse inalámbrico", 300.00);
        Product p4 = new Product("Silla giratoria oficina", 2300.00);

        return Arrays.asList(new Item(p1, 1), new Item(p2, 1), new Item(p3, 1), new Item(p4, 1));
    }

}
