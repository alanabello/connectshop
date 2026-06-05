package com.connectshopp.usuarios.dto;

import jakarta.validation.constraints.NotBlank;

public record DireccionRequest(
    @NotBlank String calle,
    @NotBlank String ciudad,
    @NotBlank String region
) {
}
