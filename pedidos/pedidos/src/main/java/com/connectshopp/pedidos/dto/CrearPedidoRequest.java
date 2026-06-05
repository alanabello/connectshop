package com.connectshopp.pedidos.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

public record CrearPedidoRequest(
    @NotNull @Positive Long usuarioId,
    @NotNull @Positive Long metodoPagoId,
    @NotEmpty List<@Valid DetallePedidoRequest> detalles
) {
}
