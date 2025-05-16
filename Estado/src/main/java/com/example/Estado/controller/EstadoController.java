package com.example.Estado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Estado.model.Estado;
import com.example.Estado.service.EstadoService;


@RestController
@RequestMapping("/api/estado")
public class EstadoController {
    @Autowired
    private EstadoService estadoService;

        //endpoint buscar todos los estados

    @GetMapping
    public ResponseEntity<List<Estado>> obtenerEstado(){
        List<Estado> estados = estadoService.getEstados();
        if (estados.isEmpty()) {
            //retorno codigo 204
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estados);
    }

    //endpoint para buscar un proyecto mediante su id
    @GetMapping("/{id}")
    public ResponseEntity<Estado> obtenerEstadoPorId(@PathVariable Long id) {
        try {
            //verificar si existe el cliente
            Estado estado = estadoService.getEstadoPorId(id);
            return ResponseEntity.ok(estado);
        } catch (Exception e) {
            //retorno codigo 404
            return ResponseEntity.notFound().build();
        }
        
    }

    

    //endpoint para crear un nuevo estado
    @PostMapping
    public ResponseEntity<Estado> crearEstado(@RequestBody Estado estado) {
        return ResponseEntity.status(201).body(estadoService.saveEstado(estado));
        
    }

}
