package com.example.respuesta.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.respuesta.model.Respuesta;
import com.example.respuesta.service.RespuestaService;

@RestController
@RequestMapping("/api/respuesta")
public class RespuestaController {
    @Autowired
    private RespuestaService respuestaService;

    // Obtener todas las respuetas
    @GetMapping
    public ResponseEntity<List<Respuesta>> obtenerRespuestas() {
        List<Respuesta> respuesta = respuestaService.obtenerRespuestas();
        if (respuesta.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(respuesta);
    }

    // Obtener una respuesta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Respuesta> obtenerPorId(@PathVariable Long id) {
        try {
            // verificar si la respuesta existe
            Respuesta respuesta = respuestaService.getRespuestaPorId(id);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una respuesta
    @PostMapping
    public ResponseEntity<?> crearservicio(@RequestBody Respuesta nuevarespuesta) {
        // Definir los tipos de usuario permitidos
        Set<String> tiposPermitidos = Set.of("Administrador", "Tecnico Instalador", "Coordinador", "Cliente","Soporte");

        // Validar si el tipo de usuario no está en el conjunto
        if (!tiposPermitidos.contains(nuevarespuesta.getTipousuario())) {
            return ResponseEntity.badRequest().body(
                    "Solo se aceptan estos Tipos de Usuarios\n'Administrador', 'Tecnico Instalador', 'Coordinador', 'Cliente','Soporte'");
        }

        try {
            Respuesta respuesta = respuestaService.saveRespuesta(nuevarespuesta);
            return ResponseEntity.status(201).body(respuesta);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Actualizar un servicio existente
    @PutMapping("/{id}")
    public ResponseEntity<Respuesta> actualizar(@PathVariable Long id, @RequestBody Respuesta actdatos) {
        try {
            Respuesta actualizado = respuestaService.actualizar(id, actdatos);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un servicio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        respuestaService.eliminarrespuesta(id);
        return ResponseEntity.noContent().build();
    }

}
