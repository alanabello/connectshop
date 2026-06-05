package com.connectshopp.inventario.repository;

import com.connectshopp.inventario.model.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Long> {
}
