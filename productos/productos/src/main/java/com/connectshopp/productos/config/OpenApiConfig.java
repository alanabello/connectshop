package com.connectshopp.productos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI productosOpenApi() {
        return new OpenAPI()
            .info(new Info()
                .title("ConnectShop - API de Productos")
                .version("0.0.1")
                .description("Microservicio para administrar catalogo, categorias, marcas e imagenes de productos.")
                .contact(new Contact().name("ConnectShop").email("soporte@connectshop.local"))
                .license(new License().name("Uso academico")))
            .addServersItem(new Server().url("http://localhost:8081").description("Servidor local de productos"));
    }
}
