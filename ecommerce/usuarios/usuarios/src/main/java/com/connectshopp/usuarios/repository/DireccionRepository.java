package com.connectshopp.usuarios.repository;

import com.connectshopp.usuarios.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionRepository extends JpaRepository<Direccion, Long> {
}
