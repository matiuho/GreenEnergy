package com.example.direccion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.direccion.model.Comuna;
import com.example.direccion.service.ComunaService;

@RestController
@RequestMapping("/api/comuna")
public class ComunaController {
    @Autowired
    private ComunaService comunaService;


    //endpoint para buscar todas las comunas
    @GetMapping
    public ResponseEntity<List<Comuna>> obtenercomunas() {
        List<Comuna> comunas = comunaService.getComunas();
        if (comunas.isEmpty()) {
            //retorno codigo 204
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comunas);
        
    }

    //endpoint para buscar una comuna por su id
    @GetMapping("/{id}")
    public ResponseEntity<Comuna> obtenerComunaPorId(@PathVariable Long id) {
        try {
            //verificar si existe la comuna
            //si no existe, se lanza una excepcion
            Comuna comuna = comunaService.obtenerComunaPorId(id);
            return ResponseEntity.ok(comuna);
        } catch (Exception e) {
            //retorno codigo 404
            return ResponseEntity.notFound().build();
        }
    }

    

}
