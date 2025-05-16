package com.example.Proyecto.webclient;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.Value;

@Component
public class ClienteClient {
    //variable de comunicación
    private final WebClient webClient;

    //metodo constructor 
    public ClienteClient(@Value("${estado-service.url}") String estadoServiceUrl){
        this.webClient = webClient.builder().baseUrl(estadoServiceUrl).build();
    }
    //metodo para realizar la consulta getmaping(/{id}) al microservicio estado
    public Map<String, Object> getEstadoById(Long id){
        return this.webClient.get()
        .uri("/{id}", id)
        .retrieve()
        .onStatus(status -> status.is4xxClientError(),
        response -> response.bodyToMono(String.class)
        .map(body -> new RuntimeException("Estado no encontrado")))
        .bodyToMono(Map.class).block();
    }

    
}
