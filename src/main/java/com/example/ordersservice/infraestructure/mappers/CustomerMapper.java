package com.example.ordersservice.infraestructure.mappers;

import com.example.ordersservice.domain.dto.CustomerDTO;
import com.example.ordersservice.infraestructure.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDTO toDTO(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(customer.getCustomerId().intValue());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setTelefono(customer.getTelefono());
        dto.setDireccion(customer.getDireccion());
        return dto;
    }

    public Customer toEntity(CustomerDTO dto) {
        if (dto == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setCustomerId((long) dto.getCustomerId());
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setTelefono(dto.getTelefono());
        customer.setDireccion(dto.getDireccion());
        return customer;
    }
}