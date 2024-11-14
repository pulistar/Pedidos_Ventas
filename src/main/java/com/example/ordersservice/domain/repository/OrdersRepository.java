package com.example.ordersservice.domain.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.ordersservice.infraestructure.entity.Orders;

import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long> {
    List<Orders> findByCustomerId(Long customerId);
    List<Orders> findByStatus(String status);
}