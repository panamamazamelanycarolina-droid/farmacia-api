package com.farmacia.farmacia_api.servicio;

import com.farmacia.farmacia_api.entidad.Cliente;
import com.farmacia.farmacia_api.repositorio.ClienteRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicio {

    private final ClienteRepositorio clienteRepositorio;

    public ClienteServicio(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    public List<Cliente> listar() {
        return clienteRepositorio.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public Cliente guardar(Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().isBlank()) {
            throw new RuntimeException("El nombre del cliente es obligatorio");
        }

        if (cliente.getCedula() == null || cliente.getCedula().isBlank()) {
            throw new RuntimeException("La cédula es obligatoria");
        }

        return clienteRepositorio.save(cliente);
    }
}