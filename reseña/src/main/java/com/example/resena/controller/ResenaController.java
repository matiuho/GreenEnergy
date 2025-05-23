package com.example.resena.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.resena.model.Resena;
import com.example.resena.service.ResenaService;
import com.example.resena.webclient.ClienteClient;
import com.example.resena.webclient.UserClient;

@RestController
@RequestMapping("/api/resena")
public class ResenaController {
    @Autowired ResenaService resenaService;
    @Autowired UserClient userClient;
    @Autowired ClienteClient clienteClient;

    @GetMapping 
    public ResponseEntity<List<Resena>> getResenas() {
        List<Resena> resenas = resenaService.getResenas();
        return ResponseEntity.ok(resenas);
    }

    @PostMapping
    public ResponseEntity<?> crearResena(@RequestBody Resena nuevResena){
        try {
            Resena resena = resenaService.savResena(nuevResena);
            return ResponseEntity.status(201).body(resena);
        } catch (RuntimeException e) {
            // Captura error por estado no encontrado
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            // Otro error interno
            return ResponseEntity.status(404).body(e.getMessage());
    }
    }
}
