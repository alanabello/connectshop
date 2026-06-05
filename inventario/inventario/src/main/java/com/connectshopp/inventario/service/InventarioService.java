package com.connectshopp.inventario.service;

import com.connectshopp.inventario.dto.CrearInventarioRequest;
import com.connectshopp.inventario.dto.MovimientoInventarioRequest;
import com.connectshopp.inventario.exception.BusinessException;
import com.connectshopp.inventario.exception.ResourceNotFoundException;
import com.connectshopp.inventario.model.Inventario;
import com.connectshopp.inventario.model.MovimientoInventario;
import com.connectshopp.inventario.repository.InventarioRepository;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (inventarioRepository.existsByProductoId(request.productoId())) {
            throw new BusinessException("Ya existe inventario para el producto " + request.productoId());
        }

        Inventario inventario = new Inventario();
        inventario.setProductoId(request.productoId());
        inventario.setStockActual(request.stockActual());
        inventario.setStockMinimo(request.stockMinimo());
        return inventarioRepository.save(inventario);
    }

    @Transactional
    public MovimientoInventario registrarMovimiento(Long inventarioId, MovimientoInventarioRequest request) {
        Inventario inventario = buscarPorId(inventarioId);
        String tipo = request.tipo().toUpperCase();

        if (!TIPOS_VALIDOS.contains(tipo)) {
            throw new BusinessException("Tipo de movimiento invalido: " + request.tipo());
        }

        if (SALIDA.equals(tipo) && inventario.getStockActual() < request.cantidad()) {
            throw new BusinessException("Stock insuficiente para realizar salida");
        }

        if (ENTRADA.equals(tipo)) {
            inventario.setStockActual(inventario.getStockActual() + request.cantidad());
        } else if (SALIDA.equals(tipo)) {
            inventario.setStockActual(inventario.getStockActual() - request.cantidad());
        } else {
            inventario.setStockActual(request.cantidad());
        }

        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.setTipo(tipo);
        movimiento.setCantidad(request.cantidad());
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
