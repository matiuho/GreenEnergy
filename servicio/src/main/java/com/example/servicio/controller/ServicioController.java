package com.example.servicio.controller;

import com.example.servicio.model.Servicio;
import com.example.servicio.service.ServicioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
@Tag(name = "Servicios", description = "Operaciones relacionadas con los Servicios ofrecidos")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    // Obtener todos los servicios activos
    @GetMapping
    @Operation(summary = "Obtener todos los servicios activos",
    description = "Retorna una lista de todos los servicios que actualmente están activos en el sistema.")

    @ApiResponse(responseCode = "200",
    description = "Servicios activos encontrados y devueltos.",
    content = @Content(schema = @Schema(implementation = Servicio.class)))

    @ApiResponse(responseCode = "204",
    description = "No hay servicios activos para devolver (lista vacía).")

    @ApiResponse(responseCode = "500", description = "Error interno del servidor al intentar obtener los servicios activos.")
    public ResponseEntity<List<Servicio>> obtenerServiciosActivos() {
        List<Servicio> activos = servicioService.listarServiciosActivos();
        return activos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(activos);
    }

    // Obtener un servicio por ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un servicio por ID",
    description = "Busca y retorna un servicio específico mediante su identificador único.")

    @ApiResponse(responseCode = "200",
    description = "Servicio encontrado y devuelto.",
    content = @Content(schema = @Schema(implementation = Servicio.class)))
    @ApiResponse(responseCode = "404",
    description = "El servicio con el ID especificado no fue encontrado.")

    @ApiResponse(responseCode = "500", description = "Error interno del servidor al buscar el servicio.")
    public ResponseEntity<Servicio> obtenerPorId(@PathVariable Long id) {
        try {
            // verificar si el servicio existe
            Servicio servicio = servicioService.getServicioPorId(id);
            return ResponseEntity.ok(servicio);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo servicio
    @PostMapping
    @Operation(summary = "Crear un nuevo servicio",
    description = "Registra un nuevo servicio en el sistema, aplicando validaciones de longitud para nombre y descripción.")

    @ApiResponse(responseCode = "201",
    description = "Servicio creado exitosamente.",
    content = @Content(schema = @Schema(implementation = Servicio.class)))

    @ApiResponse(responseCode = "400",
    description = "Solicitud inválida debido a validaciones de longitud fallidas.",
    content = @Content(schema = @Schema(implementation = String.class,
    example = "El Nombre debe Contener entre 1 y 20 Caracteres")))

    @ApiResponse(responseCode = "500",
    description = "Error interno del servidor al crear el servicio.")
    public ResponseEntity<?> crearservicio(@RequestBody Servicio servicio) {
        // Validar
        if (servicio.getNombre().length() < 1 || servicio.getNombre().length() > 20) {
            return ResponseEntity.badRequest().body("El Nombre debe Contener entre 1 y 20 Caracteres");
        }
        if (servicio.getDescripcion().length() < 1 || servicio.getDescripcion().length() > 100) {
            return ResponseEntity.badRequest().body("La Descripcicion debe Contener entre 1 y 100 Caracteres");
        }
        Servicio nuevoServicio = servicioService.saveServicio(servicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoServicio);
    }

    // Actualizar un servicio existente
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un servicio existente",
    description = "Actualiza los detalles de un servicio existente por su ID. El cuerpo de la solicitud debe contener los datos actualizados del servicio.")

    @ApiResponse(responseCode = "200",
    description = "Servicio actualizado exitosamente.",
    content = @Content(schema = @Schema(implementation = Servicio.class)))

    @ApiResponse(responseCode = "404",
    description = "El servicio con el ID especificado no fue encontrado para actualizar.")

    @ApiResponse(responseCode = "500",
    description = "Error interno del servidor al actualizar el servicio.")
    public ResponseEntity<?> actualizarDireccion(@PathVariable Long id, @RequestBody Servicio servicioActualizada) {
        try {
            // verificar si existe la servicio
            Servicio servicio = servicioService.getServicioPorId(id);
            // actualizar los campos del servicio
            servicio.setNombre(servicioActualizada.getNombre());
            servicio.setDescripcion(servicioActualizada.getDescripcion());
            servicio.setPrecio(servicioActualizada.getPrecio());
            // guardar el servicio actualizado
            Servicio updatedDireccion = servicioService.saveServicio(servicio);
            return ResponseEntity.ok(updatedDireccion);
        } catch (Exception e) {
            // retorno codigo 404
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un servicio
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un servicio",
    description = "Elimina un servicio del sistema por su identificador único. Esta operación no retorna contenido.")

    @ApiResponse(responseCode = "204",
    description = "Servicio eliminado exitosamente (No Content).")

    @ApiResponse(responseCode = "404",
    description = "El servicio con el ID especificado no fue encontrado para eliminar.")

    @ApiResponse(responseCode = "500",
    description = "Error interno del servidor al intentar eliminar el servicio.")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicioService.eliminarservicio(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/desactivar/{id}")
    @Operation(summary = "Desactivar un servicio",
    description = "Cambia el estado de un servicio a inactivo mediante su ID.")

    @ApiResponse(responseCode = "200",
    description = "Servicio desactivado exitosamente.",
    content = @Content(schema = @Schema(implementation = Servicio.class)))

    @ApiResponse(responseCode = "404",
    description = "El servicio no fue encontrado o no se pudo desactivar.")

    @ApiResponse(responseCode = "500", description = "Error interno del servidor al intentar desactivar el servicio.")
    public ResponseEntity<?> desactivarServicio(@PathVariable Long id) {
        try {
            Servicio servicioDesactivado = servicioService.desactivarServicio(id);
            return ResponseEntity.ok(servicioDesactivado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("No se pudo desactivar: " + e.getMessage());
        }
    }


    @PutMapping("/activar/{idServicio}")
    @Operation(summary = "Activar un servicio",
    description = "Cambia el estado de un servicio a activo mediante su ID.")

    @ApiResponse(responseCode = "200",
    description = "Servicio activado exitosamente.",
    content = @Content(schema = @Schema(implementation = Servicio.class)))

    @ApiResponse(responseCode = "404",
    description = "El servicio no fue encontrado o no se pudo activar.")

    @ApiResponse(responseCode = "500",
    description = "Error interno del servidor al intentar activar el servicio.")
    public ResponseEntity<?> activarServicio(@PathVariable("idServicio") Long id) {
        try {
            Servicio activado = servicioService.activarServicio(id);
            return ResponseEntity.ok(activado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("No se pudo activar: " + e.getMessage());
        }
    }
    //http://localhost:8089/doc/swagger-ui/index.html#/Servicios/obtenerServiciosActivos

}