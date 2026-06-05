package com.connectshopp.inventario.repository;

import com.connectshopp.inventario.model.Inventario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    Optional<Inventario> findByProductoId(Long productoId);

    boolean existsByProductoId(Long productoId);

    @Query("select i from Inventario i where i.stockActual <= i.stockMinimo")
    List<Inventario> findAlertasStockMinimo();
}
