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

@RestController
@RequestMapping("/api/proyecto")
public class ProyectoController {
    @Autowired
    private ProyectosService proyectosService;

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
        } catch (Exception e) {
            // Retorno código 404 Not Found si el estado no existe
            return ResponseEntity.status(404).body(e.getMessage());
        }
        
    }

}
