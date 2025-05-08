package com.example.Notificaciones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Notificaciones.controller.NotificacionController;
import com.example.Notificaciones.model.NotificacionModel;
import com.example.Notificaciones.repository.NotificacionRepository;

@Service
public class NotificacionService {  
        @Autowired
        private NotificacionRepository notificacionRepository;

        public NotificacionModel crearNotifiacion(NotificacionModel notificacion) {
            return notificacionRepository.save(notificacion);
        }

        public List<NotificacionModel> listarNotificaciones() {
            return notificacionRepository.findAll();
        }

        public NotificacionModel obtenerNotificacionPorId(Long id) {
            return notificacionRepository.findById(id).orElse(null);
        }

        public void crearNotificacion(NotificacionController notificacion) {
            throw new UnsupportedOperationException("Unimplemented method 'crearNotificacion'");
        }   

}
