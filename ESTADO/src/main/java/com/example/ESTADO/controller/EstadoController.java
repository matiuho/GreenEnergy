package com.example.ESTADO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ESTADO.model.Estado;
import com.example.ESTADO.service.EstadoService;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {
    @Autowired
    private EstadoService estadoService;

    //endpoint para buscar todos los estados
    @GetMapping
    public ResponseEntity<List<Estado>> obtenerEstado(){
        List<Estado> estados = estadoService.getEstado();
        if (estados.isEmpty()) {
            return ResponseEntity.noContent().build(); // devuelve 204 No Content si no hay estados
        }
        return ResponseEntity.ok(estados); // devuelve 200 OK con la lista de estados
    }

    
    //endpoint para crear un nuevo estado
    @PostMapping
    public ResponseEntity<?> crearEstado(@RequestBody Estado nuevoEstado) {
        try {
            Estado estado = estadoService.saveEstado(nuevoEstado);
            return ResponseEntity.status(201).body(estado); // devuelve 201 Created con el estado creado
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage()); 
        }
    }



}
