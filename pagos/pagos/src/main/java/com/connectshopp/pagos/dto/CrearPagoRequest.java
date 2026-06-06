package com.connectshopp.pagos.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CrearPagoRequest {
    @NotNull
    @Positive
    private Long pedidoId;
    @NotNull
    @DecimalMin("0.01")
    private BigDecimal monto;

    public CrearPagoRequest() {
    }

    public CrearPagoRequest(@NotNull @Positive Long pedidoId, @NotNull @DecimalMin("0.01") BigDecimal monto) {
        this.pedidoId = pedidoId;
        this.monto = monto;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
