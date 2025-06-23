package com.example.respuesta.controller;

import java.util.List;
import java.util.Set;

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

import com.example.respuesta.model.Respuesta;
import com.example.respuesta.service.RespuestaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/respuesta")
@Tag(name = "Respuestas", description = "Operaciones relacionadas con las respuestas a tickets de soporte.")
public class RespuestaController {
    @Autowired
    private RespuestaService respuestaService;

    // Obtener todas las respuetas
    @GetMapping
    @Operation(summary = "Obtener todas las respuestas",
               description = "Retorna una lista de todas las respuestas registradas en el sistema.")
    @ApiResponse(responseCode = "200", description = "Las respuestas fueron encontradas y devueltas.",
                 content = @Content(schema = @Schema(implementation = Respuesta.class))) 
    @ApiResponse(responseCode = "204", description = "No hay respuestas para devolver (lista vacía).")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al intentar obtener las respuestas.")
    public ResponseEntity<List<Respuesta>> obtenerRespuestas() {
        List<Respuesta> respuesta = respuestaService.obtenerRespuestas();
        if (respuesta.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(respuesta);
    }

    // Obtener una respuesta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Respuesta> obtenerPorId(@PathVariable Long id) {
        try {
            // verificar si la respuesta existe
            Respuesta respuesta = respuestaService.getRespuestaPorId(id);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una respuesta
    @PostMapping
    @Operation(summary = "Obtener una respuesta por ID",
               description = "Busca y retorna una respuesta específica mediante su identificador único.")
    @ApiResponse(responseCode = "200", description = "La respuesta fue encontrada y devuelta.",
                 content = @Content(schema = @Schema(implementation = Respuesta.class)))
    @ApiResponse(responseCode = "404", description = "La respuesta con el ID especificado no fue encontrada.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al buscar la respuesta.")
    public ResponseEntity<?> crearservicio(
    @Parameter(description = "ID de la respuesta a buscar", required = true, example = "1")    
    @RequestBody Respuesta nuevarespuesta) {
        // Definir los tipos de usuario permitidos
        Set<String> tiposPermitidos = Set.of("Administrador", "Tecnico Instalador", "Coordinador", "Cliente",
                "Soporte");

        // Validar si el tipo de usuario no está en el conjunto
        if (!tiposPermitidos.contains(nuevarespuesta.getTipousuario())) {
            return ResponseEntity.badRequest().body(
                    "Solo se aceptan estos Tipos de Usuarios\n'Administrador', 'Tecnico Instalador', 'Coordinador', 'Cliente','Soporte'");
        }

        try {
            Respuesta respuesta = respuestaService.saveRespuesta(nuevarespuesta);
            return ResponseEntity.status(201).body(respuesta);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Actualizar una respuesta existente
    @PutMapping("/{id}")
    @Operation(summary = "Crear una nueva respuesta",
               description = "Crea una nueva respuesta para un ticket de soporte. Requiere que el 'tipoUsuario' sea uno de los roles permitidos.",
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                   description = "Datos de la respuesta a crear", required = true,
                   content = @Content(schema = @Schema(implementation = Respuesta.class))
               ))
    @ApiResponse(responseCode = "201", description = "Respuesta creada exitosamente.",
                 content = @Content(schema = @Schema(implementation = Respuesta.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida: el tipo de usuario no es permitido.",
                 content = @Content(schema = @Schema(implementation = String.class, example = "Solo se aceptan estos Tipos de Usuarios: 'Administrador', 'Tecnico Instalador', 'Coordinador', 'Cliente','Soporte'")))
    @ApiResponse(responseCode = "404", description = "Recurso asociado no encontrado (ej. ticket de soporte).",
                 content = @Content(schema = @Schema(implementation = String.class, example = "Ticket de soporte no encontrado.")))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al crear la respuesta.")
    public ResponseEntity<Respuesta> actualizar(@PathVariable Long id, @RequestBody Respuesta actdatos) {
        try {
            Respuesta actualizado = respuestaService.actualizar(id, actdatos);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una respuesta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        respuestaService.eliminarrespuesta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/soporte/{idsoporte}")
    public ResponseEntity<List<Respuesta>> obtenerRespuestasPorSoporte(@PathVariable Long idsoporte) {
        List<Respuesta> respuestas = respuestaService.obtenerReBySoporte(idsoporte);
        if (respuestas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(respuestas);
    }

}
