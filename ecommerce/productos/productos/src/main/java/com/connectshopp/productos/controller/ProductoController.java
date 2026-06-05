package com.connectshopp.productos.controller;

import com.connectshopp.productos.dto.ImagenProductoRequest;
import com.connectshopp.productos.model.ImagenProducto;
import com.connectshopp.productos.model.Producto;
import com.connectshopp.productos.service.ProductoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<Producto> crear(
        @RequestParam Long categoriaId,
        @RequestParam Long marcaId,
        @Valid @RequestBody Producto producto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(productoService.crear(categoriaId, marcaId, producto));
    }

    @GetMapping
    public ResponseEntity<List<Producto>> buscar(
        @RequestParam(required = false) Long categoriaId,
        @RequestParam(required = false) Long marcaId,
        @RequestParam(required = false) String nombre
    ) {
        return ResponseEntity.ok(productoService.buscar(categoriaId, marcaId, nombre));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    @PostMapping("/{id}/imagenes")
    public ResponseEntity<ImagenProducto> agregarImagen(
        @PathVariable Long id,
        @Valid @RequestBody ImagenProductoRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.agregarImagen(id, request));
    }
}
