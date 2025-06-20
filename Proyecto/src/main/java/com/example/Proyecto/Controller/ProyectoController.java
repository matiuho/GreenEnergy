package com.example.Proyecto.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Proyecto.Model.Proyecto;
import com.example.Proyecto.Service.ProyectosService;
import com.example.Proyecto.WebClient.ContratacionClient;
import com.example.Proyecto.WebClient.EstadoClient;
import com.example.Proyecto.WebClient.UsuarioClient;



@RestController
@RequestMapping("/api/proyecto")
public class ProyectoController {
    @Autowired
    private ProyectosService proyectosService;

    @Autowired
    EstadoClient estadoClient;

    @Autowired
    UsuarioClient usuarioClient;

    @Autowired
    ContratacionClient contratacionClient;

    @GetMapping
    public ResponseEntity<List<Proyecto>> obtenerProyectos() {
        List<Proyecto> proyecto = proyectosService.getProyectos();
        if (proyecto.isEmpty()) {
            // Retorno cÃ³digo 204 No Content
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(proyecto);
    }

    // endpoint para crear un nuevo proyecto
    @PostMapping
    public ResponseEntity<?> crearProyecto(@RequestBody Proyecto nuevoproyecto) {
        if (nuevoproyecto.getComentario().length() < 1|| nuevoproyecto.getComentario().length() > 100) {
            return ResponseEntity.badRequest().body("El comentario debe tener entre 1 y 100 caracteres.");
        }
        try {
            Proyecto proyecto = proyectosService.saveProyecto(nuevoproyecto);
            return ResponseEntity.status(201).body(proyecto);
        } catch (RuntimeException e) {
            // Captura error por estado no encontrado
            return ResponseEntity.status(404).body(e.getMessage());
        } 
    }

    // endpoint para buscar un estado mediante su id
    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> obtenerProyectoPorId(@PathVariable Long id) {
        try {
            // verificar si existe el estado
            Proyecto proyecto = proyectosService.getProyectoPorId(id);
            return ResponseEntity.ok(proyecto);
        } catch (Exception e) {
            // retorno codigo 404
            return ResponseEntity.notFound().build();
        }

    }

    // endpoint para buscar Contratcion por ID USUARIO
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Proyecto>> obtenerProByUsuario(@PathVariable Long usuarioId) {
        List<Proyecto> proyecto = proyectosService.obtenerProByUsuarioo(usuarioId);

        if (proyecto == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(proyecto);
    }

    //endpoint para actualizar un proyecto
    @PutMapping ("/{id}")
    public ResponseEntity<?> actualizarProyecto(@PathVariable Long id, @RequestBody Proyecto proyectoActualizado) {
        try {
            //verificar si existe el proyecto
            Proyecto proyecto = proyectosService.getProyectoPorId(id);
            //actualizar el proyecto
            proyecto.setComentario(proyectoActualizado.getComentario());
            proyecto.setEstadoId(proyectoActualizado.getEstadoId());
            //guardar el proyecto actualizado
            proyectosService.saveProyecto(proyecto);
            return ResponseEntity.ok(proyecto);
        } catch (Exception e) {
            //retorno codigo 404
            return ResponseEntity.notFound().build();
        }
    }
}

