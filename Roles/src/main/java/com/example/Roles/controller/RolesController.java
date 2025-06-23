package com.example.Roles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Roles.model.Roles;
import com.example.Roles.service.RolesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Roles", description = "Operaciones relacionadas con los Roles de usuario")
public class RolesController {
    @Autowired
    private RolesService rolesService;

    // Obtener todas los roles
    @GetMapping
    @Operation(summary = "Obtener todos los roles",
               description = "Retorna una lista de todos los roles existentes en el sistema.")
    @ApiResponse(responseCode = "200", description = "Los roles fueron encontrados y devueltos.",
                 content = @Content(schema = @Schema(implementation = Roles.class)))
    @ApiResponse(responseCode = "204", description = "No hay roles para devolver (lista vacía).")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al intentar obtener los roles.")
    public List<Roles> obtenerRoles() {
        return rolesService.obtenerRoles();
    }

    // Obtener un rol por ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un rol por ID",
               description = "Busca y retorna un rol específico mediante su identificador único.")
    @ApiResponse(responseCode = "200", description = "Rol encontrado y devuelto.",
                 content = @Content(schema = @Schema(implementation = Roles.class)))
    @ApiResponse(responseCode = "404", description = "El rol con el ID especificado no fue encontrado.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al buscar el rol.")
    public ResponseEntity<Roles> obtenerPorId(@PathVariable Long id) {
        try {
            // verificar si la roles existe
            Roles roles = rolesService.getRolesPorId(id);
            return ResponseEntity.ok(roles);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //http://localhost:8095/doc/swagger-ui/index.html#/
}
