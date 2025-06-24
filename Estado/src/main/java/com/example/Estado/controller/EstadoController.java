package com.example.Estado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Estado.model.Estado;
import com.example.Estado.service.EstadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/estado")
@Tag(name = "Estados", description = "Operaciones relacionadas con los Estados")
public class EstadoController {
    @Autowired
    private EstadoService estadoService;

    //endpoint buscar todos los estados
    @GetMapping
    @Operation(summary = "Obtener todos los Estados",
    description = "Retorna una lista de todos los estados definidos en el sistema.")

    @ApiResponse(responseCode = "200",
    description = "Los Estados fueron encontrados y devueltos.",
    content = @Content(schema = @Schema(implementation = Estado.class)))

    @ApiResponse(responseCode = "204",
    description = "No hay estados para devolver (lista vacía).")

    @ApiResponse(responseCode = "500", description = "Error interno del servidor al intentar obtener los estados.")
    public ResponseEntity<List<Estado>> obtenerEstado(){
        List<Estado> estados = estadoService.getEstados();
        if (estados.isEmpty()) {
            //retorno codigo 204
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estados);
    }

    //endpoint para buscar un estado mediante su id
    @GetMapping("/{id}")
    @Operation(summary = "Obtener Estado por ID",
    description = "Busca y retorna un estado específico mediante su identificador único.")

    @ApiResponse(responseCode = "200",
    description = "El Estado fue encontrado y devuelto.",
    content = @Content(schema = @Schema(implementation = Estado.class)))

    @ApiResponse(responseCode = "404",
    description = "El Estado con el ID especificado no fue encontrado.")

    @ApiResponse(responseCode = "500",
    description = "Error interno del servidor al buscar el estado.")
    public ResponseEntity<Estado> obtenerEstadoPorId(
    @Parameter(description = "ID del estado a buscar", required = true, example = "1")    
    @PathVariable Long id) {
        try {
            //verificar si existe el estado
            Estado estado = estadoService.getEstadoPorId(id);
            return ResponseEntity.ok(estado);
        } catch (Exception e) {
            //retorno codigo 404
            return ResponseEntity.notFound().build();
        }
        
    }

    //http://localhost:8082/doc/swagger-ui/index.html#/

}
