package com.example.Contrataciones.webclient;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
               

@Component
public class ServiceClient {
    //variable de comunicación
    private final WebClient webClient;

    //metodo constructor 
    public ServiceClient(@Value("${estado-service.url}") String estadoServiceUrl) {
        this.webClient = WebClient.builder().baseUrl(estadoServiceUrl).build();
    }
    //metodo para realizar la consulta  al microservicio estado y al usuario
    public Map<String, Object> getEstadoById(Long id){
        return this.webClient.get()
        .uri("/{id}", id)
        .retrieve()
        .onStatus(status -> status.is4xxClientError(),
        response -> response.bodyToMono(String.class)
        .map(body -> new RuntimeException("Estado no encontrado")))
        .bodyToMono(Map.class)
        .doOnNext(body -> System.out.println("Respuesta estado: " + body))
        .block();

    }

    


    
}