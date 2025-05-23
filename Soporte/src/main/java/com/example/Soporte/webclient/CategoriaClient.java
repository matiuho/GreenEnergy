package com.example.Soporte.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class CategoriaClient {

    //conexion
    private final WebClient webClient;

    // metodo constructor
    public CategoriaClient(@Value("${categoria-service.url}") String categoriaServiceUrl) {
        this.webClient = WebClient.builder().baseUrl(categoriaServiceUrl).build();
    }

    public Map<String, Object> getCategoriaById(Long id) {
        return this.webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                    response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new RuntimeException("Categoria no encontrado: " + body)))
                )
                .onStatus(status -> status.is5xxServerError(),
                    response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new RuntimeException("Error del servidor Categoria: " + body)))
                )
                .bodyToMono(Map.class)
                .doOnNext(body -> System.out.println("Respuesta Categoria: " + body))
                .block();
    }

}
