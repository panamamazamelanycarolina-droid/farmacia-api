package com.farmacia.farmacia_api.servicio;

import com.farmacia.farmacia_api.entidad.Medicamento;
import com.farmacia.farmacia_api.repositorio.MedicamentoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoServicio {

    private final MedicamentoRepositorio medicamentoRepositorio;

    public MedicamentoServicio(MedicamentoRepositorio medicamentoRepositorio) {
        this.medicamentoRepositorio = medicamentoRepositorio;
    }

    public List<Medicamento> listar() {
        return medicamentoRepositorio.findAll();
    }

    public Medicamento buscarPorId(Long id) {
        return medicamentoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));
    }

    public Medicamento guardar(Medicamento medicamento) {
        validarMedicamento(medicamento);
        return medicamentoRepositorio.save(medicamento);
    }

    public Medicamento actualizar(Long id, Medicamento medicamento) {
        validarMedicamento(medicamento);

        Medicamento existente = buscarPorId(id);
        existente.setNombre(medicamento.getNombre());
        existente.setDescripcion(medicamento.getDescripcion());
        existente.setPrecio(medicamento.getPrecio());
        existente.setStock(medicamento.getStock());

        return medicamentoRepositorio.save(existente);
    }

    public void eliminar(Long id) {
        Medicamento existente = buscarPorId(id);
        medicamentoRepositorio.delete(existente);
    }

    private void validarMedicamento(Medicamento medicamento) {
        if (medicamento.getNombre() == null || medicamento.getNombre().isBlank()) {
            throw new RuntimeException("El nombre del medicamento es obligatorio");
        }

        if (medicamento.getPrecio() == null || medicamento.getPrecio() < 0) {
            throw new RuntimeException("El precio no puede ser negativo");
        }

        if (medicamento.getStock() == null || medicamento.getStock() < 0) {
            throw new RuntimeException("El stock no puede ser negativo");
        }
    }
}