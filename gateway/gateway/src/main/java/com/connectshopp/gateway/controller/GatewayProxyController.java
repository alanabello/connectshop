package com.connectshopp.gateway.controller;

import com.connectshopp.gateway.config.ServiceUrlProperties;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Collections;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class GatewayProxyController {

    private final RestTemplate restTemplate;
    private final ServiceUrlProperties serviceUrlProperties;

    public GatewayProxyController(RestTemplate restTemplate, ServiceUrlProperties serviceUrlProperties) {
        this.restTemplate = restTemplate;
        this.serviceUrlProperties = serviceUrlProperties;
    }

    @RequestMapping("/{service}/**")
    public ResponseEntity<byte[]> proxy(
        @PathVariable String service,
        @RequestBody(required = false) byte[] body,
        HttpServletRequest request
    ) {
        String serviceUrl = serviceUrlProperties.getServiceUrl(service);
        if (serviceUrl == null) {
            return ResponseEntity.notFound().build();
        }

        URI targetUri = buildTargetUri(serviceUrl, service, request);
        HttpHeaders headers = copyRequestHeaders(request);
        HttpEntity<byte[]> entity = new HttpEntity<>(body, headers);

        ResponseEntity<byte[]> response = restTemplate.exchange(
            targetUri,
            HttpMethod.valueOf(request.getMethod()),
            entity,
            byte[].class
        );

        return ResponseEntity
            .status(response.getStatusCode())
            .headers(filterResponseHeaders(response.getHeaders()))
            .body(response.getBody());
    }

    private URI buildTargetUri(String serviceUrl, String service, HttpServletRequest request) {
        String prefix = "/api/" + service;
        String path = request.getRequestURI().substring(prefix.length());
        String queryString = request.getQueryString();

        UriComponentsBuilder builder = UriComponentsBuilder
            .fromUri(URI.create(serviceUrl))
            .path(path);

        if (queryString != null) {
            builder.query(queryString);
        }

        return builder.build(true).toUri();
    }

    private HttpHeaders copyRequestHeaders(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        Collections.list(request.getHeaderNames()).forEach(headerName -> {
            if (!HttpHeaders.HOST.equalsIgnoreCase(headerName)
                && !HttpHeaders.CONTENT_LENGTH.equalsIgnoreCase(headerName)) {
                headers.put(headerName, Collections.list(request.getHeaders(headerName)));
            }
        });
        return headers;
    }

    private HttpHeaders filterResponseHeaders(HttpHeaders originalHeaders) {
        HttpHeaders headers = new HttpHeaders();
        originalHeaders.forEach((name, values) -> {
            if (!HttpHeaders.TRANSFER_ENCODING.equalsIgnoreCase(name)
                && !HttpHeaders.CONTENT_LENGTH.equalsIgnoreCase(name)) {
                headers.put(name, values);
            }
        });
        return headers;
    }
}
