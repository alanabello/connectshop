package com.connectshopp.pagos.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Datos necesarios para procesar un pago")
public class CrearPagoRequest {
    @NotNull
    @Positive
    @Schema(description = "ID del pedido que sera pagado", example = "15")
    private Long pedidoId;
    @NotNull
    @DecimalMin("0.01")
    @Schema(description = "Monto total del pago", example = "45990.00")
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
