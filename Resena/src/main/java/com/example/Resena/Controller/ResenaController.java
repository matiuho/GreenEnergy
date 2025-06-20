package com.example.Resena.Controller;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Resena.Model.Resena;
import com.example.Resena.Service.ResenaService;
import com.example.Resena.WebClient.ServicioClient;
import com.example.Resena.WebClient.UserClient;


@RestController
@RequestMapping("/api/resena")
public class ResenaController {
    @Autowired
    ResenaService resenaService;
    @Autowired
    UserClient userClient;
    @Autowired
    ServicioClient servicioClient;

    @GetMapping
    public ResponseEntity<List<Resena>> getResenas() {
        List<Resena> resenas = resenaService.getResenas();
        return ResponseEntity.ok(resenas);
    }

    @PostMapping
    public ResponseEntity<?> crearResena(@RequestBody Resena nuevResena) {
        LocalDate despues = LocalDate.of(2025, 5, 28);
        if (nuevResena.getFechaComentario().isBefore(despues)) {
            return ResponseEntity.badRequest()
                    .body("La fecha del Comentario debe ser igual o posterior al 28 de mayo de 2025.");
        }
        if (nuevResena.getComentario().length() < 1 || nuevResena.getComentario().length() > 100) {
            return ResponseEntity.badRequest().body("El comentario debe tener entre 1 y 100 caracteres.");
        }
        try {
            Resena resena = resenaService.savResena(nuevResena);
            return ResponseEntity.status(201).body(resena);
        } catch (RuntimeException e) {
            // Captura error por estado no encontrado
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resena> obtenerProyectoPorId(@PathVariable Long id) {
        try {
            // verificar si existe el estado
            Resena resena = resenaService.getResenaPorId(id);
            return ResponseEntity.ok(resena);
        } catch (Exception e) {
            // retorno codigo 404
            return ResponseEntity.notFound().build();
        }

    }

    //endpoint para buscar ReseÃ±a por ID USUARIO
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Resena>> obtenerReByUsuario(@PathVariable Long idUsuario){
        List<Resena> resena = resenaService.obtenerReByUsuario(idUsuario);
        if (resena == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(resena);
    }
}
