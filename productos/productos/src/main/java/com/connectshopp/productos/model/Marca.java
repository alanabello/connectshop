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
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "marcas")
@Schema(description = "Marca de productos")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "ID de la marca", example = "1")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 80)
    @NotBlank
    @Schema(description = "Nombre de la marca", example = "ConnectTech")
    private String nombre;

    @Column(name = "pais_origen", length = 80)
    @Schema(description = "Pais de origen de la marca", example = "Chile")
    private String paisOrigen;

    @Column(name = "logo_url", length = 500)
    @Schema(description = "URL del logo de la marca", example = "https://cdn.connectshop.local/marcas/connecttech.png")
    private String logoUrl;

    @OneToMany(mappedBy = "marca", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Producto> productos = new ArrayList<>();

    public Marca() {
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

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
