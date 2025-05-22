package com.example.categoria.controller;

import java.util.List;

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

import com.example.categoria.model.Categoria;
import com.example.categoria.service.CategoriaService;

@RestController
@RequestMapping("api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Obtener todas las categroias 
    @GetMapping
    public List<Categoria> obtenerTodos() {
        return categoriaService.obtenerCategoria();
    }

    // Obtener un servicio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerPorId(@PathVariable Long id) {
        try {
            //verificar si el servicio existe
            Categoria servicio = categoriaService.getCategoriaById(id);
            return ResponseEntity.ok(servicio);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }  

     // Crear un nuevo servicio
    @PostMapping
    public ResponseEntity<Categoria> crearcategroia(@RequestBody Categoria categoria) {
        Categoria nuevaCategoria= categoriaService.saveCategoria(categoria);
        return ResponseEntity.status(201).body(nuevaCategoria);
    }

    // Actualizar un servicio existente
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(@PathVariable Long id, @RequestBody Categoria  datos) {
        try {
            Categoria actualizado = categoriaService.actualizar(id, datos);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

     // Eliminar un categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaService.eliminarservicio(id);
        return ResponseEntity.noContent().build();
    }

}
