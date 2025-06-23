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
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/categoria")
@Tag(name = "Categorias", description = "Operaciones relacionadas con las Categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Obtener todas las categroias
    @GetMapping
    @Operation(summary = "Obtener todas las Categorias")
    @ApiResponse(responseCode = "200", description = "Las Categorias fueron encontradas y devueltas")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al intentar obtener las categorías")
    public List<Categoria> obtenerTodos() {
        return categoriaService.obtenerCategoria();
    }

    // Obtener un servicio por ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener las categorias por id")
    @ApiResponse(responseCode = "200", description = "La Categoria fue encontrada y devuelta")
    @ApiResponse(responseCode = "404", description = "La Categoria no fue encontrada")
    public ResponseEntity<Categoria> obtenerPorId(
            @Parameter(description = "ID de la categoría a buscar", required = true, example = "1") @PathVariable Long id) {
        try {
            // verificar si el servicio existe
            Categoria servicio = categoriaService.getCategoriaById(id);
            return ResponseEntity.ok(servicio);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo servicio
    @PostMapping
    @Operation(summary = "Crear una nueva Categoria")
    @ApiResponse(responseCode = "201", description = "La Categoria fue creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida, el nombre excede los 30 caracteres")
    public ResponseEntity<?> crearCategoria(
        @RequestBody Categoria categoria) {
        if (categoria.getNombre().length() > 30) {
            return ResponseEntity.badRequest().body("El Nombre debe Contener entre 1 y 30 Caracteres");
        }
        Categoria nuevaCategoria = categoriaService.saveCategoria(categoria);
        return ResponseEntity.status(201).body(nuevaCategoria);
    }

    // Actualizar un servicio existente
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una Categoria por id")
    @ApiResponse(responseCode = "200", description = "Categoría actualizada exitosamente")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida o datos de entrada erróneos")
    public ResponseEntity<Categoria> actualizar(
            @Parameter(description = "ID de la categoría a actualizar", required = true, example = "1") 
            @PathVariable Long id,
            @RequestBody Categoria datos) {
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
    @ApiResponse(responseCode = "204", description = "Categoría eliminada exitosamente (no hay contenido para devolver)")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada para eliminar")
    @Operation(summary = "Eliminar una Categoria por id")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID de la categoría a eliminar", required = true, example = "1") @PathVariable Long id) {
        categoriaService.eliminarcategoria(id);
        return ResponseEntity.noContent().build();
    }

    //http://localhost:8091/doc/swagger-ui/index.html#/Categorias/obtenerTodos

}
