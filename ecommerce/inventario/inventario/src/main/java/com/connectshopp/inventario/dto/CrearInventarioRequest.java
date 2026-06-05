package com.connectshopp.inventario.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CrearInventarioRequest(
    @NotNull @Positive Long productoId,
    @NotNull @Min(0) Integer stockActual,
    @NotNull @Min(0) Integer stockMinimo
) {
}
