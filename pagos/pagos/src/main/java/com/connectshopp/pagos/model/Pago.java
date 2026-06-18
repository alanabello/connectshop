package com.connectshopp.pagos.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pagos")
@Schema(description = "Pago asociado a un pedido")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "ID del pago", example = "1")
    private Long id;

    @Column(name = "monto", nullable = false, precision = 12, scale = 2)
    @NotNull
    @DecimalMin("0.01")
    @Schema(description = "Monto del pago", example = "45990.00")
    private BigDecimal monto;

    @Column(name = "pedido_id", nullable = false)
    @NotNull
    @Positive
    @Schema(description = "ID del pedido pagado", example = "15")
    private Long pedidoId;

    @Column(name = "estado", nullable = false, length = 30)
    @NotBlank
    @Schema(description = "Estado actual del pago", example = "PENDIENTE")
    private String estado = "PENDIENTE";

    @Column(name = "fecha_pago", nullable = false)
    @Schema(description = "Fecha y hora de creacion del pago", example = "2026-06-17T10:30:00")
    private LocalDateTime fechaPago = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "metodo_pago_id", nullable = false)
    @Schema(description = "Metodo utilizado para el pago")
    private MetodoPago metodoPago;

    @OneToMany(mappedBy = "pago", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Transacciones registradas para el pago")
    private List<TransaccionPago> transacciones = new ArrayList<>();

    public Pago() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public List<TransaccionPago> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<TransaccionPago> transacciones) {
        this.transacciones = transacciones;
    }

    public void agregarTransaccion(TransaccionPago transaccion) {
        transaccion.setPago(this);
        transacciones.add(transaccion);
    }
}
