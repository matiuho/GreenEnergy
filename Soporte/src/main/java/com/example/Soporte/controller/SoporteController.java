package com.example.Soporte.controller;

import java.time.LocalDate;
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

import com.example.Soporte.model.Soporte;
import com.example.Soporte.service.SoporteService;
import com.example.Soporte.webclient.CategoriaClient;
import com.example.Soporte.webclient.EstadoClient;
import com.example.Soporte.webclient.UserClient;

@RestController
@RequestMapping("/api/soporte")
public class SoporteController {
    @Autowired
    private SoporteService soporteService;

    @Autowired
    CategoriaClient categoriaClient;

    @Autowired
    EstadoClient estadoClient;

    @Autowired
    UserClient userClient;

    // Endpoint para obtener todos los soportes
    @GetMapping
    public ResponseEntity<List<Soporte>> obtenerSoportes() {
        List<Soporte> soportes = soporteService.getSoporte();
        if (soportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(soportes);
    }

    @PostMapping
    public ResponseEntity<?> crearSoporte(@RequestBody Soporte nuevoSoporte) {
        LocalDate desde = LocalDate.of(2025, 5, 27);
        if (nuevoSoporte.getFecha().isBefore(desde)) {
            return ResponseEntity.badRequest()
                    .body("La fecha de contratación debe ser igual o posterior al 27 de mayo de 2025.");
        }
        if (nuevoSoporte.getDescripcion().length() < 1 || nuevoSoporte.getDescripcion().length() > 100) {
            return ResponseEntity.badRequest().body("La Descripccion debe Contener entre 1 y 100 Caracteres");
        }
        try {
            Soporte soporte = soporteService.saveSoporte(nuevoSoporte);
            return ResponseEntity.status(HttpStatus.CREATED).body(soporte);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            // Otro error interno
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Soporte> obtenerSoportePorId(@PathVariable Long id) {
        try {
            Soporte soporte = soporteService.getSoportePorId(id);
            return ResponseEntity.ok(soporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/usuario/{idusuario}")
    public ResponseEntity<List<Soporte>> obtenerSoportePorUsuario(@PathVariable Long idusuario) {
        List<Soporte> soporte = soporteService.obtenerSoByUsuario(idusuario);
        if (soporte == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(soporte);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarSoportePorId(@PathVariable Long id) {
        try {
            // verificar si el soporte existe
            Soporte soporte = soporteService.getSoportePorId(id);
            soporteService.eliminarPorId(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // no existe el paciente
            return ResponseEntity.notFound().build();
        }

    }

    // metodo para actualixar un paciete por su id
    @PutMapping("/{id}")
    public ResponseEntity<Soporte> actualizarSoportePorId(@PathVariable Long id, @RequestBody Soporte sop) {
        try {
            // verifico si el paciente existe
            Soporte soporte2 = soporteService.getSoportePorId(id);
            // si existe modifico uno a uno sus valores
            soporte2.setFecha(sop.getFecha());
            soporte2.setDescripcion(sop.getDescripcion());
            soporte2.setIdEstado(sop.getIdEstado());
            soporte2.setIdCategoria(sop.getIdCategoria());
            soporte2.setIdusuario(sop.getIdusuario());

            // actualizar el registro
            soporteService.saveSoporte(soporte2);
            return ResponseEntity.ok(soporte2);
        } catch (Exception e) {
            // si no encuentra el paciente
            return ResponseEntity.notFound().build();
        }
    }

}
