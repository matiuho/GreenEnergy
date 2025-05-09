package com.example.Notificaciones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Notificaciones.model.NotificacionModel;
import com.example.Notificaciones.repository.NotificacionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NotificacionService {  
        @Autowired
        private NotificacionRepository notificacionRepository;

        //funcion para obtener todas las notificaciones 
        public List<NotificacionModel> obtenerNotificaciones() {
            return notificacionRepository.findAll();
        }

       //funcion para obtener una notificaion  mediante su id
        public NotificacionModel buscarNotificacionPorId (long  id) {
            return notificacionRepository.findById(id).get();
        }

        //funcion para guardar una notificacion
        public NotificacionModel saveNotificacion(NotificacionModel notificacion) {
            return notificacionRepository.save(notificacion);
        }

        //funcion para eliminar una notificacion
        public void eliminarNotificacion(Long id) {
            notificacionRepository.deleteById(id);
        }

}
