package com.example.servicio.controller;

import com.example.servicio.model.Servicio;
import com.example.servicio.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    // Obtener todos los servicios
    @GetMapping
    public List<Servicio> obtenerTodos() {
        return servicioService.obtenerTodos();
    }

    // Obtener un servicio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> obtenerPorId(@PathVariable Long id) {
        try {
            //verificar si el servicio existe
            Servicio servicio = servicioService.getServicioPorId(id);
            return ResponseEntity.ok(servicio);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }   

    // Crear un nuevo servicio
    @PostMapping
    public ResponseEntity<?> crearservicio(@RequestBody Servicio servicio) {
        //Validar 
        if (servicio.getNombre().length() <1|| servicio.getNombre().length()>20) {
            return ResponseEntity.badRequest().body("El Nombre debe Contener entre 1 y 20 Caracteres");
        }
        if (servicio.getDescripcion().length() <1 || servicio.getDescripcion().length()>100) {
            return ResponseEntity.badRequest().body("La Descripcicion debe Contener entre 1 y 100 Caracteres");
        }
        if  (!servicio.getDisponibilidad().contains("Disponible") && !servicio.getDisponibilidad().contains("No Disponible")) {
            return ResponseEntity.badRequest().body("Disponibilidad Solo acepta 2 Estados 'Disponible' y 'No Disponible'");
        }
        Servicio nuevoServicio = servicioService.saveServicio(servicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoServicio);
    }

    // Actualizar un servicio existente
    @PutMapping("/{id}")
    public ResponseEntity<Servicio> actualizar(@PathVariable Long id, @RequestBody Servicio datos) {
        try {
            Servicio actualizado = servicioService.actualizar(id, datos);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un servicio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicioService.eliminarservicio(id);
        return ResponseEntity.noContent().build();
    }
}