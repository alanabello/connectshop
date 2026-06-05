package com.connectshopp.pedidos.client;

import com.connectshopp.pedidos.exception.BusinessException;
import com.connectshopp.pedidos.exception.ResourceNotFoundException;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Component
public class ProductoClient {

    private final RestClient restClient;

    public ProductoClient(RestClient.Builder builder, @Value("${connectshopp.services.productos.url}") String baseUrl) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    public ProductoInfo buscarProducto(Long productoId) {
        try {
            ProductoInfo producto = restClient.get()
                .uri("/productos/{id}", productoId)
                .retrieve()
                .body(ProductoInfo.class);

            if (producto == null || producto.id() == null) {
                throw new ResourceNotFoundException("Producto no encontrado");
            }
            return producto;
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().is4xxClientError()) {
                throw new ResourceNotFoundException("Producto no encontrado");
            }
            throw new BusinessException("No se pudo consultar el producto");
        }
    }

    public record ProductoInfo(Long id, BigDecimal precio, Integer stock) {
    }
}
