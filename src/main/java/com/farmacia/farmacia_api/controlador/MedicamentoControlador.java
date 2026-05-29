package com.farmacia.farmacia_api.controlador;

import com.farmacia.farmacia_api.entidad.Medicamento;
import com.farmacia.farmacia_api.servicio.MedicamentoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Medicamento>> listar() {
        return ResponseEntity.ok(medicamentoServicio.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicamentoServicio.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Medicamento> guardar(@RequestBody Medicamento medicamento) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(medicamentoServicio.guardar(medicamento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> actualizar(
            @PathVariable Long id,
            @RequestBody Medicamento medicamento
    ) {
        return ResponseEntity.ok(medicamentoServicio.actualizar(id, medicamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        medicamentoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}