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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/api/proyecto")
@Tag(name = "Proyectos", description = "Operaciones relacionadas con la gestión de Proyectos.")
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
    @Operation(summary = "Obtener todos los Proyectos",
               description = "Retorna una lista de todos los proyectos registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Los Proyectos fueron encontrados y devueltos.",
                 content = @Content(schema = @Schema(implementation = Proyecto.class)))
    @ApiResponse(responseCode = "204", description = "No hay proyectos registrados para devolver (lista vacía).")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al intentar obtener los proyectos.")
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
    @Operation(summary = "Crear un nuevo Proyecto",
               description = "Registra un nuevo proyecto en el sistema. El comentario debe tener entre 1 y 100 caracteres.")
    @ApiResponse(responseCode = "201", description = "Proyecto creado exitosamente.",
                 content = @Content(schema = @Schema(implementation = Proyecto.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida: el comentario no cumple con los requisitos de longitud o es nulo.",
                 content = @Content(schema = @Schema(implementation = String.class, example = "El comentario debe tener entre 1 y 100 caracteres."))) 
    @ApiResponse(responseCode = "404", description = "Recurso asociado (ej. estado, usuario, contratación) no encontrado.",
                 content = @Content(schema = @Schema(implementation = String.class, example = "El estado especificado no existe.")))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al crear el proyecto.")
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
    @Operation(summary = "Obtener Proyecto por ID",
               description = "Busca y retorna un proyecto específico mediante su identificador único.")
    @ApiResponse(responseCode = "200", description = "El Proyecto fue encontrado y devuelto.",
                 content = @Content(schema = @Schema(implementation = Proyecto.class)))
    @ApiResponse(responseCode = "404", description = "El Proyecto con el ID especificado no fue encontrado.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al buscar el proyecto.")
    public ResponseEntity<Proyecto> obtenerProyectoPorId(
    @Parameter(description = "ID del proyecto a buscar", required = true, example = "1")    
    @PathVariable Long id) {
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
    @Operation(summary = "Obtener Proyectos por ID de Usuario",
               description = "Retorna una lista de proyectos asociados a un ID de usuario específico.")
    @ApiResponse(responseCode = "200", description = "Proyectos encontrados y devueltos.",
                 content = @Content(schema = @Schema(implementation = Proyecto.class)))
    @ApiResponse(responseCode = "404", description = "No se encontraron proyectos para el ID de usuario especificado o el usuario no existe.")
    @ApiResponse(responseCode = "204", description = "No hay proyectos para este usuario (lista vacía).")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al buscar los proyectos por usuario.")
    public ResponseEntity<List<Proyecto>> obtenerProByUsuario(
    @Parameter(description = "ID del usuario para buscar sus proyectos", required = true, example = "5")    
    @PathVariable Long usuarioId) {
        List<Proyecto> proyecto = proyectosService.obtenerProByUsuarioo(usuarioId);

        if (proyecto == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(proyecto);
    }

    //endpoint para actualizar un proyecto
    @PutMapping ("/{id}")
    @Operation(summary = "Actualizar un Proyecto por ID",
               description = "Actualiza el comentario y el estado de un proyecto existente identificado por su ID. " +
                             "El comentario debe tener entre 1 y 100 caracteres.")
    @ApiResponse(responseCode = "200", description = "Proyecto actualizado exitosamente y devuelto.",
                 content = @Content(schema = @Schema(implementation = Proyecto.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida: el comentario no cumple con los requisitos de longitud o es nulo.",
                 content = @Content(schema = @Schema(implementation = String.class, example = "El comentario debe tener entre 1 y 100 caracteres.")))
    @ApiResponse(responseCode = "404", description = "Proyecto no encontrado para actualizar o el nuevo estado no existe.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al actualizar el proyecto.")
    public ResponseEntity<?> actualizarProyecto(
    @Parameter(description = "ID del proyecto a actualizar", required = true, example = "1")    
    @PathVariable Long id, @RequestBody Proyecto proyectoActualizado) {
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
    //http://localhost:8081/doc/swagger-ui/index.html#/Proyectos/obtenerProyectos
}

