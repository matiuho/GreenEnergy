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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/comuna")
@Tag(name = "Comunas", description = "Operaciones relacionadas con las Comunas.") 
public class ComunaController {
    @Autowired
    private ComunaService comunaService;


    //endpoint para buscar todas las comunas
    @GetMapping
    @Operation(summary = "Obtener todas las Comunas",
    description = "Retorna una lista de todas las comunas existentes en el sistema.")

    @ApiResponse(responseCode = "200",
    description = "Las comunas fueron encontradas y devueltas.",
    content = @Content(schema = @Schema(implementation = Comuna.class))) 

    @ApiResponse(responseCode = "204",
    description = "No hay comunas para devolver (lista vacía).")

    @ApiResponse(responseCode = "500",
    description = "Error interno del servidor al intentar obtener las comunas.")
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
    @Operation(summary = "Obtener Comuna por ID",
    description = "Busca y retorna una comuna específica mediante su identificador único.")

    @ApiResponse(responseCode = "200",
    description = "La Comuna fue encontrada y devuelta.",
    content = @Content(schema = @Schema(implementation = Comuna.class)))

    @ApiResponse(responseCode = "404",
    description = "La Comuna con el ID especificado no fue encontrada.")

    @ApiResponse(responseCode = "500",
    description = "Error interno del servidor al buscar la comuna.")
    public ResponseEntity<Comuna> obtenerComunaPorId(
    @Parameter(description = "ID de la comuna a buscar", required = true, example = "1")    
    @PathVariable Long id) {
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
