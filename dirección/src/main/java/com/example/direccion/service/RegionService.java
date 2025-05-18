package com.example.direccion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.direccion.model.Region;
import com.example.direccion.repository.RegionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;
    // Método para obtener todas las regiones
    public List<Region> obtenerTodasLasRegiones() {
        return regionRepository.findAll();
    }
    // Método para buscar una región por su ID
    public Region obtenerRegionPorId(Long id) {
        return regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Región no encontrada con ID: " + id));
    }       

}
