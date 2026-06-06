package com.connectshopp.pedidos.service;

import com.connectshopp.pedidos.client.InventarioClient;
import com.connectshopp.pedidos.client.PagoClient;
import com.connectshopp.pedidos.client.ProductoClient;
import com.connectshopp.pedidos.client.ProductoClient.ProductoInfo;
import com.connectshopp.pedidos.client.UsuarioClient;
import com.connectshopp.pedidos.dto.CrearPedidoRequest;
import com.connectshopp.pedidos.dto.DetallePedidoRequest;
import com.connectshopp.pedidos.exception.BusinessException;
import com.connectshopp.pedidos.exception.ResourceNotFoundException;
import com.connectshopp.pedidos.model.DetallePedido;
import com.connectshopp.pedidos.model.EstadoPedido;
import com.connectshopp.pedidos.model.Pedido;
import com.connectshopp.pedidos.repository.EstadoPedidoRepository;
import com.connectshopp.pedidos.repository.PedidoRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

    private static final String ESTADO_INICIAL = "PENDIENTE";
    private static final Map<String, Set<String>> TRANSICIONES = Map.of(
        "PENDIENTE", Set.of("CONFIRMADO"),
        "CONFIRMADO", Set.of("ENVIADO", "CANCELADO"),
        "ENVIADO", Set.of("ENTREGADO", "CANCELADO")
    );

    private final PedidoRepository pedidoRepository;
    private final EstadoPedidoRepository estadoPedidoRepository;
    private final UsuarioClient usuarioClient;
    private final ProductoClient productoClient;
    private final InventarioClient inventarioClient;
    private final PagoClient pagoClient;

    public PedidoService(
        PedidoRepository pedidoRepository,
        EstadoPedidoRepository estadoPedidoRepository,
        UsuarioClient usuarioClient,
        ProductoClient productoClient,
        InventarioClient inventarioClient,
        PagoClient pagoClient
    ) {
        this.pedidoRepository = pedidoRepository;
        this.estadoPedidoRepository = estadoPedidoRepository;
        this.usuarioClient = usuarioClient;
        this.productoClient = productoClient;
        this.inventarioClient = inventarioClient;
        this.pagoClient = pagoClient;
    }

    @Transactional
    public Pedido crear(CrearPedidoRequest request) {
        usuarioClient.validarUsuarioActivo(request.getUsuarioId());

        EstadoPedido estado = estadoPedidoRepository.findByNombreIgnoreCase(ESTADO_INICIAL)
            .orElseThrow(() -> new ResourceNotFoundException("Estado inicial PENDIENTE no encontrado"));

        Pedido pedido = new Pedido();
        pedido.setUsuarioId(request.getUsuarioId());
        pedido.setEstado(estado);

        BigDecimal total = BigDecimal.ZERO;
        for (DetallePedidoRequest item : request.getDetalles()) {
            ProductoInfo producto = productoClient.buscarProducto(item.getProductoId());
            InventarioClient.InventarioInfo inventario = inventarioClient.buscarPorProductoId(item.getProductoId());
            validarStockDisponible(item, inventario);

            DetallePedido detalle = new DetallePedido();
            detalle.setProductoId(producto.id());
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnit(producto.precio());
            pedido.agregarDetalle(detalle);
            total = total.add(producto.precio().multiply(BigDecimal.valueOf(item.getCantidad())));
        }

        pedido.setTotal(total);
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        for (DetallePedido detalle : pedidoGuardado.getDetalles()) {
            InventarioClient.InventarioInfo inventario = inventarioClient.buscarPorProductoId(detalle.getProductoId());
            inventarioClient.descontarStock(inventario.id(), detalle.getCantidad());
        }

        pagoClient.crearPago(request.getMetodoPagoId(), pedidoGuardado.getId(), pedidoGuardado.getTotal());
        return pedidoGuardado;
    }

    private void validarStockDisponible(DetallePedidoRequest item, InventarioClient.InventarioInfo inventario) {
        if (inventario.stockActual() < item.getCantidad()) {
            throw new BusinessException("Stock insuficiente para producto " + item.getProductoId());
        }
    }

    @Transactional
    public Pedido cambiarEstado(Long pedidoId, Long estadoId) {
        Pedido pedido = buscarPorId(pedidoId);
        EstadoPedido nuevoEstado = estadoPedidoRepository.findById(estadoId)
            .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado"));

        String actual = pedido.getEstado().getNombre().toUpperCase();
        String nuevo = nuevoEstado.getNombre().toUpperCase();
        if (!TRANSICIONES.getOrDefault(actual, Set.of()).contains(nuevo)) {
            throw new BusinessException("Transicion de estado no permitida: " + actual + " -> " + nuevo);
        }

        pedido.setEstado(nuevoEstado);
        return pedidoRepository.save(pedido);
    }

    @Transactional(readOnly = true)
    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Pedido> buscar(Long usuarioId, Long estadoId) {
        if (usuarioId != null) {
            return pedidoRepository.findByUsuarioId(usuarioId);
        }
        if (estadoId != null) {
            return pedidoRepository.findByEstadoId(estadoId);
        }
        return pedidoRepository.findAll();
    }
}
