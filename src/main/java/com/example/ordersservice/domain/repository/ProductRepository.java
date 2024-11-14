package com.example.ordersservice.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.ordersservice.infraestructure.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
