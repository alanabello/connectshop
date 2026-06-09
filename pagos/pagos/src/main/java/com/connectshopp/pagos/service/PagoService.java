package com.connectshopp.pagos.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connectshopp.pagos.dto.CrearPagoRequest;
import com.connectshopp.pagos.exception.ResourceNotFoundException;
import com.connectshopp.pagos.model.MetodoPago;
import com.connectshopp.pagos.model.Pago;
import com.connectshopp.pagos.model.TransaccionPago;
import com.connectshopp.pagos.repository.MetodoPagoRepository;
import com.connectshopp.pagos.repository.PagoRepository;

@Service
public class PagoService {

    private static final String PENDIENTE = "PENDIENTE";
    private static final Set<String> ESTADOS_VALIDOS = Set.of("PENDIENTE", "APROBADO", "RECHAZADO", "ANULADO");

    private final PagoRepository pagoRepository;
    private final MetodoPagoRepository metodoPagoRepository;

    public PagoService(PagoRepository pagoRepository, MetodoPagoRepository metodoPagoRepository) {
        this.pagoRepository = pagoRepository;
        this.metodoPagoRepository = metodoPagoRepository;
    }

    @Transactional
    public Pago procesar(Long metodoId, CrearPagoRequest request) {
        if (request.getPedidoId() <= 0) {
            throw new IllegalArgumentException("pedidoId debe ser mayor que cero");
        }

        MetodoPago metodo = metodoPagoRepository.findById(metodoId)
            .orElseThrow(() -> new ResourceNotFoundException("Metodo de pago no encontrado"));
        if (!Boolean.TRUE.equals(metodo.getActivo())) {
            throw new IllegalStateException("Metodo de pago inactivo");
        }

        Pago pago = new Pago();
        pago.setPedidoId(request.getPedidoId());
        pago.setMonto(request.getMonto());
        pago.setEstado(PENDIENTE);
        pago.setMetodoPago(metodo);

        TransaccionPago transaccion = new TransaccionPago();
        transaccion.setEstado(PENDIENTE);
        transaccion.setMensaje("Transaccion inicial creada");
        pago.agregarTransaccion(transaccion);

        return pagoRepository.save(pago);
    }

    @Transactional
    public Pago actualizarEstado(Long pagoId, String estado) {
        Pago pago = buscarPorId(pagoId);
        String nuevoEstado = estado.toUpperCase();
        if (!ESTADOS_VALIDOS.contains(nuevoEstado)) {
            throw new IllegalArgumentException("Estado de pago invalido: " + estado);
        }

        pago.setEstado(nuevoEstado);

        TransaccionPago transaccion = new TransaccionPago();
        transaccion.setEstado(nuevoEstado);
        transaccion.setMensaje("Pago actualizado a " + nuevoEstado);
        pago.agregarTransaccion(transaccion);

        return pagoRepository.save(pago);
    }

    @Transactional(readOnly = true)
    public Pago buscarPorId(Long id) {
        return pagoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Pago> buscar(Long pedidoId, String estado) {
        if (pedidoId != null) {
            return pagoRepository.findByPedidoId(pedidoId);
        }
        if (estado != null && !estado.isBlank()) {
            return pagoRepository.findByEstadoIgnoreCase(estado);
        }
        return pagoRepository.findAll();
    }
}
