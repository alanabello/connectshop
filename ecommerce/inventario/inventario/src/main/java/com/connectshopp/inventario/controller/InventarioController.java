package com.connectshopp.inventario.controller;

import com.connectshopp.inventario.dto.CrearInventarioRequest;
import com.connectshopp.inventario.dto.MovimientoInventarioRequest;
import com.connectshopp.inventario.model.Inventario;
import com.connectshopp.inventario.model.MovimientoInventario;
import com.connectshopp.inventario.service.InventarioService;
import jakarta.validation.Valid;
import java.util.List;
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
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @PostMapping
    public ResponseEntity<Inventario> crear(@Valid @RequestBody CrearInventarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventarioService.crear(request));
    }

    @GetMapping
    public ResponseEntity<List<Inventario>> listar() {
        return ResponseEntity.ok(inventarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(inventarioService.buscarPorId(id));
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<Inventario> buscarPorProductoId(@PathVariable Long productoId) {
        return ResponseEntity.ok(inventarioService.buscarPorProductoId(productoId));
    }

    @GetMapping("/alertas")
    public ResponseEntity<List<Inventario>> alertasStockMinimo() {
        return ResponseEntity.ok(inventarioService.alertasStockMinimo());
    }

    @PostMapping("/{id}/movimientos")
    public ResponseEntity<MovimientoInventario> registrarMovimiento(
        @PathVariable Long id,
        @Valid @RequestBody MovimientoInventarioRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(inventarioService.registrarMovimiento(id, request));
    }
}
