package com.connectshopp.pagos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record CrearPagoRequest(
    @NotNull @Positive Long pedidoId,
    @NotNull @DecimalMin("0.01") BigDecimal monto
) {
}
