package com.connectshopp.inventario.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Inventario asociado a un producto")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "ID del inventario", example = "1")
    private Long id;

    @Column(name = "producto_id", nullable = false)
    @NotNull
    @Positive
    @Schema(description = "ID del producto asociado", example = "10")
    private Long productoId;

    @Column(name = "stock_actual", nullable = false)
    @NotNull
    @Min(0)
    @Schema(description = "Cantidad actual disponible", example = "25")
    private Integer stockActual = 0;

    @Column(name = "stock_minimo", nullable = false)
    @NotNull
    @Min(0)
    @Schema(description = "Cantidad minima para alerta", example = "5")
    private Integer stockMinimo = 0;

    @OneToMany(mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Movimientos registrados para el inventario")
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
