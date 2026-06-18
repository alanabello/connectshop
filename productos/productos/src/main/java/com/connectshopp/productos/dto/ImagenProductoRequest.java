package com.connectshopp.productos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Datos para agregar una imagen a un producto")
public class ImagenProductoRequest {
    @NotBlank
    @Schema(description = "URL publica de la imagen", example = "https://cdn.connectshop.local/productos/notebook.jpg")
    private String url;
    @Min(0)
    @Schema(description = "Orden de visualizacion de la imagen", example = "1")
    private Integer orden;
    @Schema(description = "Indica si es la imagen principal del producto", example = "true")
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
