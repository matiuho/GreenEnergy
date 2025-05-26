package com.example.servicio.service;

import com.example.servicio.model.Servicio;
import com.example.servicio.repository.ServicioRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public List<Servicio> obtenerTodos() {
        return servicioRepository.findAll();
    }

   public Servicio getServicioPorId(Long id){
        return servicioRepository.findById(id).orElseThrow(()-> new RuntimeException("Servicio no encontrado"));
    }

    public Servicio saveServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public Servicio actualizarServicio(Long id, Servicio datos) {
        Servicio existente = servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        existente.setNombre(datos.getNombre());
        existente.setPrecio(datos.getPrecio());

        return servicioRepository.save(existente);
    }

    public void eliminarservicio(Long id) {
        servicioRepository.deleteById(id);
    }
}
