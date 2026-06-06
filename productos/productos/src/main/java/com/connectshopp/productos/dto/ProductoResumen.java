package com.connectshopp.productos.dto;

import java.math.BigDecimal;

public record ProductoResumen(
    Long id,
    String nombre,
    BigDecimal precio,
    Integer stock,
    String imagenPrincipal
) {
}
