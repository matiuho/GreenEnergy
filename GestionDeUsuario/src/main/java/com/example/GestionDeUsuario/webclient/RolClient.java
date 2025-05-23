package com.example.GestionDeUsuario.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class RolClient {
    private final WebClient webClient;
    // metodo constructor
    public RolClient(@Value("${roles-service.url}") String rolesServiceUrl) {
        this.webClient = WebClient.builder().baseUrl(rolesServiceUrl).build();
    }
    public Map<String, Object> getRolesById(Long id) {
        return this.webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                    response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new RuntimeException("Rol no encontrado: " + body)))
                )
                .onStatus(status -> status.is5xxServerError(),
                    response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new RuntimeException("Error del servidor Rol: " + body)))
                )
                .bodyToMono(Map.class)
                .doOnNext(body -> System.out.println("Respuesta Roles: " + body))
                .block();
    }


}
