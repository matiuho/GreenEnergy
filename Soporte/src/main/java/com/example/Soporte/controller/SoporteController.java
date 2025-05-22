package com.example.Soporte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Soporte.model.Soporte;
import com.example.Soporte.service.SoporteService;

@RestController
@RequestMapping("/api/soporte")
public class SoporteController {
    @Autowired
    private SoporteService soporteService;
    // Endpoint para obtener todos los soportes
    @GetMapping
    public ResponseEntity<List<Soporte>> obtenerSoportes() {
        List<Soporte> soportes = soporteService.obtenerTodos();
        if (soportes.isEmpty()) {
            return ResponseEntity.noContent().build();
            
        }
        return ResponseEntity.ok(soportes);
    }

    @PostMapping
    public ResponseEntity<?> crearSoporte(@RequestBody Soporte nuevoSoporte){
        try {
            Soporte soporte = soporteService.guardarSoporte(nuevoSoporte);
            return ResponseEntity.status(HttpStatus.CREATED).body(soporte);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body( e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(" Error interno: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Soporte> obtenerSoportePorId(@PathVariable Long id) {
        try {
            Soporte soporte = soporteService.getEstadoPorId(id);
            return ResponseEntity.ok(soporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
