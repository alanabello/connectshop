package com.connectshopp.inventario.controller;

import com.connectshopp.inventario.dto.CrearInventarioRequest;
import com.connectshopp.inventario.dto.MovimientoInventarioRequest;
import com.connectshopp.inventario.model.Inventario;
import com.connectshopp.inventario.model.MovimientoInventario;
import com.connectshopp.inventario.service.InventarioService;
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
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventarios")
@Tag(name = "Inventarios", description = "Operaciones para administrar stock y movimientos de inventario")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @PostMapping
    @Operation(summary = "Crear inventario", description = "Registra el stock inicial y minimo de un producto.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Inventario creado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Inventario.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos", content = @Content)
    })
    public ResponseEntity<Inventario> crear(@Valid @RequestBody CrearInventarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventarioService.crear(request));
    }

    @GetMapping
    @Operation(summary = "Listar inventarios", description = "Obtiene todos los registros de inventario disponibles.")
    @ApiResponse(responseCode = "200", description = "Lista de inventarios",
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
            array = @ArraySchema(schema = @Schema(implementation = Inventario.class))))
    public ResponseEntity<List<Inventario>> listar() {
        return ResponseEntity.ok(inventarioService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar inventario por ID", description = "Obtiene un inventario especifico por su identificador.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Inventario encontrado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Inventario.class))),
        @ApiResponse(responseCode = "404", description = "Inventario no encontrado", content = @Content)
    })
    public ResponseEntity<Inventario> buscarPorId(@Parameter(description = "ID del inventario", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(inventarioService.buscarPorId(id));
    }

    @GetMapping("/producto/{productoId}")
    @Operation(summary = "Buscar inventario por producto", description = "Obtiene el inventario asociado a un producto.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Inventario encontrado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Inventario.class))),
        @ApiResponse(responseCode = "404", description = "Inventario no encontrado", content = @Content)
    })
    public ResponseEntity<Inventario> buscarPorProductoId(@Parameter(description = "ID del producto", example = "10") @PathVariable Long productoId) {
        return ResponseEntity.ok(inventarioService.buscarPorProductoId(productoId));
    }

    @GetMapping("/alertas")
    @Operation(summary = "Listar alertas de stock minimo", description = "Obtiene inventarios cuyo stock actual esta bajo o igual al minimo.")
    @ApiResponse(responseCode = "200", description = "Lista de inventarios con alerta",
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
            array = @ArraySchema(schema = @Schema(implementation = Inventario.class))))
    public ResponseEntity<List<Inventario>> alertasStockMinimo() {
        return ResponseEntity.ok(inventarioService.alertasStockMinimo());
    }

    @PostMapping("/{id}/movimientos")
    @Operation(summary = "Registrar movimiento de inventario", description = "Registra una entrada o salida de stock para un inventario.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Movimiento registrado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MovimientoInventario.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Inventario no encontrado", content = @Content)
    })
    public ResponseEntity<MovimientoInventario> registrarMovimiento(
        @Parameter(description = "ID del inventario", example = "1") @PathVariable Long id,
        @Valid @RequestBody MovimientoInventarioRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(inventarioService.registrarMovimiento(id, request));
    }
}
