package com.connectshopp.pedidos.client;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Component
public class PagoClient {

    private final RestClient restClient;

    public PagoClient(RestClient.Builder builder, @Value("${connectshopp.services.pagos.url}") String baseUrl) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    public void crearPago(Long metodoPagoId, Long pedidoId, BigDecimal monto) {
        try {
            restClient.post()
                .uri(uriBuilder -> uriBuilder
                    .path("/pagos")
                    .queryParam("metodoId", metodoPagoId)
                    .build())
                .body(new CrearPagoRequest(pedidoId, monto))
                .retrieve()
                .toBodilessEntity();
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().is4xxClientError()) {
                throw new IllegalStateException("No se pudo crear el pago para el pedido");
            }
            throw new IllegalStateException("Servicio de pagos no disponible");
        }
    }

    private record CrearPagoRequest(Long pedidoId, BigDecimal monto) {
    }
}
