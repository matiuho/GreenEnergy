package com.example.direccion.controller;

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

import com.example.direccion.model.Direccion;
import com.example.direccion.service.DireccionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/direccion")
@Tag(name = "Direcciones", description = "Operaciones relacionadas con las Direcciones de clientes.")
public class DireccionController {
    @Autowired
    private DireccionService direccionService;

    @GetMapping
    @Operation(summary = "Obtener todas las Direcciones",
               description = "Retorna una lista de todas las direcciones existentes en el sistema.")
    @ApiResponse(responseCode = "200", description = "Las direcciones fueron encontradas y devueltas.",
                 content = @Content(schema = @Schema(implementation = Direccion.class)))
    @ApiResponse(responseCode = "204", description = "No hay direcciones para devolver (lista vacía).")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al intentar obtener las direcciones.")
    public ResponseEntity<List<Direccion>> obtenerDirecciones() {
        List<Direccion> direcciones = direccionService.getDirecciones();
        if (direcciones.isEmpty()) {
            // retorno codigo 204
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(direcciones);
    }

    // endpoint para crear una nueva direccion
    @PostMapping
    @Operation(summary = "Crear una nueva Dirección",
               description = "Registra una nueva dirección en el sistema. El nombre de la dirección debe tener entre 5 y 50 caracteres.")
    @ApiResponse(responseCode = "201", description = "La Dirección fue creada exitosamente.",
                 content = @Content(schema = @Schema(implementation = Direccion.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida: el nombre excede los límites (5-50 caracteres) o es nulo/vacío.",
                 content = @Content(schema = @Schema(implementation = String.class, example = "El Nombre debe Contener entre 5 y 50 Caracteres")))
    @ApiResponse(responseCode = "404", description = "Recurso asociado (ej. comuna) no encontrado durante la creación.",
                 content = @Content(schema = @Schema(implementation = String.class, example = "La comuna especificada no existe.")))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al crear la dirección.")
    public ResponseEntity<?> crearDireccion(@RequestBody Direccion nuevaDireccion) {
        if (nuevaDireccion.getNombre().length() < 5 || nuevaDireccion.getNombre().length() > 50) {
            return ResponseEntity.badRequest().body("El Nombre debe Contener entre 5 y 50 Caracteres");
        }
        try {
            Direccion direccion = direccionService.saveDireccion(nuevaDireccion);
            return ResponseEntity.status(HttpStatus.CREATED).body(direccion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(" Error interno: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Dirección por ID",
               description = "Busca y retorna una dirección específica mediante su identificador único.")
    @ApiResponse(responseCode = "200", description = "Dirección encontrada y devuelta.",
                 content = @Content(schema = @Schema(implementation = Direccion.class)))
    @ApiResponse(responseCode = "404", description = "La dirección con el ID especificado no fue encontrada.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al buscar la dirección.")
    public ResponseEntity<Direccion> obtenerDireccionPorId(
        @Parameter(description = "ID de la dirección a buscar", required = true, example = "1")
        @PathVariable Long id) {
        try {
            // verificar si existe la comuna
            // si no existe, se lanza una excepcion
            Direccion direccion = direccionService.obtenerDireccionPorId(id);
            return ResponseEntity.ok(direccion);
        } catch (Exception e) {
            // retorno codigo 404
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Obtener Direcciones por ID de Usuario",
               description = "Retorna una lista de direcciones asociadas a un ID de usuario específico.")
    @ApiResponse(responseCode = "200", description = "Direcciones encontradas y devueltas.",
                 content = @Content(schema = @Schema(implementation = Direccion.class)))
    @ApiResponse(responseCode = "404", description = "No se encontraron direcciones para el ID de usuario especificado o el usuario no existe.")
    @ApiResponse(responseCode = "204", description = "No hay direcciones para este usuario (lista vacía).")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al buscar las direcciones por usuario.")
    public ResponseEntity<List<Direccion>> obtenerDirPorUsuario(
    @Parameter(description = "ID del usuario para buscar sus direcciones", required = true, example = "10")    
    @PathVariable Long idUsuario) {
        List<Direccion> direccion = direccionService.obtenerDiByUsuario(idUsuario);

        if (direccion == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(direccion);
    }



    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una Dirección por ID",
               description = "Actualiza completamente un registro de dirección existente identificado por su ID. " +
                             "El nombre debe tener entre 5 y 50 caracteres.")
    @ApiResponse(responseCode = "200", description = "Dirección actualizada exitosamente y devuelta.",
                 content = @Content(schema = @Schema(implementation = Direccion.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida: datos de entrada erróneos o nombre inválido (fuera del rango de 5-50 caracteres).",
                 content = @Content(schema = @Schema(implementation = String.class, example = "El Nombre debe Contener entre 5 y 50 Caracteres y no puede estar vacío para la actualización.")))
    @ApiResponse(responseCode = "404", description = "Dirección no encontrada para actualizar.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al actualizar la dirección.")
    public ResponseEntity<?> actualizarDireccion(
    @Parameter(description = "ID de la dirección a actualizar", required = true, example = "1")    
    @PathVariable Long id, @RequestBody Direccion direccionActualizada) {
        try {
            // verificar si existe la direccion
            Direccion direccion = direccionService.obtenerDireccionPorId(id);
            // actualizar los campos de la direccion
            direccion.setNombre(direccionActualizada.getNombre());
            direccion.setComuna(direccionActualizada.getComuna());
            // guardar la direccion actualizada
            Direccion updatedDireccion = direccionService.saveDireccion(direccion);
            return ResponseEntity.ok(updatedDireccion);
        } catch (Exception e) {
            // retorno codigo 404
            return ResponseEntity.notFound().build();
        }
    }

    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una Dirección por ID",
               description = "Elimina permanentemente una dirección del sistema por su identificador único.")
    @ApiResponse(responseCode = "204", description = "Dirección eliminada exitosamente (sin contenido para devolver).")
    @ApiResponse(responseCode = "404", description = "Dirección no encontrada para eliminar.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al eliminar la dirección.")
    public ResponseEntity<?> eliminarDireccion(
    @Parameter(description = "ID de la dirección a eliminar", required = true, example = "1")    
    @PathVariable Long id) {
        try {
            // verificar si existe la direccion
            direccionService.obtenerDireccionPorId(id);
            // eliminar la direccion
            direccionService.eliminarDireccion(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // retorno codigo 404
            return ResponseEntity.notFound().build();
        }
    }

        //http://localhost:8083/doc/swagger-ui/index.html#/Contrataciones/obtenerContrataciones

}
