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

    // Obtener todos los servicios activos
    @GetMapping
    public ResponseEntity<List<Servicio>> obtenerServiciosActivos() {
        List<Servicio> activos = servicioService.listarServiciosActivos();
        return activos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(activos);
    }

    // Obtener un servicio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> obtenerPorId(@PathVariable Long id) {
        try {
            // verificar si el servicio existe
            Servicio servicio = servicioService.getServicioPorId(id);
            return ResponseEntity.ok(servicio);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo servicio
    @PostMapping
    public ResponseEntity<?> crearservicio(@RequestBody Servicio servicio) {
        // Validar
        if (servicio.getNombre().length() < 1 || servicio.getNombre().length() > 20) {
            return ResponseEntity.badRequest().body("El Nombre debe Contener entre 1 y 20 Caracteres");
        }
        if (servicio.getDescripcion().length() < 1 || servicio.getDescripcion().length() > 100) {
            return ResponseEntity.badRequest().body("La Descripcicion debe Contener entre 1 y 100 Caracteres");
        }
        Servicio nuevoServicio = servicioService.saveServicio(servicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoServicio);
    }

    // Actualizar un servicio existente
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDireccion(@PathVariable Long id, @RequestBody Servicio servicioActualizada) {
        try {
            // verificar si existe la servicio
            Servicio servicio = servicioService.getServicioPorId(id);
            // actualizar los campos del servicio
            servicio.setNombre(servicioActualizada.getNombre());
            servicio.setDescripcion(servicioActualizada.getDescripcion());
            servicio.setPrecio(servicioActualizada.getPrecio());
            // guardar el servicio actualizado
            Servicio updatedDireccion = servicioService.saveServicio(servicio);
            return ResponseEntity.ok(updatedDireccion);
        } catch (Exception e) {
            // retorno codigo 404
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un servicio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicioService.eliminarservicio(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<?> desactivarServicio(@PathVariable Long id) {
        try {
            Servicio servicioDesactivado = servicioService.desactivarServicio(id);
            return ResponseEntity.ok(servicioDesactivado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("No se pudo desactivar: " + e.getMessage());
        }
    }


    @PutMapping("/activar/{idServicio}")
    public ResponseEntity<?> activarServicio(@PathVariable("idServicio") Long id) {
        try {
            Servicio activado = servicioService.activarServicio(id);
            return ResponseEntity.ok(activado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("No se pudo activar: " + e.getMessage());
        }
    }

}