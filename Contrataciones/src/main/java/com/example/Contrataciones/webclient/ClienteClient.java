package com.example.Contrataciones.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ClienteClient {
    private final WebClient webClient;
    //metodo constructor 
    public ClienteClient(@Value("${servicio-service.url}") String servicioServiceUrl) {
        this.webClient = WebClient.builder().baseUrl(servicioServiceUrl).build();
    }
    //metodo para realizar la consulta  al microservicio de servicio
    public Map<String, Object> getServicioById(Long id){
        return this.webClient.get()
        .uri("/{id}", id)
        .retrieve()
        .onStatus(status -> status.is4xxClientError(),
        response -> response.bodyToMono(String.class)
        .map(body -> new RuntimeException("Servicio no encontrado")))
        .bodyToMono(Map.class)
        .doOnNext(body -> System.out.println("Respuesta Servicio: " + body))
        .block();

    }
}
