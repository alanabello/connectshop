package com.connectshopp.usuarios.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
    name = "usuarios",
    uniqueConstraints = @UniqueConstraint(name = "uk_usuarios_email", columnNames = "email")
)
@Schema(description = "Usuario registrado en ConnectShop")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "ID del usuario", example = "1")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 120)
    @NotBlank
    @Schema(description = "Nombre completo del usuario", example = "Ana Perez")
    private String nombre;

    @Column(name = "email", nullable = false, length = 150)
    @NotBlank
    @Email
    @Schema(description = "Correo electronico unico del usuario", example = "ana@connectshop.local")
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    @NotBlank
    @Schema(description = "Password del usuario", example = "Secreto123")
    private String password;

    @Column(name = "activo", nullable = false)
    @NotNull
    @Schema(description = "Indica si el usuario esta activo", example = "true")
    private Boolean activo = true;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rol_id", nullable = false)
    @Schema(description = "Rol asignado al usuario")
    private RolUsuario rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Direcciones registradas del usuario")
    private List<Direccion> direcciones = new ArrayList<>();

    public Usuario() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public void agregarDireccion(Direccion direccion) {
        direccion.setUsuario(this);
        direcciones.add(direccion);
    }
}
