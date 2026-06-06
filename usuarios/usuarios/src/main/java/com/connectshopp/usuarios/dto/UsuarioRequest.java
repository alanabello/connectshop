package com.connectshopp.usuarios.dto;

public class UsuarioRequest {
    private String nombre;
    private String email;
    private String password;
    private Long rolId;

    public UsuarioRequest() {
    }

    public UsuarioRequest(String nombre, String email, String password, Long rolId) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rolId = rolId;
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

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }
}
