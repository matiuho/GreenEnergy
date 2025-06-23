package com.example.Contrataciones.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Contrataciones.model.Contrataciones;
import com.example.Contrataciones.service.ContratacionesService;
import com.example.Contrataciones.webclient.ClienteClient;
import com.example.Contrataciones.webclient.DireccionClient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/contrataciones")
@Tag(name = "Contrataciones", description = "Operaciones relacionadas con las Contrataciones")
public class ContratacionesController {
    @Autowired
    private ContratacionesService contratacionesService;

    @Autowired
    ClienteClient clienteClient;

    @Autowired
    DireccionClient direccionClient;

    @GetMapping
    @Operation(summary = "Obtener todas las contrataciones",
               description = "Retorna una lista de todas las contrataciones existentes en el sistema.")
    @ApiResponse(responseCode = "200", description = "Las Contrataciones fueron encontradas y devueltas.",
                 content = @Content(schema = @Schema(implementation = Contrataciones.class)))
    @ApiResponse(responseCode = "204", description = "No hay contrataciones para devolver (lista vacía).")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al intentar obtener las contrataciones.")
    public ResponseEntity<List<Contrataciones>> obtenerContrataciones() {
        List<Contrataciones> contrataciones = contratacionesService.getContrataciones();
        if (contrataciones.isEmpty()) {
            // Retorno código 204 No Content
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contrataciones);
    }

    // endpoint para crear un nuevo proyecto
    @PostMapping
    @Operation(summary = "Crear una nueva Contratación",
               description = "Registra una nueva contratación de servicio, aplicando validaciones en las fechas. " +
                             "El cuerpo de la solicitud debe contener los datos completos de la contratación.")
    @ApiResponse(responseCode = "201", description = "Contratación creada exitosamente.",
                 content = @Content(schema = @Schema(implementation = Contrataciones.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida debido a fechas no permitidas o formato incorrecto.",
                 content = @Content(schema = @Schema(implementation = String.class, example = "La fecha de contratación debe ser igual o posterior al 27 de mayo de 2025."))) 
    @ApiResponse(responseCode = "404", description = "Recurso asociado (ej. cliente, dirección) no encontrado durante la creación.",
                 content = @Content(schema = @Schema(implementation = String.class, example = "Cliente no encontrado para el ID proporcionado."))) 
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al crear la contratación.")
    
    public ResponseEntity<?> crearContrataciones(@RequestBody Contrataciones nuevContrataciones) {
        LocalDate DESDE = LocalDate.of(2025, 5, 27);
        LocalDate despues = LocalDate.of(2025, 5, 28);
        if (nuevContrataciones.getFechaContratacion().isBefore(DESDE)) {
            return ResponseEntity.badRequest()
                    .body("La fecha de contratación debe ser igual o posterior al 27 de mayo de 2025.");
        }

        if (nuevContrataciones.getFechaInicio().isBefore(DESDE)) {
            return ResponseEntity.badRequest()
                    .body("La fecha de inicio debe ser posterior al 27 de mayo de 2025.");
        }

        if (nuevContrataciones.getFechaFin().isBefore(despues)) {
            return ResponseEntity.badRequest()
                    .body("La fecha de finalización debe ser posterior al 28 de mayo de 2025.");
        }
        try {
            Contrataciones contrataciones = contratacionesService.saveContrataciones(nuevContrataciones);
            return ResponseEntity.status(201).body(contrataciones);
        } catch (RuntimeException e) {
            // Captura error por estado no encontrado
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // endpoint para buscar una contratacion mediante su id
    @GetMapping("/{id}")
    public ResponseEntity<Contrataciones> obtenercontratacionPorId(@PathVariable Long id) {
        try {
            // verificar si existe el estado
            Contrataciones contratacion = contratacionesService.getContrtacionPorId(id);
            return ResponseEntity.ok(contratacion);
        } catch (Exception e) {
            // retorno codigo 404
            return ResponseEntity.notFound().build();
        }

    }

    // endpoint para buscar Contratcion por ID USUARIO
    @GetMapping("/usuario/{idusuario}")
    @Operation(summary = "Obtener Contratación por ID",
               description = "Busca y retorna una contratación específica mediante su identificador único.")
    @ApiResponse(responseCode = "200", description = "Contratación encontrada y devuelta.",
                 content = @Content(schema = @Schema(implementation = Contrataciones.class)))
    @ApiResponse(responseCode = "404", description = "La contratación con el ID especificado no fue encontrada.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al buscar la contratación.")
    public ResponseEntity<List<Contrataciones>> obtenerConPorUsuario(
        @Parameter(description = "ID de la contratación a buscar", required = true, example = "1")
        @PathVariable Long idusuario) {
        List<Contrataciones> contratacion = contratacionesService.obtenerContratacionByUsuario(idusuario);

        if (contratacion == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(contratacion);
    }

    //http://localhost:8087/doc/swagger-ui/index.html#/Contrataciones/obtenerContrataciones
    
    

}
