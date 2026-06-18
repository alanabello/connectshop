package com.connectshopp.pagos.controller;

import com.connectshopp.pagos.dto.CrearPagoRequest;
import com.connectshopp.pagos.model.Pago;
import com.connectshopp.pagos.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagos")
@Tag(name = "Pagos", description = "Operaciones para procesar pagos y actualizar estados")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @PostMapping
    @Operation(summary = "Procesar pago", description = "Crea un pago para un pedido usando un metodo de pago.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Pago procesado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Pago.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Metodo de pago no encontrado", content = @Content)
    })
    public ResponseEntity<Pago> procesar(
        @Parameter(description = "ID del metodo de pago", example = "1") @RequestParam Long metodoId,
        @Valid @RequestBody CrearPagoRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pagoService.procesar(metodoId, request));
    }

    @GetMapping
    @Operation(summary = "Buscar pagos", description = "Lista pagos filtrando opcionalmente por pedido o estado.")
    @ApiResponse(responseCode = "200", description = "Lista de pagos",
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
            array = @ArraySchema(schema = @Schema(implementation = Pago.class))))
    public ResponseEntity<List<Pago>> buscar(
        @Parameter(description = "ID del pedido", example = "10") @RequestParam(required = false) Long pedidoId,
        @Parameter(description = "Estado del pago", example = "APROBADO") @RequestParam(required = false) String estado
    ) {
        return ResponseEntity.ok(pagoService.buscar(pedidoId, estado));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pago por ID", description = "Obtiene un pago especifico por su identificador.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pago encontrado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Pago.class))),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado", content = @Content)
    })
    public ResponseEntity<Pago> buscarPorId(@Parameter(description = "ID del pago", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(pagoService.buscarPorId(id));
    }

    @PatchMapping("/{id}/estado")
    @Operation(summary = "Actualizar estado de pago", description = "Actualiza el estado de un pago existente.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Estado actualizado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Pago.class))),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado", content = @Content)
    })
    public ResponseEntity<Pago> actualizarEstado(
        @Parameter(description = "ID del pago", example = "1") @PathVariable Long id,
        @Parameter(description = "Nuevo estado del pago", example = "APROBADO") @RequestParam String estado
    ) {
        return ResponseEntity.ok(pagoService.actualizarEstado(id, estado));
    }
}
