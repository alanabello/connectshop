package com.connectshopp.productos.dto;

import java.math.BigDecimal;

public class ProductoRequest {
    private String nombre;
    private BigDecimal precio;
    private Integer stock;
    private String sku;
    private Long categoriaId;
    private Long marcaId;

    public ProductoRequest() {
    }

    public ProductoRequest(String nombre, BigDecimal precio, Integer stock, String sku, Long categoriaId, Long marcaId) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.sku = sku;
        this.categoriaId = categoriaId;
        this.marcaId = marcaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Long marcaId) {
        this.marcaId = marcaId;
    }
}
