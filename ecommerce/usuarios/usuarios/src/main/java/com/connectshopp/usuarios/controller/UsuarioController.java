package com.connectshopp.usuarios.controller;

import com.connectshopp.usuarios.dto.DireccionRequest;
import com.connectshopp.usuarios.model.Direccion;
import com.connectshopp.usuarios.model.Usuario;
import com.connectshopp.usuarios.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestParam Long rolId, @Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crear(rolId, usuario));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> buscar(
        @RequestParam(required = false) String email,
        @RequestParam(required = false) Long rolId
    ) {
        return ResponseEntity.ok(usuarioService.buscar(email, rolId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PostMapping("/{id}/direcciones")
    public ResponseEntity<Direccion> agregarDireccion(
        @PathVariable Long id,
        @Valid @RequestBody DireccionRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.agregarDireccion(id, request));
    }
}
