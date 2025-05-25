package com.example.direccion.controller;

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

import com.example.direccion.model.Direccion;
import com.example.direccion.service.DireccionService;

@RestController
@RequestMapping("/api/direccion")
public class DireccionController {
    @Autowired
    private DireccionService direccionService;

    @GetMapping
    public ResponseEntity<List<Direccion>> obtenerDirecciones() {
        List<Direccion> direcciones = direccionService.getDirecciones();
        if (direcciones.isEmpty()) {
            // retorno codigo 204
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(direcciones);
    }

    // endpoint para crear una nueva direccion
    @PostMapping
    public ResponseEntity<?> crearDireccion(@RequestBody Direccion nuevaDireccion) {
        if (nuevaDireccion.getNombre().length() < 5 || nuevaDireccion.getNombre().length() > 50) {
            return ResponseEntity.badRequest().body("El Nombre debe Contener entre 5 y 50 Caracteres");
        }
        try {
            Direccion direccion = direccionService.saveDireccion(nuevaDireccion);
            return ResponseEntity.status(HttpStatus.CREATED).body(direccion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(" Error interno: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Direccion> obtenerDireccionPorId(@PathVariable Long id) {
        try {
            // verificar si existe la comuna
            // si no existe, se lanza una excepcion
            Direccion direccion = direccionService.obtenerDireccionPorId(id);
            return ResponseEntity.ok(direccion);
        } catch (Exception e) {
            // retorno codigo 404
            return ResponseEntity.notFound().build();
        }
    }

}
