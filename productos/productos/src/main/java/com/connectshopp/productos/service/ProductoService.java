package com.connectshopp.productos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connectshopp.productos.dto.ImagenProductoRequest;
import com.connectshopp.productos.exception.BusinessException;
import com.connectshopp.productos.exception.ResourceNotFoundException;
import com.connectshopp.productos.model.Categoria;
import com.connectshopp.productos.model.ImagenProducto;
import com.connectshopp.productos.model.Marca;
import com.connectshopp.productos.model.Producto;
import com.connectshopp.productos.repository.CategoriaRepository;
import com.connectshopp.productos.repository.MarcaRepository;
import com.connectshopp.productos.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final MarcaRepository marcaRepository;

    public ProductoService(
        ProductoRepository productoRepository,
        CategoriaRepository categoriaRepository,
        MarcaRepository marcaRepository
    ) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.marcaRepository = marcaRepository;
    }

    @Transactional
    public Producto crear(Long categoriaId, Long marcaId, Producto producto) {
        if (productoRepository.existsBySku(producto.getSku())) {
            throw new BusinessException("Ya existe un producto con el SKU " + producto.getSku());
        }
        if (producto.getStock() != null && producto.getStock() < 0) {
            throw new BusinessException("El stock no puede ser negativo");
        }

        Categoria categoria = categoriaRepository.findById(categoriaId)
            .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));
        Marca marca = marcaRepository.findById(marcaId)
            .orElseThrow(() -> new ResourceNotFoundException("Marca no encontrada"));

        producto.setCategoria(categoria);
        producto.setMarca(marca);
        return productoRepository.save(producto);
    }

    @Transactional
    public ImagenProducto agregarImagen(Long productoId, ImagenProductoRequest request) {
        Producto producto = buscarPorId(productoId);

        ImagenProducto imagen = new ImagenProducto();
        imagen.setUrl(request.getUrl());
        imagen.setOrden(Optional.ofNullable(request.getOrden()).orElse(0));
        imagen.setPrincipal(Boolean.TRUE.equals(request.getPrincipal()));

        producto.agregarImagen(imagen);
        productoRepository.save(producto);
        return imagen;
    }

    @Transactional(readOnly = true)
    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Producto> buscar(Long categoriaId, Long marcaId, String nombre) {
        if (categoriaId != null) {
            return productoRepository.findByCategoriaId(categoriaId);
        }
        if (marcaId != null) {
            return productoRepository.findByMarcaId(marcaId);
        }
        if (nombre != null && !nombre.isBlank()) {
            return productoRepository.findByNombreContainingIgnoreCase(nombre);
        }
        return productoRepository.findAll();
    }
}
