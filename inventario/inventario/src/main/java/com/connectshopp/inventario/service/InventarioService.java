package com.connectshopp.inventario.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connectshopp.inventario.dto.CrearInventarioRequest;
import com.connectshopp.inventario.dto.MovimientoInventarioRequest;
import com.connectshopp.inventario.exception.ResourceNotFoundException;
import com.connectshopp.inventario.model.Inventario;
import com.connectshopp.inventario.model.MovimientoInventario;
import com.connectshopp.inventario.repository.InventarioRepository;

@Service
public class InventarioService {

    private static final String ENTRADA = "ENTRADA";
    private static final String SALIDA = "SALIDA";
    private static final String AJUSTE = "AJUSTE";
    private static final Set<String> TIPOS_VALIDOS = Set.of(ENTRADA, SALIDA, AJUSTE);

    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    @Transactional
    public Inventario crear(CrearInventarioRequest request) {
        if (inventarioRepository.existsByProductoId(request.getProductoId())) {
            throw new IllegalArgumentException("Ya existe inventario para el producto " + request.getProductoId());
        }

        Inventario inventario = new Inventario();
        inventario.setProductoId(request.getProductoId());
        inventario.setStockActual(request.getStockActual());
        inventario.setStockMinimo(request.getStockMinimo());
        return inventarioRepository.save(inventario);
    }

    @Transactional
    public MovimientoInventario registrarMovimiento(Long inventarioId, MovimientoInventarioRequest request) {
        Inventario inventario = buscarPorId(inventarioId);
        String tipo = request.getTipo().toUpperCase();

        if (!TIPOS_VALIDOS.contains(tipo)) {
            throw new IllegalArgumentException("Tipo de movimiento invalido: " + request.getTipo());
        }

        if (SALIDA.equals(tipo) && inventario.getStockActual() < request.getCantidad()) {
            throw new IllegalStateException("Stock insuficiente para realizar salida");
        }

        switch (tipo) {
            case ENTRADA -> inventario.setStockActual(inventario.getStockActual() + request.getCantidad());
            case SALIDA -> inventario.setStockActual(inventario.getStockActual() - request.getCantidad());
            case AJUSTE -> inventario.setStockActual(request.getCantidad());
            default -> {
            }
        }

        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.setTipo(tipo);
        movimiento.setCantidad(request.getCantidad());
        inventario.agregarMovimiento(movimiento);

        inventarioRepository.save(inventario);
        return movimiento;
    }

    @Transactional(readOnly = true)
    public Inventario buscarPorId(Long id) {
        return inventarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado"));
    }

    @Transactional(readOnly = true)
    public Inventario buscarPorProductoId(Long productoId) {
        return inventarioRepository.findByProductoId(productoId)
            .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado para producto"));
    }

    @Transactional(readOnly = true)
    public List<Inventario> listar() {
        return inventarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Inventario> alertasStockMinimo() {
        return inventarioRepository.findAlertasStockMinimo();
    }
}
