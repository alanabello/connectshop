package com.connectshopp.inventario.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovimientoInventarioRequest(
    @NotBlank String tipo,
    @NotNull @Min(0) Integer cantidad
) {
}
