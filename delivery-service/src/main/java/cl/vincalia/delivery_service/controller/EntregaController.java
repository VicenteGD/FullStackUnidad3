package cl.vincalia.delivery_service.controller;

import cl.vincalia.delivery_service.dto.EntregaDTO;
import cl.vincalia.delivery_service.dto.EntregaRequestDTO;
import cl.vincalia.delivery_service.service.EntregaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/entregas")
@RequiredArgsConstructor
public class EntregaController {

    private final EntregaService service;

    @PostMapping
    public ResponseEntity<EntregaDTO> crear(@Valid @RequestBody EntregaRequestDTO request) {
        return new ResponseEntity<>(service.crearEntrega(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<EntregaDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<EntregaDTO> actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(service.actualizarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // REPORTES
    @GetMapping("/reportes/pedido/{pedidoId}")
    public ResponseEntity<List<EntregaDTO>> getByPedido(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(service.findByPedido(pedidoId));
    }

    @GetMapping("/reportes/estado")
    public ResponseEntity<List<EntregaDTO>> getByEstado(@RequestParam String estado) {
        return ResponseEntity.ok(service.findByEstado(estado));
    }

    @GetMapping("/reportes/repartidor")
    public ResponseEntity<List<EntregaDTO>> getByRepartidor(@RequestParam String repartidor) {
        return ResponseEntity.ok(service.findByRepartidor(repartidor));
    }

    @GetMapping("/reportes/fecha-creacion")
    public ResponseEntity<List<EntregaDTO>> getByFechaCreacion(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(service.findByFechaCreacionRange(inicio, fin));
    }

    @GetMapping("/reportes/fecha-estimada")
    public ResponseEntity<List<EntregaDTO>> getByFechaEstimada(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(service.findByFechaEstimadaRange(inicio, fin));
    }
}
