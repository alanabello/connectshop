package com.connectshopp.pagos.controller;

import com.connectshopp.pagos.model.MetodoPago;
import com.connectshopp.pagos.repository.MetodoPagoRepository;
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
@RequestMapping("/metodos-pago")
public class MetodoPagoController {

    private final MetodoPagoRepository metodoPagoRepository;

    public MetodoPagoController(MetodoPagoRepository metodoPagoRepository) {
        this.metodoPagoRepository = metodoPagoRepository;
    }

    @GetMapping
    public ResponseEntity<List<MetodoPago>> listar() {
        return ResponseEntity.ok(metodoPagoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<MetodoPago> crear(@Valid @RequestBody MetodoPago metodoPago) {
        return ResponseEntity.status(HttpStatus.CREATED).body(metodoPagoRepository.save(metodoPago));
    }
}
