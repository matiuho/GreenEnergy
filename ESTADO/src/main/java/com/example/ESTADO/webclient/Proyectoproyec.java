package com.example.ESTADO.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class Proyectoproyec {

    // Cliente HTTP para comunicarse con el microservicio de proyectos
    private final WebClient webclient;

    // Constructor que configura WebClient con la URL base del microservicio
    public Proyectoproyec(@Value("${proyecto-service.url}") String proyectoServiceUrl) {
        this.webclient = WebClient.builder()
                .baseUrl(proyectoServiceUrl)
                .build();
    }

    // Método para realizar la consulta GET al microservicio cliente
    public Map<String, Object> getProyectoPorId(Long id) {
        return this.webclient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                response -> response.bodyToMono(String.class)
                .map(body -> new RuntimeException("Proyecto no encontrado: ")))
                .bodyToMono(Map.class).block(); // bloquea hasta que recibe respuesta
    }
}


