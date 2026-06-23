# 🛍️ ConnectShop - Microservices E-Commerce Platform

Una plataforma de e-commerce moderna construida con **microservicios** en Spring Boot, diseñada para gestionar inventario, pedidos, pagos, productos y usuarios de forma escalable y desacoplada.

## 📋 Tabla de Contenidos

- [Características](#características)
- [Arquitectura](#arquitectura)
- [Microservicios](#microservicios)
- [Tecnología](#tecnología)
- [Requisitos Previos](#requisitos-previos)
- [Instalación y Ejecución](#instalación-y-ejecución)
- [Documentación API](#documentación-api)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Configuración](#configuración)
- [Contribuir](#contribuir)
- [Licencia](#licencia)

## ✨ Características

- ✅ **Arquitectura de Microservicios** - Servicios independientes y escalables
- ✅ **API REST con OpenAPI/Swagger** - Documentación automática e interactiva
- ✅ **Contenedorización Docker** - Despliegue consistente y portátil
- ✅ **Docker Compose** - Orquestación fácil de todos los servicios
- ✅ **Spring Boot** - Framework robusto y productivo
- ✅ **JPA/Hibernate** - Persistencia de datos
- ✅ **Comunicación Inter-servicios** - RestTemplate para consumo de APIs

## 🏗️ Arquitectura

```
┌─────────────────────────────────────────────────────┐
│                    Cliente/Gateway                   │
└─────────────────────────────────────────────────────┘
            ↓ ↓ ↓ ↓ ↓
┌──────────┬──────────┬──────────┬──────────┬─────────┐
│ Usuarios │ Productos│ Pedidos  │ Pagos    │Inventario
└──────────┴──────────┴──────────┴──────────┴─────────┘
     ↓          ↓          ↓          ↓          ↓
┌──────────────────────────────────────────────────────┐
│              Bases de Datos (PostgreSQL)             │
└──────────────────────────────────────────────────────┘
```

## 🎯 Microservicios

### 1. **Usuarios** (Port: 8080)
Gestiona la información de usuarios y autenticación.
- CRUD de usuarios
- Validación de credenciales
- Perfiles y roles

### 2. **Productos** (Port: 8081)
Catálogo de productos disponibles.
- Gestión del catálogo
- Información detallada de productos
- Categorías y características

### 3. **Inventario** (Port: 8082)
Control de stock y disponibilidad.
- Verificación de stock
- Actualización de inventario
- Alertas de disponibilidad

### 4. **Pedidos** (Port: 8083)
Procesamiento de pedidos de clientes.
- Creación de pedidos
- Seguimiento de estado
- Historial de compras
- Integración con otros servicios

### 5. **Pagos** (Port: 8084)
Gestión de transacciones y pagos.
- Procesamiento de pagos
- Validación de tarjetas
- Historial de transacciones

## 💻 Tecnología

| Componente | Versión |
|-----------|---------|
| Java | 11+ |
| Spring Boot | 2.x / 3.x |
| Spring Data JPA | Latest |
| OpenAPI/Swagger | 3.x |
| Docker | Latest |
| Docker Compose | Latest |
| PostgreSQL | 13+ |
| Maven | 3.6+ |

## 📋 Requisitos Previos

- **Java JDK 11+** instalado
- **Docker** instalado
- **Docker Compose** instalado
- **Maven 3.6+** (opcional, incluye mvnw)
- **Git**

### Verificar instalación:
```bash
java -version
docker --version
docker-compose --version
```

## 🚀 Instalación y Ejecución

### Opción 1: Con Docker Compose (Recomendado)

```bash
# Clonar el repositorio
git clone https://github.com/tuusuario/connectshop.git
cd connectshop

# Ejecutar todos los servicios
docker-compose up -d

# Verificar que los servicios están corriendo
docker-compose ps

# Ver logs
docker-compose logs -f
```

### Opción 2: Ejecución Local (Sin Docker)

#### Para cada microservicio:

```bash
# Ir al directorio del servicio
cd inventario/inventario

# Compilar
./mvnw clean install

# Ejecutar
./mvnw spring-boot:run

# En otras terminales, repetir para cada servicio:
# - pagos/pagos
# - pedidos/pedidos
# - productos/productos
# - usuarios/usuarios
```

### Verificar servicios activos:

```bash
curl http://localhost:8080/api/usuarios      # Usuarios
curl http://localhost:8081/api/productos     # Productos
curl http://localhost:8082/api/inventario    # Inventario
curl http://localhost:8083/api/pedidos       # Pedidos
curl http://localhost:8084/api/pagos         # Pagos
```

## 📚 Documentación API

Cada servicio expone su documentación con **Swagger/OpenAPI**. Accede a:

| Servicio | URL Swagger |
|----------|-------------|
| Usuarios | http://localhost:8080/swagger-ui.html |
| Productos | http://localhost:8081/swagger-ui.html |
| Inventario | http://localhost:8082/swagger-ui.html |
| Pedidos | http://localhost:8083/swagger-ui.html |
| Pagos | http://localhost:8084/swagger-ui.html |

### Documentación JSON (OpenAPI):

```bash
# Ejemplo para el servicio de Pedidos
curl http://localhost:8083/v3/api-docs
```

### Anotaciones Swagger Utilizadas:

- `@Tag` - Agrupa endpoints por módulo
- `@Operation` - Describe cada endpoint
- `@ApiResponse` - Define respuestas HTTP
- `@Parameter` - Documenta parámetros
- `@Schema` - Describe DTOs

## 📁 Estructura del Proyecto

```
connectshop/
├── docker-compose.yml          # Orquestación de servicios
├── README.md                   # Este archivo
├── notas.txt                   # Documentación de desarrollo
│
├── inventario/
│   └── inventario/
│       ├── pom.xml
│       ├── Dockerfile
│       ├── src/
│       │   ├── main/
│       │   │   ├── java/com/connectshopp/inventario/
│       │   │   │   ├── InventarioApplication.java
│       │   │   │   ├── config/          # Configuración (OpenAPI, RestTemplate)
│       │   │   │   ├── controller/      # Endpoints REST
│       │   │   │   ├── dto/             # Data Transfer Objects
│       │   │   │   ├── model/           # Entidades JPA
│       │   │   │   ├── repository/      # Acceso a datos
│       │   │   │   ├── service/         # Lógica de negocio
│       │   │   │   └── exception/       # Excepciones personalizadas
│       │   │   └── resources/
│       │   │       └── application.properties
│       │   └── test/
│
├── pagos/
│   └── pagos/
│       └── [Estructura similar a inventario]
│
├── pedidos/
│   └── pedidos/
│       └── [Estructura similar a inventario]
│
├── productos/
│   └── productos/
│       └── [Estructura similar a inventario]
│
├── usuarios/
│   └── usuarios/
│       └── [Estructura similar a inventario]
│
└── zmodelo_dato/               # Modelo de datos compartido
```

## ⚙️ Configuración

### Variables de Entorno

Cada servicio usa `application.properties`:

```properties
# Puerto del servicio
server.port=8082

# Base de datos
spring.datasource.url=jdbc:postgresql://localhost:5432/inventario
spring.datasource.username=postgres
spring.datasource.password=password

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

# OpenAPI/Swagger
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

### Docker Compose Configuration

```yaml
version: '3.8'
services:
  # Cada servicio con su puerto y base de datos
  inventario:
    image: connectshop/inventario:latest
    ports:
      - "8082:8082"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/inventario
```

## 🔗 Comunicación Entre Servicios

Los microservicios se comunican usando **RestTemplate**:

```java
// Ejemplo: Pedidos llamando a Inventario
@Service
public class PedidoService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    public void verificarInventario(Long productoId) {
        String url = "http://localhost:8082/api/inventario/" + productoId;
        InventarioDTO inventario = restTemplate.getForObject(url, InventarioDTO.class);
        // Validar disponibilidad
    }
}
```

## 🧪 Pruebas

### Ejecutar tests unitarios:

```bash
cd [nombre-servicio]/[nombre-servicio]
./mvnw test
```

### Pruebas manuales con cURL:

```bash
# Listar productos
curl -X GET http://localhost:8081/api/productos

# Crear un pedido
curl -X POST http://localhost:8083/api/pedidos \
  -H "Content-Type: application/json" \
  -d '{"usuarioId":1,"productoId":5,"cantidad":2}'

# Procesar pago
curl -X POST http://localhost:8084/api/pagos \
  -H "Content-Type: application/json" \
  -d '{"pedidoId":1,"monto":99.99,"metodo":"TARJETA"}'
```

## 🐳 Docker

### Construir imagen de un servicio:

```bash
cd inventario/inventario
docker build -t connectshop/inventario:latest .
```

### Construir todas las imágenes:

```bash
docker-compose build
```

### Ver contenedores corriendo:

```bash
docker ps
```

### Ver logs de un servicio:

```bash
docker-compose logs -f inventario
```

## 📊 Monitoreo y Debugging

### Acceder a la consola de logs:

```bash
docker-compose logs -f [nombre-servicio]
```

### Entrar en un contenedor:

```bash
docker exec -it connectshop_inventario_1 /bin/bash
```

### Verificar conectividad entre servicios:

```bash
docker-compose exec pedidos curl http://inventario:8082/api/inventario/1
```

## 🤝 Contribuir

1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

### Estándares de Código

- Utilizar nombres significativos en clases y métodos
- Mantener la estructura de carpetas consistente
- Documentar endpoints con Swagger
- Escribir tests para nuevas funcionalidades
- Usar DTOs para request/response

## 📝 Convenciones de Desarrollo

- **Controllers**: Una clase principal por microservicio con todos los endpoints
- **DTOs**: Clases separadas para request (`*Request.class`) y response (`*Response.class`)
- **Services**: Contienen la lógica de negocio
- **Repositories**: Acceso a datos con Spring Data JPA
- **Config**: Configuración centralizada (OpenAPI, RestTemplate, etc.)

## 🐛 Troubleshooting

### Los servicios no inician

```bash
# Verificar puertos en uso
netstat -ano | findstr :8082

# Ver logs de error
docker-compose logs [nombre-servicio]
```

### Conexión a base de datos rechazada

```bash
# Verificar que PostgreSQL está corriendo
docker-compose ps postgres

# Verificar credenciales en application.properties
```

### Swagger no aparece

```bash
# Verificar que la dependencia está en pom.xml:
# org.springdoc:springdoc-openapi-starter-webmvc-ui

# Reiniciar el servicio
./mvnw spring-boot:run
```

## 📞 Contacto y Soporte

Para reportar bugs o sugerencias:
- Abre un issue en GitHub
- Contacta al equipo de desarrollo

## 📄 Licencia

Este proyecto está licenciado bajo la Licencia MIT - ver el archivo LICENSE para detalles.

---

**Última actualización**: 23 de Junio de 2026

**Versión del Proyecto**: 1.0.0

**Estado**: En Desarrollo 🚀
