package com.connectshopp.usuarios.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
    name = "roles_usuario",
    uniqueConstraints = @UniqueConstraint(name = "uk_roles_usuario_nombre", columnNames = "nombre")
)
@Schema(description = "Rol asignable a usuarios")
public class RolUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "ID del rol", example = "1")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    @NotBlank
    @Schema(description = "Nombre del rol", example = "CLIENTE")
    private String nombre;

    @Column(name = "descripcion", length = 255)
    @Schema(description = "Descripcion del rol", example = "Usuario comprador de la plataforma")
    private String descripcion;

    @OneToMany(mappedBy = "rol", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Usuario> usuarios = new ArrayList<>();

    public RolUsuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
