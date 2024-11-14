package com.example.ordersservice.domain.service;

import com.example.ordersservice.domain.repository.OrdersRepository;
import com.example.ordersservice.domain.repository.ProductRepository;
import com.example.ordersservice.exception.OrdersException;
import com.example.ordersservice.infraestructure.entity.Orders;
import com.example.ordersservice.infraestructure.entity.OrdersDetail;
import com.example.ordersservice.infraestructure.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockService stockService;

    public Orders crearPedido(Orders order) throws OrdersException {
        if (order.getCustomerId() == null) {
            throw new OrdersException("El ID del cliente es obligatorio");
        }

        // Verifica si hay detalles de la orden y asigna el objeto Orders a cada detalle
        if (order.getOrderDetails() != null && !order.getOrderDetails().isEmpty()) {
            for (OrdersDetail detail : order.getOrderDetails()) {
                Product product = productRepository.findById(detail.getProduct().getProductId()).orElse(null);

                if (product == null || !stockService.validarStock(product.getProductId(), detail.getQuantity())) {
                    throw new OrdersException("Stock insuficiente o producto no encontrado para el ID: " + detail.getProduct().getProductId());
                }

                // Asigna el producto completo al detalle
                detail.setProduct(product);
                detail.setPrice(product.getPrice());

                // Asigna la referencia del pedido al detalle
                detail.setOrder(order);
            }

            double totalAmount = order.getOrderDetails().stream()
                    .mapToDouble(d -> d.getPrice() * d.getQuantity())
                    .sum();
            order.setTotalAmount(totalAmount);
        } else {
            order.setTotalAmount(0);  // Si no hay detalles, el total es 0
        }

        order.setStatus("pendiente");
        return orderRepository.save(order);
    }

    public Orders obtenerPedido(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public List<Orders> listarPedidos(Long customerId, String status) {
        if (customerId != null) {
            return orderRepository.findByCustomerId(customerId);
        } else if (status != null) {
            return orderRepository.findByStatus(status);
        }
        return (List<Orders>) orderRepository.findAll();
    }

    public Orders actualizarEstadoPedido(Long orderId, String status) {
        Orders order = obtenerPedido(orderId);
        if (order != null) {
            order.setStatus(status);
            return orderRepository.save(order);
        }
        return null;
    }

    public void eliminarPedido(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
