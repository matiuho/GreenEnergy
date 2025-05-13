package com.example.GestionDeUsuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GestionDeUsuarios.model.Usuario;
import com.example.GestionDeUsuarios.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @PostMapping 
    public Usuario crear(@RequestBody Usuario usuario){
        return service.crearUsuario(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return service.actualizarUsuario(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void desactivar(@PathVariable Long id){
        service.desactivarUsuario(id);
    }

    @GetMapping("/{id}")
    public Usuario obtener(@PathVariable Long id){
        return service.obtenerPorId(id);
    }

    @GetMapping
    public List<Usuario> listar(){
        return service.listarUsuariosActivos();
    }

}
//ERROR EN PRUEBA DE POSTMAN, NO NOS DEJA NO SABEMOS POR QUE