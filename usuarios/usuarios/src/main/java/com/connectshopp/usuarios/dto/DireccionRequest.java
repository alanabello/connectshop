package com.connectshopp.usuarios.dto;

import jakarta.validation.constraints.NotBlank;

public class DireccionRequest {
    @NotBlank
    private String calle;
    @NotBlank
    private String ciudad;
    @NotBlank
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
