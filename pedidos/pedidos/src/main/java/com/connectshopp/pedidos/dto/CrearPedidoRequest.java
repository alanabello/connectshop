package com.connectshopp.pedidos.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CrearPedidoRequest {
    @NotNull
    @Positive
    private Long usuarioId;
    @NotNull
    @Positive
    private Long metodoPagoId;
    @NotEmpty
    private List<@Valid DetallePedidoRequest> detalles;

    public CrearPedidoRequest() {
    }

    public CrearPedidoRequest(@NotNull @Positive Long usuarioId, @NotNull @Positive Long metodoPagoId, @NotEmpty List<@Valid DetallePedidoRequest> detalles) {
        this.usuarioId = usuarioId;
        this.metodoPagoId = metodoPagoId;
        this.detalles = detalles;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getMetodoPagoId() {
        return metodoPagoId;
    }

    public void setMetodoPagoId(Long metodoPagoId) {
        this.metodoPagoId = metodoPagoId;
    }

    public List<DetallePedidoRequest> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedidoRequest> detalles) {
        this.detalles = detalles;
    }
}

