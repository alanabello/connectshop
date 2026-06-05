package com.connectshopp.pedidos.client;

import com.connectshopp.pedidos.exception.BusinessException;
import com.connectshopp.pedidos.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Component
public class InventarioClient {

    private static final String SALIDA = "SALIDA";

    private final RestClient restClient;

    public InventarioClient(RestClient.Builder builder, @Value("${connectshopp.services.inventario.url}") String baseUrl) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    public InventarioInfo buscarPorProductoId(Long productoId) {
        try {
            InventarioInfo inventario = restClient.get()
                .uri("/inventarios/producto/{productoId}", productoId)
                .retrieve()
                .body(InventarioInfo.class);

            if (inventario == null || inventario.id() == null) {
                throw new ResourceNotFoundException("Inventario no encontrado para producto " + productoId);
            }
            return inventario;
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().is4xxClientError()) {
                throw new ResourceNotFoundException("Inventario no encontrado para producto " + productoId);
            }
            throw new BusinessException("No se pudo consultar inventario");
        }
    }

    public void descontarStock(Long inventarioId, Integer cantidad) {
        try {
            restClient.post()
                .uri("/inventarios/{id}/movimientos", inventarioId)
                .body(new MovimientoInventarioRequest(SALIDA, cantidad))
                .retrieve()
                .toBodilessEntity();
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().is4xxClientError()) {
                throw new BusinessException("Stock insuficiente o movimiento invalido");
            }
            throw new BusinessException("No se pudo descontar inventario");
        }
    }

    public record InventarioInfo(Long id, Long productoId, Integer stockActual) {
    }

    private record MovimientoInventarioRequest(String tipo, Integer cantidad) {
    }
}
