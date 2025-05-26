package com.example.GestionDeUsuario.controller;

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

import com.example.GestionDeUsuario.model.Usuario;
import com.example.GestionDeUsuario.service.UsuarioService;
import com.example.GestionDeUsuario.webclient.RolClient;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RolClient rolClient;

    // endpoint para obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuario() {
        List<Usuario> usuarios = usuarioService.getUsuario();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    // endpoint para crear un nuevo usuario
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario nuevoUsuario) {
        // Validacion debe contener ciertos caracteres
        if (!nuevoUsuario.getEmail().contains("@") ||
            (!nuevoUsuario.getEmail().contains(".com") && !nuevoUsuario.getEmail().contains(".cl"))) {
            return ResponseEntity.badRequest().body("El email es inválido debe tener '@' y terminar en '.com o .cl'");
        }
        if (nuevoUsuario.getEmail().length() < 1 || nuevoUsuario.getEmail().length() > 50){
            return ResponseEntity.badRequest().body("El Email debe Contener entre 1 y 50 Caracteres'");
        }
        if (nuevoUsuario.getPassword().length() < 8 || nuevoUsuario.getPassword().length() > 12) {
            return ResponseEntity.badRequest().body("La contraseña debe tener entre 8 y 12 caracteres.");
        }
        if (nuevoUsuario.getNombre().length() < 1 || nuevoUsuario.getNombre().length() > 50) {
            return ResponseEntity.badRequest().body("El Nombre debe Contener entre 1 y 50 Caracteres");
        }
        if (nuevoUsuario.getApellido().length() < 1 || nuevoUsuario.getApellido().length() > 50) {
            return ResponseEntity.badRequest().body("El Apellifo debe Contener entre 1 y 50 Caracteres");
        }
        try {
            Usuario usuario = usuarioService.saveUsuario(nuevoUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // endpoint para obtener un usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.getUsuarioPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // endpoint para eliminar un usuario por id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarUsuarioPorId(@PathVariable Long id) {
        try {
            // verificar si el usuario existe
            Usuario usuario = usuarioService.getUsuarioPorId(id);
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // no existe el usuario
            return ResponseEntity.notFound().build();
        }

    }

    // metodo para actualixar un usuario por su id
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuarioPorId(@PathVariable Long id, @RequestBody Usuario user) {
        try {
            // verifico si el usuario existe
            Usuario usuario2 = usuarioService.getUsuarioPorId(id);
            // si existe modifico uno a uno sus valores
            usuario2.setIdUsuario(id);
            usuario2.setNombre(user.getNombre());
            usuario2.setApellido(user.getApellido());
            usuario2.setEmail(user.getEmail());
            usuario2.setPassword(user.getPassword());
            usuario2.setIdRol(user.getIdRol());

            // actualizar el usuario
            usuarioService.saveUsuario(usuario2);
            return ResponseEntity.ok(usuario2);
        } catch (Exception e) {
            // si no encuentra el usuario
            return ResponseEntity.notFound().build();
        }
    }
}
