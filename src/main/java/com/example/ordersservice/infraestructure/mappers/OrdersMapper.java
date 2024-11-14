package com.example.ordersservice.infraestructure.mappers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ordersservice.domain.dto.OrdersDTO;
import com.example.ordersservice.domain.repository.ProductRepository;
import com.example.ordersservice.infraestructure.entity.Orders;
import com.example.ordersservice.infraestructure.entity.Product;

@Component
public class OrdersMapper {

    private final OrdersDetailMapper orderDetailMapper;

    @Autowired
    private ProductRepository productRepository;

    // Constructor para inyectar el OrderDetailMapper
    public OrdersMapper(OrdersDetailMapper orderDetailMapper) {
        this.orderDetailMapper = orderDetailMapper;
    }

    // Convertir de entidad a DTO
    public OrdersDTO toDTO(Orders order) {
        if (order == null) {
            return null;
        }

        OrdersDTO dto = new OrdersDTO();
        dto.setPedidoId(order.getOrderId().intValue());
        dto.setClienteId(order.getCustomerId().intValue());
        dto.setFecha(order.getFecha());
        dto.setEstado(order.getStatus());
        dto.setTotal(order.getTotalAmount());

        // Mapeo de detalles de la orden usando OrderDetailMapper
        dto.setDetalles(order.getOrderDetails().stream()
            .map(orderDetailMapper::toDTO)
            .collect(Collectors.toList()));

        return dto;
    }

    // Convertir de DTO a entidad
    public Orders toEntity(OrdersDTO dto) {
        if (dto == null) {
            return null;
        }

        Orders order = new Orders();
        order.setOrderId((long) dto.getPedidoId());
        order.setCustomerId((long) dto.getClienteId());
        order.setFecha(dto.getFecha());
        order.setStatus(dto.getEstado());
        order.setTotalAmount(dto.getTotal());

        // Convertir cada detalle de la orden de DTO a entidad
        order.setOrderDetails(dto.getDetalles().stream()
            .map(detailDto -> {
                Product product = productRepository.findById((long) detailDto.getProductoId()).orElse(null);
                return orderDetailMapper.toEntity(detailDto, order, product);
            })
            .collect(Collectors.toList()));

        return order;
    }
}
