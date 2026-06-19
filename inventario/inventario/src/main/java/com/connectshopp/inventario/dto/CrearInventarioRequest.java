package com.connectshopp.inventario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Datos necesarios para crear un inventario de producto")
public class CrearInventarioRequest {
    @NotNull
    @Positive
    @Schema(
        description = "ID del producto asociado al inventario", 
        example = "10")
    private Long productoId;
    @NotNull
    @Min(0)
    @Schema(
        description = "Cantidad actual disponible en stock", 
        example = "25")
    private Integer stockActual;
    @NotNull
    @Min(0)
    @Schema(
        description = "Cantidad minima antes de generar alerta de stock", 
        example = "5")
    private Integer stockMinimo;

    public CrearInventarioRequest() {
    }

    public CrearInventarioRequest(@NotNull @Positive Long productoId, @NotNull @Min(0) Integer stockActual, @NotNull @Min(0) Integer stockMinimo) {
        this.productoId = productoId;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
}
