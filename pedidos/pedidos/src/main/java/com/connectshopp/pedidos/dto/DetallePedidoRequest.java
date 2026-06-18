package com.connectshopp.pedidos.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Detalle de un producto dentro del pedido")
public class DetallePedidoRequest {
    @NotNull
    @Positive
    @Schema(description = "ID del producto solicitado", example = "10")
    private Long productoId;
    @NotNull
    @Min(1)
    @Schema(description = "Cantidad solicitada del producto", example = "2")
    private Integer cantidad;
    @NotNull
    @DecimalMin("0.01")
    @Schema(description = "Precio unitario del producto al momento del pedido", example = "12990.00")
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
