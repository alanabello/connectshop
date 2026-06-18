package com.connectshopp.usuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Datos para agregar una direccion a un usuario")
public class DireccionRequest {
    @NotBlank
    @Schema(description = "Calle y numero de la direccion", example = "Av. Providencia 1234")
    private String calle;
    @NotBlank
    @Schema(description = "Ciudad de la direccion", example = "Santiago")
    private String ciudad;
    @NotBlank
    @Schema(description = "Region de la direccion", example = "Metropolitana")
    private String region;

    public DireccionRequest() {
    }

    public DireccionRequest(@NotBlank String calle, @NotBlank String ciudad, @NotBlank String region) {
        this.calle = calle;
        this.ciudad = ciudad;
        this.region = region;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
