package com.conecta.sqlserver.conecta_sqlserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conecta.sqlserver.conecta_sqlserver.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}
