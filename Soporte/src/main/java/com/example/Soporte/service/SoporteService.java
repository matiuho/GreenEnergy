package com.example.Soporte.service;

import com.example.Soporte.model.Soporte;
import com.example.Soporte.repository.SoporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<Soporte> obtenerPorFecha(LocalDate fecha) {
        return soporteRepository.findByFecha(fecha);
    }
}