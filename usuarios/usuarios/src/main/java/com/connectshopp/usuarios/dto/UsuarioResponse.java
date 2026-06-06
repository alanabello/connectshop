package com.connectshopp.usuarios.dto;

public class UsuarioResponse {
    private Long id;
    private String nombre;
    private String email;
    private Boolean activo;
    private Long rolId;
    private String rolNombre;

    public UsuarioResponse() {
    }

    public UsuarioResponse(Long id, String nombre, String email, Boolean activo, Long rolId, String rolNombre) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.activo = activo;
        this.rolId = rolId;
        this.rolNombre = rolNombre;
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }
}
