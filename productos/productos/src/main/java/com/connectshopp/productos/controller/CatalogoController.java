package com.connectshopp.productos.controller;

import com.connectshopp.productos.model.Categoria;
import com.connectshopp.productos.model.Marca;
import com.connectshopp.productos.repository.CategoriaRepository;
import com.connectshopp.productos.repository.MarcaRepository;
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
@RequestMapping("/catalogo")
public class CatalogoController {

    private final CategoriaRepository categoriaRepository;
    private final MarcaRepository marcaRepository;

    public CatalogoController(CategoriaRepository categoriaRepository, MarcaRepository marcaRepository) {
        this.categoriaRepository = categoriaRepository;
        this.marcaRepository = marcaRepository;
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @PostMapping("/categorias")
    public ResponseEntity<Categoria> crearCategoria(@Valid @RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
    }

    @GetMapping("/marcas")
    public ResponseEntity<List<Marca>> listarMarcas() {
        return ResponseEntity.ok(marcaRepository.findAll());
    }

    @PostMapping("/marcas")
    public ResponseEntity<Marca> crearMarca(@Valid @RequestBody Marca marca) {
        return ResponseEntity.status(HttpStatus.CREATED).body(marcaRepository.save(marca));
    }
}
