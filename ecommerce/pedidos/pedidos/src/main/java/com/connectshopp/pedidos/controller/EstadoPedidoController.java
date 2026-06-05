package com.connectshopp.pedidos.controller;

import com.connectshopp.pedidos.model.EstadoPedido;
import com.connectshopp.pedidos.repository.EstadoPedidoRepository;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estados-pedido")
public class EstadoPedidoController {

    private final EstadoPedidoRepository estadoPedidoRepository;

    public EstadoPedidoController(EstadoPedidoRepository estadoPedidoRepository) {
        this.estadoPedidoRepository = estadoPedidoRepository;
    }

    @GetMapping
    public ResponseEntity<List<EstadoPedido>> listar() {
        return ResponseEntity.ok(estadoPedidoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<EstadoPedido> crear(@Valid @RequestBody EstadoPedido estado) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoPedidoRepository.save(estado));
    }
}
