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

    public Venta guardar(Venta venta) {
        Cliente cliente = clienteRepositorio.findById(venta.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Medicamento medicamento = medicamentoRepositorio.findById(venta.getMedicamento().getId())
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));

        if (medicamento.getStock() < venta.getCantidad()) {
            throw new RuntimeException("Stock insuficiente");
        }

        Double total = medicamento.getPrecio() * venta.getCantidad();

        medicamento.setStock(medicamento.getStock() - venta.getCantidad());
        medicamentoRepositorio.save(medicamento);

        venta.setCliente(cliente);
        venta.setMedicamento(medicamento);
        venta.setFecha(LocalDate.now());
        venta.setTotal(total);

        return ventaRepositorio.save(venta);
    }

    public void eliminar(Long id) {
        Venta venta = buscarPorId(id);
        ventaRepositorio.delete(venta);
    }
}