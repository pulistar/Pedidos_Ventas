package com.example.ordersservice.domain.dto;

import java.util.Date;
import java.util.List;

public class OrdersDTO {
    private int pedidoId;
    private int clienteId;
    private Date fecha;
    private String estado;
    private double total;
    private List<OrdersDetailDTO> detalles;

    // Getters y Setters
    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<OrdersDetailDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<OrdersDetailDTO> detalles) {
        this.detalles = detalles;
    }
}
