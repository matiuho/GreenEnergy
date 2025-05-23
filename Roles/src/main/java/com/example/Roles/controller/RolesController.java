package com.example.Roles.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Roles.model.Roles;
import com.example.Roles.service.RolesService;

@RestController
@RequestMapping("/api/roles")
public class RolesController {
    @Autowired
    private RolesService rolesService;
    // Obtener todas los roles
    @GetMapping
    public List<Roles> obtenerRoles() {
        return rolesService.obtenerRoles();
    }
    // Obtener un rol por ID
    @GetMapping("/{id}")
    public ResponseEntity<Roles> obtenerPorId(@PathVariable Long id) {
        try {
            //verificar si la roles existe
            Roles roles = rolesService.getRolesPorId(id);
            return ResponseEntity.ok(roles);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    // Crear un rol
    @PostMapping
    public ResponseEntity<Roles> crearroles(@RequestBody Roles roles) {
        Roles nuevoRoles = rolesService.saveRoles(roles);
        return ResponseEntity.status(201).body(nuevoRoles);
    }
    // Eliminar un rol
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        rolesService.eliminarroles(id);
        return ResponseEntity.noContent().build();
    }

}
