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
   // public Contrataciones crearContrataciones(Date fechaContrato, Date fechaFin, Date fechaInicio, int total, Long id){
      //  Contrataciones contrataciones = contratacionesRepository.findById(id).orElseThrow(()-> new RuntimeException("Contratacion no existe con ID: " + id));
        
      /*   Contrataciones contrataciones2 = new Contrataciones();
        contrataciones2.setFechaContrato(fechaContrato);
        contrataciones2.setFechaFin(fechaFin);
        contrataciones2.setFechaInicio(fechaInicio);
        contrataciones2.setTotal(total);
    }

}
