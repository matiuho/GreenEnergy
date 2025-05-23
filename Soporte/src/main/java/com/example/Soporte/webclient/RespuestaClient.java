package com.example.Soporte.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class RespuestaClient {

    //conexion
    private final WebClient  webClient;
    
    // metodo constructor
    public RespuestaClient(@Value("${respuesta-service.url}") String respuestaServiceUrl) {
        this.webClient = WebClient.builder().baseUrl(respuestaServiceUrl).build();
    }

    public Map<String, Object> getRespuestaById(Long id) {
        return this.webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                    response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new RuntimeException("Respuesta no encontrado: " + body)))
                )
                .onStatus(status -> status.is5xxServerError(),
                    response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new RuntimeException("Error del servidor Respuesta: " + body)))
                )
                .bodyToMono(Map.class)
                .doOnNext(body -> System.out.println("Respuesta: " + body))
                .block();
    }

}
