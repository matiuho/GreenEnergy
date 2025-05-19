package com.example.direccion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.direccion.model.Region;
import com.example.direccion.service.RegionService;

@RestController
@RequestMapping("/api/region")
public class RegionController {
    @Autowired 
    private RegionService regionService;

    //edpoint para buscar todas las regiones
    @GetMapping
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
    public ResponseEntity<Region> obtenerRegionPorId(int  id) {
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
