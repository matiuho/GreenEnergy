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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con la gestión de Usuarios y autenticación.")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired

    // endpoint para obtener todos los usuarios
    @GetMapping
    @Operation(summary = "Obtener todos los Usuarios",
               description = "Retorna una lista de todos los usuarios registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Los Usuarios fueron encontrados y devueltos.",
                 content = @Content(schema = @Schema(implementation = Usuario.class))) 
    @ApiResponse(responseCode = "204", description = "No hay usuarios registrados para devolver (lista vacía).")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al intentar obtener los usuarios.")
    public ResponseEntity<List<Usuario>> obtenerUsuario() {
        List<Usuario> usuarios = usuarioService.getUsuario();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    // endpoint para crear un nuevo usuario
    @PostMapping
    @Operation(summary = "Crear un nuevo Usuario",
               description = "Registra un nuevo usuario en el sistema. Se aplican validaciones a los datos proporcionados.")
    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente.",
                 content = @Content(schema = @Schema(implementation = Usuario.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida: los datos del usuario no cumplen con los requisitos (ej. formato de email, longitud de contraseña).",
                 content = @Content(schema = @Schema(implementation = String.class, example = "El email es inválido debe tener '@' y terminar en '.com o .cl'"))) // Ejemplo del mensaje de error
    @ApiResponse(responseCode = "409", description = "Conflicto: ya existe un usuario con el email proporcionado.",
                 content = @Content(schema = @Schema(implementation = String.class, example = "Ya existe un usuario con este correo electrónico."))) // Ejemplo del mensaje de error
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al crear el usuario.")
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
            //erro 409 conflic ya que hay dos usuarios con el mismo correo
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    // endpoint para obtener un usuario por id
    @GetMapping("/{id}")
    @Operation(summary = "Obtener Usuario por ID",
               description = "Busca y retorna un usuario específico mediante su identificador único.")
    @ApiResponse(responseCode = "200", description = "El Usuario fue encontrado y devuelto.",
                 content = @Content(schema = @Schema(implementation = Usuario.class)))
    @ApiResponse(responseCode = "404", description = "El Usuario con el ID especificado no fue encontrado.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al buscar el usuario.")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(
    @Parameter(description = "ID del usuario a buscar", required = true, example = "1")    
    @PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.getUsuarioPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        
    }

    // endpoint para eliminar un usuario por id
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un Usuario por ID",
               description = "Elimina permanentemente un usuario del sistema por su identificador único.")
    @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente (sin contenido para devolver).")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado para eliminar.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al eliminar el usuario.")
    public ResponseEntity<?> borrarUsuarioPorId(
    @Parameter(description = "ID del usuario a eliminar", required = true, example = "1")    
    @PathVariable Long id) {
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
    @Operation(summary = "Actualizar un Usuario por ID",
               description = "Actualiza completamente un usuario existente identificado por su ID. " +
                             "Se aplican validaciones a los datos del usuario.")
    @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente y devuelto.",
                 content = @Content(schema = @Schema(implementation = Usuario.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida: los datos del usuario no cumplen con los requisitos.",
                 content = @Content(schema = @Schema(implementation = String.class, example = "El Nombre debe Contener entre 1 y 50 Caracteres")))
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado para actualizar.")
    @ApiResponse(responseCode = "409", description = "Conflicto: el email de actualización ya pertenece a otro usuario.",
                 content = @Content(schema = @Schema(implementation = String.class, example = "Ya existe un usuario con este correo electrónico.")))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al actualizar el usuario.")
    public ResponseEntity<Usuario> actualizarUsuarioPorId(
    @Parameter(description = "ID del usuario a actualizar", required = true, example = "1")    
    @PathVariable Long id, @RequestBody Usuario user) {
        try {
            // verifico si el usuario existe
            Usuario usuario2 = usuarioService.getUsuarioPorId(id);
            // si existe modifico uno a uno sus valores
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

    //http://localhost:8096/doc/swagger-ui/index.html#/
}
