package com.example.Resena.Controller;

import java.time.LocalDate;
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

import com.example.Resena.Model.Resena;
import com.example.Resena.Service.ResenaService;
import com.example.Resena.WebClient.ServicioClient;
import com.example.Resena.WebClient.UserClient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/resena")
@Tag(name = "Reseñas", description = "Operaciones relacionadas con la gestión de Reseñas de servicios.")
public class ResenaController {
    @Autowired
    ResenaService resenaService;
    @Autowired
    UserClient userClient;
    @Autowired
    ServicioClient servicioClient;

    @GetMapping
    @Operation(summary = "Obtener todas las Reseñas activas",
    description = "Retorna una lista de todas las reseñas que están marcadas como activas en el sistema.")

    @ApiResponse(responseCode = "200",
    description = "Las Reseñas activas fueron encontradas y devueltas.",
    content = @Content(schema = @Schema(implementation = Resena.class)))

    @ApiResponse(responseCode = "204",
    description = "No hay reseñas activas para devolver (lista vacía).")

    @ApiResponse(responseCode = "500", description = "Error interno del servidor al intentar obtener las reseñas.")
    public ResponseEntity<List<Resena>> obtenerResenas() {
        List<Resena> resenas = resenaService.listarResenasActivas();
        if (resenas.isEmpty()) {
            // Retorno código 204 No Content
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resenas);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva Reseña",
    description = "Registra una nueva reseña en el sistema. Se aplican validaciones a la fecha y el comentario.")
    @ApiResponse(responseCode = "201",
    description = "Reseña creada exitosamente.",
    content = @Content(schema = @Schema(implementation = Resena.class))) 

    @ApiResponse(responseCode = "400",
    description = "Solicitud inválida: la fecha o el comentario no cumplen con los requisitos.",
    content = @Content(schema = @Schema(implementation = String.class,
    example = "El comentario debe tener entre 1 y 100 caracteres."))) 

    @ApiResponse(responseCode = "404",
    description = "Recurso asociado (ej. usuario o servicio) no encontrado.",
    content = @Content(schema = @Schema(implementation = String.class,
    example = "El usuario o servicio especificado no existe.")))

    @ApiResponse(responseCode = "500", description = "Error interno del servidor al crear la reseña.")
    public ResponseEntity<?> crearResena(@RequestBody Resena nuevResena) {
        LocalDate despues = LocalDate.of(2025, 5, 28);
        if (nuevResena.getFechaComentario().isBefore(despues)) {
            return ResponseEntity.badRequest()
                    .body("La fecha del Comentario debe ser igual o posterior al 28 de mayo de 2025.");
        }
        if (nuevResena.getComentario().length() < 1 || nuevResena.getComentario().length() > 100) {
            return ResponseEntity.badRequest().body("El comentario debe tener entre 1 y 100 caracteres.");
        }
        try {
            Resena resena = resenaService.savResena(nuevResena);
            return ResponseEntity.status(201).body(resena);
        } catch (RuntimeException e) {
            // Captura error por estado no encontrado
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Reseña por ID",
    description = "Busca y retorna una reseña específica mediante su identificador único.")

    @ApiResponse(responseCode = "200",
    description = "La Reseña fue encontrada y devuelta.",
    content = @Content(schema = @Schema(implementation = Resena.class)))

    @ApiResponse(responseCode = "404",
    description = "La Reseña con el ID especificado no fue encontrada.")

    @ApiResponse(responseCode = "500",
    description = "Error interno del servidor al buscar la reseña.")
    public ResponseEntity<Resena> obtenerProyectoPorId(
    @Parameter(description = "ID de la reseña a buscar", required = true, example = "1")    
    @PathVariable Long id) {
        try {
            // verificar si existe el estado
            Resena resena = resenaService.getResenaPorId(id);
            return ResponseEntity.ok(resena);
        } catch (Exception e) {
            // retorno codigo 404
            return ResponseEntity.notFound().build();
        }

    }

    // endpoint para buscar Resena por ID USUARIO
    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Obtener Reseñas por ID de Usuario",
    description = "Retorna una lista de reseñas asociadas a un ID de usuario específico.")

    @ApiResponse(responseCode = "200",
    description = "Reseñas encontradas y devueltas.",
    content = @Content(schema = @Schema(implementation = Resena.class)))

    @ApiResponse(responseCode = "204",
    description = "No se encontraron reseñas para el ID de usuario especificado")

    @ApiResponse(responseCode = "500",
    description = "Error interno del servidor al buscar las reseñas por usuario.")
    public ResponseEntity<List<Resena>> obtenerReByUsuario(
    @Parameter(description = "ID del usuario para buscar sus reseñas", required = true, example = "5")    
    @PathVariable Long idUsuario) {
        List<Resena> resenas = resenaService.obtenerReByUsuario(idUsuario);

        if (resenas == null || resenas.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        return ResponseEntity.ok(resenas); // 200 OK con contenido
    }

    @PutMapping("/desactivar/{id}")
    @Operation(summary = "Desactivar una Reseña por ID",
    description = "Cambia el estado de una reseña a 'inactiva' mediante su identificador único. " +
                  "Esto generalmente oculta la reseña sin eliminarla permanentemente.")

    @ApiResponse(responseCode = "200",
    description = "Reseña desactivada exitosamente y devuelta.",
    content = @Content(schema = @Schema(implementation = Resena.class)))

    @ApiResponse(responseCode = "404",
    description = "Reseña no encontrada para desactivar.")

    @ApiResponse(responseCode = "500",
    description = "Error interno del servidor al intentar desactivar la reseña.")
    public ResponseEntity<?> desactivarResena(
    @Parameter(description = "ID de la reseña a desactivar", required = true, example = "1")    
    @PathVariable Long id) {
        try {
            Resena desactivada = resenaService.desactivarResena(id);
            return ResponseEntity.ok(desactivada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("No se pudo desactivar: " + e.getMessage());
        }
    }
    //http://localhost:8094/doc/swagger-ui/index.html#/Proyectos/obtenerProyectos
}
