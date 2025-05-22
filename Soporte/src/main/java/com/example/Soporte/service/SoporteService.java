package com.example.Soporte.service;

import com.example.Soporte.model.Soporte;
import com.example.Soporte.repository.SoporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SoporteService {

    @Autowired
    private SoporteRepository soporteRepository;

    public List<Soporte> obtenerTodos() {
        return soporteRepository.findAll();
    }

    public Soporte guardarSoporte(Soporte soporte) {
        return soporteRepository.save(soporte);
    }

    public void eliminarPorId(Long id) {
        soporteRepository.deleteById(id);
    }
    //metodo para buscar un estado por su ID
     public Soporte getEstadoPorId(Long id){
        return soporteRepository.findById(id).orElseThrow(()-> new RuntimeException("Soporte no encontrado"));
    }
    

}