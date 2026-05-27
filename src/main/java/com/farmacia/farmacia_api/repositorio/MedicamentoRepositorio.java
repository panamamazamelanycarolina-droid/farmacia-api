package com.farmacia.farmacia_api.repositorio;

import com.farmacia.farmacia_api.entidad.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepositorio extends JpaRepository<Medicamento, Long> {
}