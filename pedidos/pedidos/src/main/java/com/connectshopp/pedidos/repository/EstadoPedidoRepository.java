package com.connectshopp.pedidos.repository;

import com.connectshopp.pedidos.model.EstadoPedido;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoPedidoRepository extends JpaRepository<EstadoPedido, Long> {

    Optional<EstadoPedido> findByNombreIgnoreCase(String nombre);
}
