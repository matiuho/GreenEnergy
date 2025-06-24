package com.example.direccion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.direccion.model.Region;
import com.example.direccion.service.RegionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/region")
@Tag(name = "Regiones", description = "Operaciones relacionadas con las Regiones.") 
public class RegionController {
    @Autowired 
    private RegionService regionService;

    //edpoint para buscar todas las regiones
    @GetMapping
    @Operation(summary = "Obtener todas las Regiones",
    description = "Retorna una lista de todas las regiones.")

    @ApiResponse(responseCode = "200",
    description = "Las Regiones fueron encontradas y devueltas.",
    content = @Content(schema = @Schema(implementation = Region.class)))

    @ApiResponse(responseCode = "204",
    description = "No hay regiones para devolver (lista vacía).")

    @ApiResponse(responseCode = "500",
    description = "Error interno del servidor al intentar obtener las regiones.")
    public ResponseEntity<List<Region>> obtenerRegiones() {
        List<Region> regiones = regionService.getRegiones();
        if (regiones.isEmpty()) {
            //retorno codigo 204
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(regiones);
    }

    //edpoint para buscar una region por su id
    @GetMapping("/{id}")
    @Operation(summary = "Obtener Región por ID",
    description = "Busca y retorna una región específica mediante su identificador único.")

    @ApiResponse(responseCode = "200",
    description = "La Región fue encontrada y devuelta.",
    content = @Content(schema = @Schema(implementation = Region.class)))

    @ApiResponse(responseCode = "404",
    description = "La Región con el ID especificado no fue encontrada.")
    
    @ApiResponse(responseCode = "500",
    description = "Error interno del servidor al buscar la región.")
    public ResponseEntity<Region> obtenerRegionPorId(
    @Parameter(description = "ID de la región a buscar", required = true, example = "13")
    @PathVariable int  id) {
        try {
            //verificar si existe la region
            Region region = regionService.obtenerRegionPorId(id);
            return ResponseEntity.ok(region);
        } catch (Exception e) {
            //retorno codigo 404
            return ResponseEntity.notFound().build();
        }
    }

}
