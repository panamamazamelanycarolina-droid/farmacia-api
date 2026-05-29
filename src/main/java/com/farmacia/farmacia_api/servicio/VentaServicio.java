package com.farmacia.farmacia_api.servicio;

import com.farmacia.farmacia_api.entidad.Cliente;
import com.farmacia.farmacia_api.entidad.Medicamento;
import com.farmacia.farmacia_api.entidad.Venta;
import com.farmacia.farmacia_api.repositorio.ClienteRepositorio;
import com.farmacia.farmacia_api.repositorio.MedicamentoRepositorio;
import com.farmacia.farmacia_api.repositorio.VentaRepositorio;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VentaServicio {

    private final VentaRepositorio ventaRepositorio;
    private final ClienteRepositorio clienteRepositorio;
    private final MedicamentoRepositorio medicamentoRepositorio;

    public VentaServicio(
            VentaRepositorio ventaRepositorio,
            ClienteRepositorio clienteRepositorio,
            MedicamentoRepositorio medicamentoRepositorio
    ) {
        this.ventaRepositorio = ventaRepositorio;
        this.clienteRepositorio = clienteRepositorio;
        this.medicamentoRepositorio = medicamentoRepositorio;
    }

    public List<Venta> listar() {
        return ventaRepositorio.findAll();
    }

    public Venta buscarPorId(Long id) {
        return ventaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    public List<Venta> listarPorMedicamento(Long medicamentoId) {
        return ventaRepositorio.findByMedicamentoId(medicamentoId);
    }

    public Venta guardar(Venta venta) {
        if (venta.getCantidad() == null || venta.getCantidad() <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor a cero");
        }

        Cliente cliente = clienteRepositorio.findById(venta.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Medicamento medicamento = medicamentoRepositorio.findById(venta.getMedicamento().getId())
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));

        if (medicamento.getStock() < venta.getCantidad()) {
            throw new RuntimeException("Stock insuficiente");
        }

        double total = medicamento.getPrecio() * venta.getCantidad();

        medicamento.setStock(medicamento.getStock() - venta.getCantidad());
        medicamentoRepositorio.save(medicamento);

        venta.setFecha(LocalDate.now());
        venta.setTotal(total);
        venta.setCliente(cliente);
        venta.setMedicamento(medicamento);

        return ventaRepositorio.save(venta);
    }
}