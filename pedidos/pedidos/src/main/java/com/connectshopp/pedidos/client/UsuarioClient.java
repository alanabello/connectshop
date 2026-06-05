package com.connectshopp.pedidos.client;

import com.connectshopp.pedidos.exception.BusinessException;
import com.connectshopp.pedidos.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Component
public class UsuarioClient {

    private final RestClient restClient;

    public UsuarioClient(RestClient.Builder builder, @Value("${connectshopp.services.usuarios.url}") String baseUrl) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    public void validarUsuarioActivo(Long usuarioId) {
        try {
            UsuarioResponse usuario = restClient.get()
                .uri("/usuarios/{id}", usuarioId)
                .retrieve()
                .body(UsuarioResponse.class);

            if (usuario == null || usuario.id() == null) {
                throw new ResourceNotFoundException("Usuario no encontrado");
            }
            if (!Boolean.TRUE.equals(usuario.activo())) {
                throw new BusinessException("Usuario inactivo");
            }
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().is4xxClientError()) {
                throw new ResourceNotFoundException("Usuario no encontrado");
            }
            throw new BusinessException("No se pudo validar el usuario");
        }
    }

    private record UsuarioResponse(Long id, Boolean activo) {
    }
}
