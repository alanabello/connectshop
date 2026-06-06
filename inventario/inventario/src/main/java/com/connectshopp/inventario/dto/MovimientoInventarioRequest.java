package com.connectshopp.inventario.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MovimientoInventarioRequest {
    @NotBlank
    private String tipo;
    @NotNull
    @Min(0)
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
