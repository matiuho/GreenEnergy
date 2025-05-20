package com.example.Proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Proyecto.model.Proyecto;
import com.example.Proyecto.service.ProyectosService;
import com.example.Proyecto.webclient.ClienteClient;

@RestController
@RequestMapping("/api/proyecto")
public class ProyectoController {
    @Autowired
    private   ProyectosService proyectosService;

    @Autowired
    private ClienteClient clienteClient; // Inyección del WebClient para Estado


    @GetMapping
    public ResponseEntity<List<Proyecto>> obtenerProyectos() {
        List<Proyecto> proyecto = proyectosService.getProyectos();
        if (proyecto.isEmpty()) {
            // Retorno código 204 No Content
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(proyecto);
    }

    //endpoint para crear un nuevo proyecto
    
    @PostMapping
    public ResponseEntity<?> crearProyecto(@RequestBody Proyecto nuevoproyecto) {
        try {
            Proyecto proyecto = proyectosService.saveProyecto(nuevoproyecto);
            return ResponseEntity.status(201).body(proyecto);
        } catch (RuntimeException e) {
        // Captura error por estado no encontrado
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
        // Otro error interno
             return ResponseEntity.status(404).body(e.getMessage());
        }
    }


}
