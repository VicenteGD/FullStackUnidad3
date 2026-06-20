package cl.vincalia.pedidos_service.controller;

import cl.vincalia.pedidos_service.dto.PedidoDTO;
import cl.vincalia.pedidos_service.dto.PedidoRequestDTO;
import cl.vincalia.pedidos_service.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "Endpoints para la gestión integral de pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los pedidos", description = "Obtiene una lista completa de todos los pedidos registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida exitosamente")
    public List<PedidoDTO> listarPedidos() {
        return pedidoService.findAll();
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo pedido", description = "Registra un nuevo pedido asociado a un cliente.")
    @ApiResponse(responseCode = "201", description = "Pedido creado con éxito")
    public PedidoDTO crearPedido(@RequestBody PedidoRequestDTO pedidoRequest) {
        return pedidoService.create(pedidoRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener pedido por ID", description = "Busca un pedido específico mediante su identificador único.")
    @ApiResponse(responseCode = "200", description = "Pedido encontrado")
    @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    public PedidoDTO obtenerPorId(
            @Parameter(description = "ID único del pedido") @PathVariable Long id) {
        return pedidoService.findById(id);
    }
}