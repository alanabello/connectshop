package com.connectshopp.pagos.controller;

import com.connectshopp.pagos.dto.CrearPagoRequest;
import com.connectshopp.pagos.model.Pago;
import com.connectshopp.pagos.service.PagoService;
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
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @PostMapping
    public ResponseEntity<Pago> procesar(
        @RequestParam Long metodoId,
        @Valid @RequestBody CrearPagoRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pagoService.procesar(metodoId, request));
    }

    @GetMapping
    public ResponseEntity<List<Pago>> buscar(
        @RequestParam(required = false) Long pedidoId,
        @RequestParam(required = false) String estado
    ) {
        return ResponseEntity.ok(pagoService.buscar(pedidoId, estado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.buscarPorId(id));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Pago> actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(pagoService.actualizarEstado(id, estado));
    }
}
