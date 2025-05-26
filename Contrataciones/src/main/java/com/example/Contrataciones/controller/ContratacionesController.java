package com.example.Contrataciones.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Contrataciones.model.Contrataciones;
import com.example.Contrataciones.service.ContratacionesService;
import com.example.Contrataciones.webclient.ClienteClient;
import com.example.Contrataciones.webclient.DireccionClient;

@RestController
@RequestMapping("/api/contrataciones")
public class ContratacionesController {
    @Autowired
    private ContratacionesService contratacionesService;

    @Autowired
    ClienteClient clienteClient;

    @Autowired
    DireccionClient direccionClient;

    @GetMapping
    public ResponseEntity<List<Contrataciones>> obtenerContrataciones() {
        List<Contrataciones> contrataciones = contratacionesService.getContrataciones();
        if (contrataciones.isEmpty()) {
            // Retorno código 204 No Content
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contrataciones);
    }

    // endpoint para crear un nuevo proyecto
    @PostMapping
    public ResponseEntity<?> crearContrataciones(@RequestBody Contrataciones nuevContrataciones) {
        LocalDate DESDE = LocalDate.of(2025, 5, 27);
        LocalDate despues = LocalDate.of(2025, 5, 28);
        if (nuevContrataciones.getFechaContratacion().isBefore(DESDE)) {
            return ResponseEntity.badRequest()
                    .body("La fecha de contratación debe ser igual o posterior al 27 de mayo de 2025.");
        }

        if (nuevContrataciones.getFechaInicio().isBefore(DESDE)) {
            return ResponseEntity.badRequest()
                    .body("La fecha de inicio debe ser posterior al 27 de mayo de 2025.");
        }

        if (nuevContrataciones.getFechaFin().isBefore(despues)) {
            return ResponseEntity.badRequest()
                    .body("La fecha de finalización debe ser posterior al 28 de mayo de 2025.");
        }
        try {
            Contrataciones contrataciones = contratacionesService.saveContrataciones(nuevContrataciones);
            return ResponseEntity.status(201).body(contrataciones);
        } catch (RuntimeException e) {
            // Captura error por estado no encontrado
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // endpoint para buscar una contratacion mediante su id
    @GetMapping("/{id}")
    public ResponseEntity<Contrataciones> obtenercontratacionPorId(@PathVariable Long id) {
        try {
            // verificar si existe el estado
            Contrataciones contratacion = contratacionesService.getContrtacionPorId(id);
            return ResponseEntity.ok(contratacion);
        } catch (Exception e) {
            // retorno codigo 404
            return ResponseEntity.notFound().build();
        }

    }

}
