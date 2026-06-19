package com.connectshopp.gateway.controller;

import com.connectshopp.gateway.config.ServiceUrlProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DocsController {

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final ServiceUrlProperties serviceUrlProperties;

    public DocsController(
        RestTemplate restTemplate,
        ServiceUrlProperties serviceUrlProperties
    ) {
        this.objectMapper = new ObjectMapper();
        this.restTemplate = restTemplate;
        this.serviceUrlProperties = serviceUrlProperties;
    }

    @GetMapping("/docs/{service}")
    public ResponseEntity<JsonNode> getDocs(@PathVariable String service, HttpServletRequest request)
        throws JsonProcessingException {
        String serviceUrl = serviceUrlProperties.getServiceUrl(service);
        if (serviceUrl == null) {
            return ResponseEntity.notFound().build();
        }

        String docsJson = restTemplate.getForObject(serviceUrl + "/v3/api-docs", String.class);
        JsonNode docs = objectMapper.readTree(docsJson);
        if (!(docs instanceof ObjectNode root)) {
            return ResponseEntity.badRequest().build();
        }

        ArrayNode servers = objectMapper.createArrayNode();
        ObjectNode server = objectMapper.createObjectNode();
        server.put("url", getGatewayBaseUrl(request) + "/api/" + service);
        server.put("description", "API Gateway");
        servers.add(server);
        root.set("servers", servers);

        return ResponseEntity.ok(root);
    }

    private String getGatewayBaseUrl(HttpServletRequest request) {
        String scheme = request.getScheme();
        String host = request.getServerName();
        int port = request.getServerPort();

        boolean defaultPort = ("http".equals(scheme) && port == 80)
            || ("https".equals(scheme) && port == 443);

        if (defaultPort) {
            return scheme + "://" + host;
        }

        return scheme + "://" + host + ":" + port;
    }
}
