package com.connectshopp.usuarios.controller;

import com.connectshopp.usuarios.dto.DireccionRequest;
import com.connectshopp.usuarios.model.Direccion;
import com.connectshopp.usuarios.model.Usuario;
import com.connectshopp.usuarios.service.UsuarioService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@Tag(name = "Usuarios", description = "Operaciones para administrar usuarios y direcciones")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @Operation(summary = "Crear usuario", description = "Registra un usuario y lo asocia a un rol.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Usuario creado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Rol no encontrado", content = @Content)
    })
    public ResponseEntity<Usuario> crear(
        @Parameter(description = "ID del rol", example = "1") @RequestParam Long rolId,
        @Valid @RequestBody Usuario usuario
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crear(rolId, usuario));
    }

    @GetMapping
    @Operation(summary = "Buscar usuarios", description = "Lista usuarios filtrando opcionalmente por email o rol.")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios",
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
            array = @ArraySchema(schema = @Schema(implementation = Usuario.class))))
    public ResponseEntity<List<Usuario>> buscar(
        @Parameter(description = "Email del usuario", example = "ana@connectshop.local") @RequestParam(required = false) String email,
        @Parameter(description = "ID del rol", example = "1") @RequestParam(required = false) Long rolId
    ) {
        return ResponseEntity.ok(usuarioService.buscar(email, rolId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuario por ID", description = "Obtiene un usuario especifico por su identificador.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario encontrado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })
    public ResponseEntity<Usuario> buscarPorId(@Parameter(description = "ID del usuario", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PostMapping("/{id}/direcciones")
    @Operation(summary = "Agregar direccion a usuario", description = "Registra una direccion asociada a un usuario.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Direccion registrada",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Direccion.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })
    public ResponseEntity<Direccion> agregarDireccion(
        @Parameter(description = "ID del usuario", example = "1") @PathVariable Long id,
        @Valid @RequestBody DireccionRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.agregarDireccion(id, request));
    }
}
