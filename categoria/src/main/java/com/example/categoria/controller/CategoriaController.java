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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/categoria")
@Tag(name = "Categorias", description = "Mostrar las Categorias existentes")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Obtener todas las categroias 
    @GetMapping
    @Operation(summary = "Obtener todas las categorias")
    public List<Categoria> obtenerTodos() {
        return categoriaService.obtenerCategoria();
    }

    // Obtener un servicio por ID
    @GetMapping("/{id}")
       @Operation(summary = "Obtener las categorias por id")
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
    @Operation(summary = "Crear una nueva Categoria")
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria) {
        if (categoria.getNombre().length() > 30) {
            return ResponseEntity.badRequest().body("El Nombre debe Contener entre 1 y 30 Caracteres");
        }
        Categoria nuevaCategoria= categoriaService.saveCategoria(categoria);
        return ResponseEntity.status(201).body(nuevaCategoria);
    }

    // Actualizar un servicio existente
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una Categoria por id")
    
    public ResponseEntity<Categoria> actualizar(@PathVariable Long id, @RequestBody Categoria  datos) {
        try {
            Categoria actualizado = categoriaService.getCategoriaById(id);
            actualizado.setNombre(datos.getNombre());
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

     // Eliminar un categoria
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una Categoria por id")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaService.eliminarcategoria(id);
        return ResponseEntity.noContent().build();
    }

    

}
