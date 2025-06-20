package com.example.Resena.WebClient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class UserClient {
    private final WebClient webClient;

    // metodo constructor
    public UserClient(@Value("${usuario-service.url}") String usuarioServiceUrl) {
        this.webClient = WebClient.builder().baseUrl(usuarioServiceUrl).build();
    }
    // metodo para realizar la consulta al microservicio de servicio
    public Map<String, Object> getUsuarioById(Long id) {
        return webClient.get()
                .uri("/{id}", id) 
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(new RuntimeException("Usuario no encontrado: " + body))))
                .bodyToMono(Map.class)
                .block();
    }

}
