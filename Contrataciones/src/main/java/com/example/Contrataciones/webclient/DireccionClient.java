package com.example.Contrataciones.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class DireccionClient {
    private final WebClient webClient;

    public DireccionClient(@Value("${direccion-service.url}") String direccionServiceUrl) {
        this.webClient = WebClient.builder().baseUrl(direccionServiceUrl).build();
    }

    public Map<String, Object> getDireccionById(Long id) {
        return this.webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                    response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new RuntimeException("Dirección no encontrada: " + body)))
                )
                .onStatus(status -> status.is5xxServerError(),
                    response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new RuntimeException("Error del servidor Dirección: " + body)))
                )
                .bodyToMono(Map.class)
                .doOnNext(body -> System.out.println("Respuesta Dirección: " + body))
                .block();
    }
}