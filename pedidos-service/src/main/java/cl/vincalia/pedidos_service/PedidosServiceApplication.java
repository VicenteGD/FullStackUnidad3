package cl.vincalia.pedidos_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
		info = @Info(
				title = "API de Pedidos - FullStack Unidad 3",
				description = "Microservicio encargado de gestionar la creación, consulta y actualización de los pedidos de los clientes.",
				version = "1.0.0",
				contact = @Contact(
						name = "Vicho",
						email = "vicho@tu-correo.com"
				)
		)
)
@SpringBootApplication
@EnableFeignClients // Dejo esto por si ya lo tenías para comunicarte con otros servicios
public class PedidosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidosServiceApplication.class, args);
	}

}