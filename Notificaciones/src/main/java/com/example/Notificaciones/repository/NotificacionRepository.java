package com.example.Notificaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Notificaciones.model.NotificacionModel;
@Repository

public interface NotificacionRepository extends JpaRepository<NotificacionModel, Long> {

    
    

}
