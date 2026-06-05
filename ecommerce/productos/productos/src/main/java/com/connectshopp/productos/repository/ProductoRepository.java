package com.connectshopp.productos.repository;

import com.connectshopp.productos.model.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    boolean existsBySku(String sku);

    List<Producto> findByCategoriaId(Long categoriaId);

    List<Producto> findByMarcaId(Long marcaId);

    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}
