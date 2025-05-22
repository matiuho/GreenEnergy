package com.example.respuesta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.respuesta.model.Respuesta;
import com.example.respuesta.repository.RespuestaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    //mostrar todas las respuestas
    public List<Respuesta> obtenerRespuestas() {
        return respuestaRepository.findAll();
    }

    //buscar respuesta por id
    public Respuesta getRespuestaPorId(Long id){
        return respuestaRepository.findById(id).orElseThrow(()-> new RuntimeException("Respuesta no encontrado"));
    }

    //guardar una respuesta 
    public Respuesta saveRespuesta(Respuesta respuesta) {
        return respuestaRepository.save(respuesta);
    }

    public Respuesta actualizar(Long id, Respuesta datos) {
        Respuesta existente = respuestaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Respuesta no encontrado"));
        existente.setFechaSoporte(datos.getFechaSoporte());
        existente.setComentario(datos.getComentario());
        existente.setTipoUsuario(datos.getTipoUsuario());
        
        return respuestaRepository.save(existente);
    }

    public void eliminarrespuesta(Long id) {
        respuestaRepository.deleteById(id);
    }


}
