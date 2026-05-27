package com.farmacia.farmacia_api.repositorio;

import com.farmacia.farmacia_api.entidad.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
}