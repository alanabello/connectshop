package com.connectshopp.pedidos.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Datos necesarios para crear un pedido")
public class CrearPedidoRequest {
    @NotNull
    @Positive
    @Schema(description = "ID del usuario que realiza el pedido", example = "1")
    private Long usuarioId;
    @NotNull
    @Positive
    @Schema(description = "ID del metodo de pago seleccionado", example = "1")
    private Long metodoPagoId;
    @NotEmpty
    @Schema(description = "Detalle de productos solicitados")
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

