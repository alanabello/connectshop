package com.connectshopp.inventario.dto;

public class InventarioResponse {
    private Long id;
    private Long productoId;
    private Integer stockActual;
    private Integer stockMinimo;
    private Boolean bajoStock;

    public InventarioResponse() {
    }

    public InventarioResponse(Long id, Long productoId, Integer stockActual, Integer stockMinimo, Boolean bajoStock) {
        this.id = id;
        this.productoId = productoId;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.bajoStock = bajoStock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public Boolean getBajoStock() {
        return bajoStock;
    }

    public void setBajoStock(Boolean bajoStock) {
        this.bajoStock = bajoStock;
    }
}
