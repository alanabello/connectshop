package com.connectshopp.productos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ImagenProductoRequest {
    @NotBlank
    private String url;
    @Min(0)
    private Integer orden;
    private Boolean principal;

    public ImagenProductoRequest() {
    }

    public ImagenProductoRequest(@NotBlank String url, @Min(0) Integer orden, Boolean principal) {
        this.url = url;
        this.orden = orden;
        this.principal = principal;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }
}
