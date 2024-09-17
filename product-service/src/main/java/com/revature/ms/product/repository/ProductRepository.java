package com.revature.ms.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.ms.product.model.Product;
public interface ProductRepository extends JpaRepository<Product, Long>{

}
