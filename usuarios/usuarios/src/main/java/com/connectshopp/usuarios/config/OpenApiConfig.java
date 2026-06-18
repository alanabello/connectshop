package com.connectshopp.usuarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI usuariosOpenApi() {
        return new OpenAPI()
            .info(new Info()
                .title("ConnectShop - API de Usuarios")
                .version("0.0.1")
                .description("Microservicio para administrar usuarios, roles y direcciones.")
                
                .license(new License().name("Uso academico")))
            .addServersItem(new Server()
            .url("http://localhost:8082")
            .description("Servidor local de usuarios"));
    }
}


