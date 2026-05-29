package com.farmacia.farmacia_api.repositorio;

import com.farmacia.farmacia_api.entidad.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepositorio extends JpaRepository<Venta, Long> {

    List<Venta> findByMedicamentoId(Long medicamentoId);
}