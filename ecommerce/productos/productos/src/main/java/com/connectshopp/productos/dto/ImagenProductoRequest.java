package com.connectshopp.productos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ImagenProductoRequest(
    @NotBlank String url,
    @Min(0) Integer orden,
    Boolean principal
) {
}
