package com.example.Notificaciones.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Notificaciones.model.NotificacionModel;
import com.example.Notificaciones.service.NotificacionService;



@RestController
@RequestMapping("api/v1/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    //método para obtener todos los pacientes
    @GetMapping
    public ResponseEntity<List<NotificacionModel>> listarNotificacion() {
       List<NotificacionModel> notificacion = notificacionService.obtenerNotificaciones();
       if (notificacion.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(notificacion);
        }
    }

    //metodo para guardar una notificacion
    @PostMapping
    public  ResponseEntity<NotificacionModel> guardarNotificacion(@RequestBody NotificacionModel notificacion) {
        NotificacionModel not = notificacionService.saveNotificacion(notificacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(not);
    }

    //metodo para buscar paciente por id 
    @GetMapping("/{id}")
    public ResponseEntity<NotificacionModel> buscarNotificacionPorId(@PathVariable Integer id){

        try {
            //verififcar si existe el id
            NotificacionModel not = notificacionService.buscarNotificacionPorId(id);
            return ResponseEntity.ok(not); 
        } catch (Exception e) {
            //si no lo encuentro envio codigo not found
            // build para 
            return ResponseEntity.notFound().build();
        }
    
    }

    //metodo de eliminar una notificcacion  por id
    @DeleteMapping("/{id}")
     public ResponseEntity<?> eliminarNotificacion(@PathVariable Long id) {
        try {
            //verificar si LA NOTIFICACION existe
            NotificacionModel not = notificacionService.buscarNotificacionPorId(id);
            notificacionService.eliminarNotificacion(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // no existe el paciente
            return ResponseEntity.notFound().build();
        }
    }

    //metodo para actualixar un paciete por su id 
    @PutMapping("/{id}")
    public ResponseEntity<NotificacionModel> actualizarNotificacion(@PathVariable Integer id, @RequestBody NotificacionModel not){
        try {
            //verificar si existe el id
            NotificacionModel not2Model = notificacionService.buscarNotificacionPorId(id);
            // si existe el id actualizo los datos
            not2Model.setId(id);
            not2Model.setTipo(not.getTipo());
            not2Model.setMensaje(not.getMensaje());
            //guardo los cambios
            notificacionService.saveNotificacion(not2Model);
            return ResponseEntity.ok(not2Model);
        } catch (Exception e) {
            //si no lo encuentro envio codigo not found
            return ResponseEntity.notFound().build();
        }
    }












    

    

    

}
