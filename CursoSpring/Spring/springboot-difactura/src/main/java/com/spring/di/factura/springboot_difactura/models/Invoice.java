package com.spring.di.factura.springboot_difactura.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class Invoice {

    @Autowired
    private Client client;
    @Value("${invoice.description}")
    private String description;
    @Autowired
    private List<Item> items;

    @PostConstruct
    public void init(){
        System.out.println("Creando componente de la factura");
        description = description.concat(" del cliente ").concat(client.getName() + " " + client.getLastName());
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Destruyendo componente bean");
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Double getTotal() {
        double total = 0.0;
        
        /*for (Item item : items){
            total += item.getImporte();
        }*/
        total = items.stream().map(item -> item.getImporte()).reduce(0.0, (sum, item) -> sum + item);

        return total;
    }
}
