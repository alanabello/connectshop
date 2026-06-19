package com.connectshopp.inventario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

/**
 * DTO estandar para responder errores de forma clara.
 *
 * Este modelo permite que Swagger muestre una estructura de error simple
 * en vez de respuestas tecnicas largas generadas por Spring.
 */
@Schema(description = "Respuesta estandar para errores de la API")
public class ErrorResponseDto {

    @Schema(description = "Fecha y hora en que ocurrio el error", example = "2026-06-18T15:30:00")
    private LocalDateTime timestamp;

    @Schema(description = "Codigo HTTP del error", example = "404")
    private int status;

    @Schema(description = "Nombre breve del error", example = "Not Found")
    private String error;

    @Schema(description = "Mensaje claro para entender el problema", example = "Inventario no encontrado")
    private String message;

    @Schema(description = "Ruta donde ocurrio el error", example = "/inventarios/99")
    private String path;

    public ErrorResponseDto() {
    }

    public ErrorResponseDto(LocalDateTime timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
