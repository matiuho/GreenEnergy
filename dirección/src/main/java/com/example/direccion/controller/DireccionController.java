package com.example.direccion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
            //retorno codigo 204
             return ResponseEntity.noContent().build();
         }
         return ResponseEntity.ok(direcciones);
    }

    //endpoint para crear una nueva direccion
    @PostMapping
    public ResponseEntity<?> crearDireccion(@RequestBody Direccion nuevaDireccion) {
        try {
            Direccion direccion = direccionService.saveProyecto(nuevaDireccion);
            return ResponseEntity.status(201).body(direccion);
        } catch (RuntimeException e) {
            //retorno codigo 404
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }


}
