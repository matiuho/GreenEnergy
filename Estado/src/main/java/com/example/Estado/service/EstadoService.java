package com.example.Estado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Estado.model.Estado;
import com.example.Estado.repository.EstadoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EstadoService {
    @Autowired
    private EstadoRepository estadoRepository;

    //metodo para consultar todos los estados
    public List<Estado> getEstados(){
        return estadoRepository.findAll();
    }

    //metodo para buscar un estado por su ID
    public Estado getEstadoPorId(Long id){
        return estadoRepository.findById(id).orElseThrow(()-> new RuntimeException("Estado no encontrado"));
    }

}