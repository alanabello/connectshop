package com.connectshopp.productos.model;

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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
@Schema(description = "Categoria del catalogo de productos")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "ID de la categoria", example = "1")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 80)
    @NotBlank
    @Schema(description = "Nombre de la categoria", example = "Computacion")
    private String nombre;

    @Column(name = "descripcion", length = 255)
    @Schema(description = "Descripcion de la categoria", example = "Productos tecnologicos y accesorios")
    private String descripcion;

    @Column(name = "activo", nullable = false)
    @NotNull
    @Schema(description = "Indica si la categoria esta activa", example = "true")
    private Boolean activo = true;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Producto> productos = new ArrayList<>();

    public Categoria() {
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
