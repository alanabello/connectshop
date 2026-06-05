package com.connectshopp.pedidos.controller;

import com.connectshopp.pedidos.dto.CrearPedidoRequest;
import com.connectshopp.pedidos.model.Pedido;
import com.connectshopp.pedidos.service.PedidoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
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
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> crear(@Valid @RequestBody CrearPedidoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.crear(request));
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> buscar(
        @RequestParam(required = false) Long usuarioId,
        @RequestParam(required = false) Long estadoId
    ) {
        return ResponseEntity.ok(pedidoService.buscar(usuarioId, estadoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Pedido> cambiarEstado(@PathVariable Long id, @RequestParam Long estadoId) {
        return ResponseEntity.ok(pedidoService.cambiarEstado(id, estadoId));
    }
}
