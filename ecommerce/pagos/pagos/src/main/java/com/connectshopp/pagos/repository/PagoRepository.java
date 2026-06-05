package com.connectshopp.pagos.repository;

import com.connectshopp.pagos.model.Pago;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Long> {

    List<Pago> findByPedidoId(Long pedidoId);

    List<Pago> findByEstadoIgnoreCase(String estado);
}
