package com.farmacia.farmacia_api.repositorio;

import com.farmacia.farmacia_api.entidad.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepositorio extends JpaRepository<Venta, Long> {
}
