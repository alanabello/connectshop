package com.connectshopp.inventario.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
    name = "inventarios",
    uniqueConstraints = @UniqueConstraint(name = "uk_inventarios_producto_id", columnNames = "producto_id")
)
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "producto_id", nullable = false)
    @NotNull
    @Positive
    private Long productoId;

    @Column(name = "stock_actual", nullable = false)
    @NotNull
    @Min(0)
    private Integer stockActual = 0;

    @Column(name = "stock_minimo", nullable = false)
    @NotNull
    @Min(0)
    private Integer stockMinimo = 0;

    @OneToMany(mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovimientoInventario> movimientos = new ArrayList<>();

    public Inventario() {
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

    public List<MovimientoInventario> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoInventario> movimientos) {
        this.movimientos = movimientos;
    }

    public void agregarMovimiento(MovimientoInventario movimiento) {
        movimiento.setInventario(this);
        movimientos.add(movimiento);
    }
}
