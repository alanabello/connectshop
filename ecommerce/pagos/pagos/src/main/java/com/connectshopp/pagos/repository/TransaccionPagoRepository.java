package com.connectshopp.pagos.repository;

import com.connectshopp.pagos.model.TransaccionPago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionPagoRepository extends JpaRepository<TransaccionPago, Long> {
}
