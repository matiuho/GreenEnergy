package com.example.Notificaciones.controller;

import java.util.List;

import javax.management.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Notificaciones.model.NotificacionModel;
import com.example.Notificaciones.service.NotificacionService;

@Controller
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @PostMapping
    public void crearNotificacion(@RequestBody NotificacionController notificacion) {
        notificacionService.crearNotificacion(notificacion);
    }

    @GetMapping
    public List<NotificacionModel> listarNotificaciones() {
        return notificacionService.listarNotificaciones();
        
    }

     @GetMapping("/{id}")
    public NotificacionModel getNotificacion(@PathVariable Long id) {
        return notificacionService.obtenerNotificacionPorId(id);
    }

    

    

}
