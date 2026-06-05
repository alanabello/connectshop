package com.connectshopp.productos.repository;

import com.connectshopp.productos.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
