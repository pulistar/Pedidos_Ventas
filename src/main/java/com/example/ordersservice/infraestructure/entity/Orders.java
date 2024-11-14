package com.example.ordersservice.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long customerId;
    private double totalAmount;
    private String status;
    private Date fecha;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrdersDetail> orderDetails;

    // Getters y Setters
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<OrdersDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrdersDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
