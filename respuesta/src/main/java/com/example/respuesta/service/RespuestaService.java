package com.example.respuesta.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.respuesta.model.Respuesta;
import com.example.respuesta.repository.RespuestaRepository;
import com.example.respuesta.webclient.SoporteClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private SoporteClient soporteClient;

    // mostrar todas las respuestas
    public List<Respuesta> obtenerRespuestas() {
        return respuestaRepository.findAll();
    }

    // buscar respuesta por id
    public Respuesta getRespuestaPorId(Long id) {
        return respuestaRepository.findById(id).orElseThrow(() -> new RuntimeException("Respuesta no encontrado"));
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

    public Respuesta saveRespuesta(Respuesta nuevaRespuesta) {
        // verificar si el Soporte existe consultando al microservicio Soporte
        Map<String, Object> soporte = soporteClient.getSoporteById(nuevaRespuesta.getIdSoporte());
        // verifico si me trajo el estado o no
        if (soporte == null || soporte.isEmpty()) {
            throw new RuntimeException("Respuesta no encontrado");
        }
        return respuestaRepository.save(nuevaRespuesta);
    }
}
