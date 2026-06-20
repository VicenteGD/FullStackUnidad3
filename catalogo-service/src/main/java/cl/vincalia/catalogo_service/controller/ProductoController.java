package cl.vincalia.catalogo_service.controller;



import cl.vincalia.catalogo_service.dto.ProductoDTO;
import cl.vincalia.catalogo_service.dto.ProductoRequestDTO;
import cl.vincalia.catalogo_service.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService service;

    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@Valid @RequestBody ProductoRequestDTO request) {
        return new ResponseEntity<>(service.crear(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequestDTO request) {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ========== REPORTES ==========
    @GetMapping("/reportes/disponibles")
    public ResponseEntity<List<ProductoDTO>> getDisponibles() {
        return ResponseEntity.ok(service.findDisponibles());
    }

    @GetMapping("/reportes/categoria")
    public ResponseEntity<List<ProductoDTO>> getByCategoria(@RequestParam String categoria) {
        return ResponseEntity.ok(service.findByCategoria(categoria));
    }

    @GetMapping("/reportes/nombre")
    public ResponseEntity<List<ProductoDTO>> getByNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(service.findByNombreContaining(nombre));
    }

    @GetMapping("/reportes/rango-precio")
    public ResponseEntity<List<ProductoDTO>> getByPrecioRange(
            @RequestParam(required = false) Double min,
            @RequestParam(required = false) Double max) {
        return ResponseEntity.ok(service.findByPrecioRange(min, max));
    }

    @GetMapping("/reportes/stock-bajo")
    public ResponseEntity<List<ProductoDTO>> getStockBajo(@RequestParam Integer limite) {
        return ResponseEntity.ok(service.findStockBajo(limite));
    }
}
