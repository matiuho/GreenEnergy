package com.example.Proyecto.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ContratacionClient {
    private final WebClient webClient;

    //metodo constructor 
    public ContratacionClient(@Value("${contratacion-service.url}") String contratacionServiceUrl) {
        this.webClient = WebClient.builder().baseUrl(contratacionServiceUrl).build();
    }

    //metodo para realizar la consulta  al microservicio estado y al contratacion
    public Map<String, Object> getContratacionById(Long id){
        return this.webClient.get()
        .uri("/{id}", id)
        .retrieve()
        .onStatus(status -> status.is4xxClientError(),
        response -> response.bodyToMono(String.class)
        .map(body -> new RuntimeException("Contratación no encontrada")))
        .bodyToMono(Map.class)
        .doOnNext(body -> System.out.println("Respuesta contratación: " + body))
        .block();

    }

    


}
