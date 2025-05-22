package com.example.direccion.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class ClienteClient {
    // variable con la que se comunica
    private final WebClient webClient;

    // metodo constructor
    public ClienteClient(@Value("${usuario-service.url}") String  direccionServiceUrl) {
        this.webClient = WebClient.builder().baseUrl(direccionServiceUrl).build();
    }

    public Map<String, Object> getUsuarioById(Long id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                response -> response.bodyToMono(String.class)
                .flatMap(body -> Mono.error(new RuntimeException("Usuario no encontrado: " + body))))    
                .bodyToMono(Map.class)
                .doOnNext(body -> System.out.println("Respuesta usuario: " + body))
                .block();
    }



}
