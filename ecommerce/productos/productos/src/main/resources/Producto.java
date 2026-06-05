package com.shopconnect.productos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal precio;

    @NotNull
    @PositiveOrZero
    private Integer stock;

    @Column(unique = true)
    @NotBlank
    private String sku;

    @ManyToOne @JoinColumn(name = "categoria_id") private Categoria categoria;
    @ManyToOne @JoinColumn(name = "marca_id") private Marca marca;
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImagenProducto> imagenes = new ArrayList<>();
}