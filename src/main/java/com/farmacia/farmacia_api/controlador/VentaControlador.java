package com.farmacia.farmacia_api.controlador;

import com.farmacia.farmacia_api.entidad.Venta;
import com.farmacia.farmacia_api.servicio.VentaServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaControlador {

    private final VentaServicio ventaServicio;

    public VentaControlador(VentaServicio ventaServicio) {
        this.ventaServicio = ventaServicio;
    }

    @GetMapping
    public ResponseEntity<List<Venta>> listar() {
        return ResponseEntity.ok(ventaServicio.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ventaServicio.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Venta> guardar(@RequestBody Venta venta) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ventaServicio.guardar(venta));
    }

    @GetMapping("/medicamento/{medicamentoId}")
    public ResponseEntity<List<Venta>> listarPorMedicamento(
            @PathVariable Long medicamentoId
    ) {
        return ResponseEntity.ok(ventaServicio.listarPorMedicamento(medicamentoId));
    }
}