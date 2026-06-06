package com.connectshopp.pedidos.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DetallePedidoRequest {
    @NotNull
    @Positive
    private Long productoId;
    @NotNull
    @Min(1)
    private Integer cantidad;
    @NotNull
    @DecimalMin("0.01")
    private BigDecimal precioUnit;

    public DetallePedidoRequest() {
    }

    public DetallePedidoRequest(@NotNull @Positive Long productoId, @NotNull @Min(1) Integer cantidad, @NotNull @DecimalMin("0.01") BigDecimal precioUnit) {
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnit = precioUnit;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(BigDecimal precioUnit) {
        this.precioUnit = precioUnit;
    }
}
