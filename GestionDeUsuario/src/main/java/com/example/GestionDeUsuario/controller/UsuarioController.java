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

    //endpoint para obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuario() {
        List<Usuario> usuarios = usuarioService.getUsuario();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }
    //endpoint para crear un nuevo usuario
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario nuevoUsuario){
        try {
            Usuario usuario = usuarioService.saveUsuario(nuevoUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            // Otro error interno
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    //endpoint para obtener un usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerSoportePorId(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.getUsuarioPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    //endpoint para eliminar un usuario por id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarUsuarioPorId(@PathVariable Long id){
        try {
            //verificar si el usuario existe
            Usuario usuario = usuarioService.getUsuarioPorId(id);
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // no existe el usuario
            return ResponseEntity.notFound().build();
        }

    }
    //metodo para actualixar un usuario por su id 
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuarioPorId(@PathVariable Long id,@RequestBody Usuario user){
        try {
            //verifico si el usuario existe
            Usuario usuario2 =usuarioService.getUsuarioPorId(id);
            //si existe modifico uno a uno sus valores
            usuario2.setIdUsuario(id);
            usuario2.setNombre(user.getNombre());
            usuario2.setApellido(user.getApellido());
            usuario2.setEmail(user.getEmail());
            usuario2.setPassword(user.getPassword());
            usuario2.setIdRol(user.getIdRol());

            //actualizar el usuario
            usuarioService.saveUsuario(usuario2);
            return ResponseEntity.ok(usuario2);
        } catch (Exception e) {
            //si no encuentra el usuario
            return ResponseEntity.notFound().build();
        }
    }
}
