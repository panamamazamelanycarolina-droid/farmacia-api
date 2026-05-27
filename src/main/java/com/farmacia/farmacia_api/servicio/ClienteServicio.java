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
        return clienteRepositorio.save(cliente);
    }

    public Cliente actualizar(Long id, Cliente cliente) {
        Cliente clienteExistente = buscarPorId(id);

        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setCedula(cliente.getCedula());
        clienteExistente.setTelefono(cliente.getTelefono());
        clienteExistente.setCorreo(cliente.getCorreo());

        return clienteRepositorio.save(clienteExistente);
    }

    public void eliminar(Long id) {
        Cliente cliente = buscarPorId(id);
        clienteRepositorio.delete(cliente);
    }
}