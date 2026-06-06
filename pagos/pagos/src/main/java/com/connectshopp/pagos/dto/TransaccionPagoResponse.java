package com.connectshopp.pagos.dto;

import java.time.LocalDateTime;

public class TransaccionPagoResponse {
    private Long id;
    private String estado;
    private String mensaje;
    private LocalDateTime fechaTransaccion;

    public TransaccionPagoResponse() {
    }

    public TransaccionPagoResponse(Long id, String estado, String mensaje, LocalDateTime fechaTransaccion) {
        this.id = id;
        this.estado = estado;
        this.mensaje = mensaje;
        this.fechaTransaccion = fechaTransaccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }
}
