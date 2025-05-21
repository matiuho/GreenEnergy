package com.example.Contrataciones.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Contrataciones.model.Contrataciones;
import com.example.Contrataciones.repository.ContratacionesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ContratacionesService {

    @Autowired
    private ContratacionesRepository contratacionesRepository;
    
    public List<Contrataciones> obtenerContrataciones(){
        return contratacionesRepository.findAll();
    }
}
