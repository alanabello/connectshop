package com.connectshopp.pedidos.repository;

import com.connectshopp.pedidos.model.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
}
