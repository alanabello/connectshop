package com.connectshopp.usuarios.controller;

import com.connectshopp.usuarios.model.RolUsuario;
import com.connectshopp.usuarios.repository.RolUsuarioRepository;
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
@RequestMapping("/roles")
public class RolUsuarioController {

    private final RolUsuarioRepository rolUsuarioRepository;

    public RolUsuarioController(RolUsuarioRepository rolUsuarioRepository) {
        this.rolUsuarioRepository = rolUsuarioRepository;
    }

    @GetMapping
    public ResponseEntity<List<RolUsuario>> listar() {
        return ResponseEntity.ok(rolUsuarioRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<RolUsuario> crear(@Valid @RequestBody RolUsuario rol) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rolUsuarioRepository.save(rol));
    }
}
