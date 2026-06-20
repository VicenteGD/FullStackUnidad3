package cl.vincalia.catalogo_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "cl.carrito.catalogo_service.client")
public class CatalogoServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CatalogoServiceApplication.class, args);
	}
}
