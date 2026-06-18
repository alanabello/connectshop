package com.connectshopp.inventario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Datos para registrar una entrada o salida de inventario")
public class MovimientoInventarioRequest {
    @NotBlank
    @Schema(description = "Tipo de movimiento de inventario", example = "ENTRADA")
    private String tipo;
    @NotNull
    @Min(0)
    @Schema(description = "Cantidad de unidades que se agregan o descuentan", example = "3")
    private Integer cantidad;

    public MovimientoInventarioRequest() {
    }

    public MovimientoInventarioRequest(@NotBlank String tipo, @NotNull @Min(0) Integer cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
