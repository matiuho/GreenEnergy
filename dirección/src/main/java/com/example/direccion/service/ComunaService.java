package com.example.direccion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.direccion.model.Comuna;
import com.example.direccion.repository.ComunaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunaService {
    @Autowired
    private ComunaRepository comunaRepository;

    //metodo para mostrar todas las comunas
    public List<Comuna> obtenerTodasLasComunas() {
        return comunaRepository.findAll();
    }

    //metodo para buscar una comuna por su id
    public Comuna obtenerComunaPorId(Long id) {
        return comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada con ID: " + id));
    }
    

}
