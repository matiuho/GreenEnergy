package com.example.Contrataciones.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Contrataciones.model.Contrataciones;
import com.example.Contrataciones.repository.ContratacionesRepository;

@RestController
@RequestMapping("/api/contrataciones")
public class ContratacionesController {
    @Autowired
    private ContratacionesRepository contratacionesService;

    @GetMapping
    public ResponseEntity<List<Contrataciones>> obtenerContratacion() {
        if (Contrataciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Contrataciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrataciones> obtenerContratacionesPorId(@PathVariable Long id){
        try {
            Contrataciones contrataciones = contratacionesService
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Contrataciones> crearEstado(@RequestBody Contrataciones estado) {
        return ResponseEntity.status(201).body(contratacionesService.saveEstado(estado));

    }

}
