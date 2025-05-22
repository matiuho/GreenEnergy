package com.example.Contrataciones.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class ClienteClient {
    private final WebClient webClient;

    // metodo constructor
    public ClienteClient(@Value("${servicio-service.url}") String servicioServiceUrl) {
        this.webClient = WebClient.builder().baseUrl(servicioServiceUrl).build();
    }

    // metodo para realizar la consulta al microservicio de servicio
    public Map<String, Object> getServicioById(Long id) {
        System.out.println("🧪 ID del servicio en clienteClient: " + id);
        return webClient.get()
                .uri("/{id}", id) // ⚠️ Usa esto exactamente
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(new RuntimeException("Servicio no encontrado: " + body))))
                .bodyToMono(Map.class)
                .block();
    }

}
