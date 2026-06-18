package com.connectshopp.pedidos.controller;

import com.connectshopp.pedidos.dto.CrearPedidoRequest;
import com.connectshopp.pedidos.model.Pedido;
import com.connectshopp.pedidos.service.PedidoService;
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
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Operaciones para crear, consultar y cambiar estados de pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @Operation(summary = "Crear pedido", description = "Crea un pedido con sus detalles, usuario y metodo de pago.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Pedido creado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Recurso relacionado no encontrado", content = @Content)
    })
    public ResponseEntity<Pedido> crear(@Valid @RequestBody CrearPedidoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.crear(request));
    }

    @GetMapping
    @Operation(summary = "Listar pedidos", description = "Lista pedidos filtrando opcionalmente por usuario o estado.")
    @ApiResponse(responseCode = "200", description = "Lista de reservas de pedidos",
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
            array = @ArraySchema(schema = @Schema(implementation = Pedido.class))))
    public ResponseEntity<List<Pedido>> buscar(
        @Parameter(description = "ID del usuario", example = "1") @RequestParam(required = false) Long usuarioId,
        @Parameter(description = "ID del estado del pedido", example = "1") @RequestParam(required = false) Long estadoId
    ) {
        return ResponseEntity.ok(pedidoService.buscar(usuarioId, estadoId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedido por ID", description = "Obtiene un pedido especifico por su identificador.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pedido encontrado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado", content = @Content)
    })
    public ResponseEntity<Pedido> buscarPorId(@Parameter(description = "ID del pedido", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    @PatchMapping("/{id}/estado")
    @Operation(summary = "Cambiar estado del pedido", description = "Actualiza el estado asociado a un pedido.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Estado del pedido actualizado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "404", description = "Pedido o estado no encontrado", content = @Content)
    })
    public ResponseEntity<Pedido> cambiarEstado(
        @Parameter(description = "ID del pedido", example = "1") @PathVariable Long id,
        @Parameter(description = "ID del nuevo estado", example = "2") @RequestParam Long estadoId
    ) {
        return ResponseEntity.ok(pedidoService.cambiarEstado(id, estadoId));
    }
}
