package com.example.respuesta.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class SoporteClient {
    private final WebClient webClient;

    public SoporteClient(@Value("${soporte-service.url}") String soporteServiceUrl) {
        this.webClient = WebClient.builder().baseUrl(soporteServiceUrl).build();
    }

    public Map<String, Object> getSoporteById(Long id) {
        return this.webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                    response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new RuntimeException("Soporte no encontrada: " + body)))
                )
                .onStatus(status -> status.is5xxServerError(),
                    response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new RuntimeException("Error del servidor Soporte: " + body)))
                )
                .bodyToMono(Map.class)
                .doOnNext(body -> System.out.println("Respuesta Soporte: " + body))
                .block();
    }
}