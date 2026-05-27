package com.farmacia.farmacia_api.controlador;

import com.farmacia.farmacia_api.entidad.Medicamento;
import com.farmacia.farmacia_api.servicio.MedicamentoServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
@CrossOrigin(origins = "*")
public class MedicamentoControlador {

    private final MedicamentoServicio medicamentoServicio;

    public MedicamentoControlador(MedicamentoServicio medicamentoServicio) {
        this.medicamentoServicio = medicamentoServicio;
    }

    @GetMapping
    public List<Medicamento> listar() {
        return medicamentoServicio.listar();
    }

    @GetMapping("/{id}")
    public Medicamento buscarPorId(@PathVariable Long id) {
        return medicamentoServicio.buscarPorId(id);
    }

    @PostMapping
    public Medicamento guardar(@RequestBody Medicamento medicamento) {
        return medicamentoServicio.guardar(medicamento);
    }

    @PutMapping("/{id}")
    public Medicamento actualizar(@PathVariable Long id, @RequestBody Medicamento medicamento) {
        return medicamentoServicio.actualizar(id, medicamento);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        medicamentoServicio.eliminar(id);
    }
}