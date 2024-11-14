package com.example.ordersservice.infraestructure.mappers;

import com.example.ordersservice.domain.dto.ProductDTO;
import com.example.ordersservice.infraestructure.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }

        ProductDTO dto = new ProductDTO();
        dto.setProductoId(product.getProductId().intValue());
        dto.setNombre(product.getName() != null ? product.getName() : "Sin Nombre");
        dto.setDescripcion(product.getDescription() != null ? product.getDescription() : "Sin Descripción");
        dto.setPrecio(product.getPrice());
        dto.setStock(product.getStock());

        return dto;
    }

    public Product toEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }

        Product product = new Product();
        product.setProductId((long) dto.getProductoId());
        product.setName(dto.getNombre());
        product.setDescription(dto.getDescripcion());
        product.setPrice(dto.getPrecio());
        product.setStock(dto.getStock());

        return product;
    }
}
