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
        return medicamentoRepositorio.save(medicamento);
    }

    public Medicamento actualizar(Long id, Medicamento medicamento) {
        Medicamento medicamentoExistente = buscarPorId(id);

        medicamentoExistente.setNombre(medicamento.getNombre());
        medicamentoExistente.setDescripcion(medicamento.getDescripcion());
        medicamentoExistente.setPrecio(medicamento.getPrecio());
        medicamentoExistente.setStock(medicamento.getStock());

        return medicamentoRepositorio.save(medicamentoExistente);
    }

    public void eliminar(Long id) {
        Medicamento medicamento = buscarPorId(id);
        medicamentoRepositorio.delete(medicamento);
    }
}
