package com.connectshopp.productos.controller;

import com.connectshopp.productos.dto.ImagenProductoRequest;
import com.connectshopp.productos.model.ImagenProducto;
import com.connectshopp.productos.model.Producto;
import com.connectshopp.productos.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
@Tag(name = "Productos", description = "Operaciones para administrar catalogo de productos e imagenes")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    @Operation(summary = "Crear producto", description = "Registra un producto y lo asocia a una categoria y marca.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Producto creado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Producto.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Categoria o marca no encontrada", content = @Content)
    })
    public ResponseEntity<Producto> crear(
        @Parameter(description = "ID de la categoria", example = "1") @RequestParam Long categoriaId,
        @Parameter(description = "ID de la marca", example = "1") @RequestParam Long marcaId,
        @Valid @RequestBody Producto producto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(productoService.crear(categoriaId, marcaId, producto));
    }

    @GetMapping
    @Operation(summary = "Buscar productos", description = "Lista productos filtrando opcionalmente por categoria, marca o nombre.")
    @ApiResponse(responseCode = "200", description = "Lista de productos",
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
            array = @ArraySchema(schema = @Schema(implementation = Producto.class))))
    public ResponseEntity<List<Producto>> buscar(
        @Parameter(description = "ID de la categoria", example = "1") @RequestParam(required = false) Long categoriaId,
        @Parameter(description = "ID de la marca", example = "1") @RequestParam(required = false) Long marcaId,
        @Parameter(description = "Texto del nombre del producto", example = "Notebook") @RequestParam(required = false) String nombre
    ) {
        return ResponseEntity.ok(productoService.buscar(categoriaId, marcaId, nombre));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar producto por ID", description = "Obtiene un producto especifico por su identificador.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Producto encontrado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Producto.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
    })
    public ResponseEntity<Producto> buscarPorId(@Parameter(description = "ID del producto", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    @PostMapping("/{id}/imagenes")
    @Operation(summary = "Agregar imagen a producto", description = "Registra una imagen asociada a un producto.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Imagen registrada",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ImagenProducto.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
    })
    public ResponseEntity<ImagenProducto> agregarImagen(
        @Parameter(description = "ID del producto", example = "1") @PathVariable Long id,
        @Valid @RequestBody ImagenProductoRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.agregarImagen(id, request));
    }
}
