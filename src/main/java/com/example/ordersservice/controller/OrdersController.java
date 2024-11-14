package com.example.ordersservice.controller;

import com.example.ordersservice.domain.service.OrdersService;
import com.example.ordersservice.exception.OrdersException;
import com.example.ordersservice.infraestructure.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService orderService;

    // POST /orders - Crear un nuevo pedido
    @PostMapping
    public ResponseEntity<?> crearPedido(@RequestBody Orders order) {
        try {
            Orders nuevoPedido = orderService.crearPedido(order);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
        } catch (OrdersException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno en el servidor: " + e.getMessage());
        }
    }

    // GET /orders/{id} - Obtener detalles de un pedido
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPedido(@PathVariable Long id) {
        Orders order = orderService.obtenerPedido(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no encontrado");
        }
    }

    // GET /orders - Listar todos los pedidos (opcionalmente por customerId y status)
    @GetMapping
    public ResponseEntity<List<Orders>> listarPedidos(@RequestParam(required = false) Long customerId,
                                                      @RequestParam(required = false) String status) {
        List<Orders> pedidos = orderService.listarPedidos(customerId, status);
        return ResponseEntity.ok(pedidos);
    }

    // PUT /orders/{id}/status - Actualizar el estado de un pedido
    @PutMapping("/{id}/status")
    public ResponseEntity<?> actualizarEstadoPedido(@PathVariable Long id, @RequestBody String status) {
        Orders order = orderService.actualizarEstadoPedido(id, status);
        if (order != null) {
            return ResponseEntity.ok("Estado actualizado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no encontrado");
        }
    }

    // DELETE /orders/{id} - Eliminar un pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPedido(@PathVariable Long id) {
        try {
            orderService.eliminarPedido(id);
            return ResponseEntity.ok("Pedido eliminado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el pedido");
        }
    }
}
