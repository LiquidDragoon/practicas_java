package com.spring.di.factura.springboot_difactura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.di.factura.springboot_difactura.models.Invoice;

@RestController
@RequestMapping("/invoices")
public class invoiceController {

    @Autowired
    private Invoice invoice;

    @GetMapping("/view")
    public Invoice view(){
        //En caso de querer evitar que sea afectado por ser singleton
        /*Invoice in = new Invoice();
        in = invoice;*/
        return invoice;
    }

}
