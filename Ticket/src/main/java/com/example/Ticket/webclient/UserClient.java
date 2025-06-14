package com.example.Ticket.webclient;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class UserClient {

    //conexion 
    private final WebClient  webClient;

    // metodo constructor
    public UserClient(@Value("${usuario-service.url}") String usuarioServiceUrl) {
        this.webClient = WebClient.builder().baseUrl(usuarioServiceUrl).build();
    }

    public Map<String, Object> getUsuarioById(Long id) {
        return this.webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                    response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new RuntimeException("Usuario no encontrado: " + body)))
                )
                .onStatus(status -> status.is5xxServerError(),
                    response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new RuntimeException("Error del servidor Usuario: " + body)))
                )
                .bodyToMono(Map.class)
                .doOnNext(body -> System.out.println("Respuesta Usuario: " + body))
                .block();
    }

}
