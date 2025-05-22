package com.example.Contrataciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Contrataciones.model.Contrataciones;
import com.example.Contrataciones.service.ContratacionesService;
import com.example.Contrataciones.webclient.ClienteClient;
import com.example.Contrataciones.webclient.DireccionClient;
import com.example.Contrataciones.webclient.ProyectClient;

@RestController
@RequestMapping("/api/contrataciones")
public class ContratacionesController {
    @Autowired
    private ContratacionesService contratacionesService;

    @Autowired ClienteClient clienteClient;

    @Autowired DireccionClient direccionClient;

    @Autowired ProyectClient proyectClient;

    @GetMapping
    public ResponseEntity<List<Contrataciones>> obtenerContrataciones() {
        List<Contrataciones> contrataciones = contratacionesService.getContrataciones();
        if (contrataciones.isEmpty()) {
            // Retorno código 204 No Content
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contrataciones);
    }
    //endpoint para crear un nuevo proyecto
    @PostMapping
    public ResponseEntity<?> crearContrataciones(@RequestBody Contrataciones nuevoproyecto) {
        try {
            Contrataciones contrataciones = contratacionesService.saveContrataciones(nuevoproyecto);
            return ResponseEntity.status(201).body(contrataciones);
        } catch (RuntimeException e) {
            // Captura error por estado no encontrado
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            // Otro error interno
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

}
