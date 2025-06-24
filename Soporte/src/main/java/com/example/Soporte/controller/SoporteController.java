package com.example.Soporte.controller;

import java.time.LocalDate;
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

import com.example.Soporte.model.Soporte;
import com.example.Soporte.service.SoporteService;
import com.example.Soporte.webclient.CategoriaClient;
import com.example.Soporte.webclient.EstadoClient;
import com.example.Soporte.webclient.UserClient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/soporte")
@Tag(name = "Soporte", description = "Operaciones relacionadas con los Soportes técnicos")
public class SoporteController {
    @Autowired
    private SoporteService soporteService;

    @Autowired
    CategoriaClient categoriaClient;

    @Autowired
    EstadoClient estadoClient;

    @Autowired
    UserClient userClient;

    // Endpoint para obtener todos los soportes
    @GetMapping
    @Operation(summary = "Obtener todos los soportes",
    description = "Retorna una lista de todos los registros de soporte existentes en el sistema.")

    @ApiResponse(responseCode = "200",
    description = "Soportes encontrados y devueltos.",
    content = @Content(schema = @Schema(implementation = Soporte.class)))

    @ApiResponse(responseCode = "204",
    description = "No hay soportes para devolver")

    @ApiResponse(responseCode = "500",
    description = "Error interno del servidor al intentar obtener los soportes.")
    public ResponseEntity<List<Soporte>> obtenerSoportes() {
        List<Soporte> soportes = soporteService.getSoporte();
        if (soportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(soportes);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo registro de soporte",
    description = "Registra un nuevo soporte en el sistema, aplicando validaciones de fecha y longitud de descripción.")

    @ApiResponse(responseCode = "201",
    description = "Soporte creado exitosamente.",
    content = @Content(schema = @Schema(implementation = Soporte.class)))

    @ApiResponse(responseCode = "400",
    description = "Solicitud inválida debido a fechas no permitidas o validaciones de longitud fallidas.",
    content = @Content(schema = @Schema(implementation = String.class, example = "La fecha de contratación debe ser igual o posterior al 27 de mayo de 2025.")))

    @ApiResponse(responseCode = "404",
    description = "Recurso asociado (ej. categoría, estado, usuario) no encontrado durante la creación.",
    content = @Content(schema = @Schema(implementation = String.class, example = "Categoría no encontrada para el ID proporcionado.")))

    @ApiResponse(responseCode = "500", description = "Error interno del servidor al crear el soporte.")
    public ResponseEntity<?> crearSoporte(@RequestBody Soporte nuevoSoporte) {
        LocalDate desde = LocalDate.of(2025, 5, 27);
        if (nuevoSoporte.getFecha().isBefore(desde)) {
            return ResponseEntity.badRequest()
                    .body("La fecha de contratación debe ser igual o posterior al 27 de mayo de 2025.");
        }
        if (nuevoSoporte.getDescripcion().length() < 1 || nuevoSoporte.getDescripcion().length() > 100) {
            return ResponseEntity.badRequest().body("La Descripccion debe Contener entre 1 y 100 Caracteres");
        }
        try {
            Soporte soporte = soporteService.saveSoporte(nuevoSoporte);
            return ResponseEntity.status(HttpStatus.CREATED).body(soporte);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } 
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un soporte por ID",
    description = "Busca y retorna un registro de soporte específico mediante su identificador único.")

    @ApiResponse(responseCode = "200",
    description = "Soporte encontrado y devuelto.",
    content = @Content(schema = @Schema(implementation = Soporte.class)))

    @ApiResponse(responseCode = "404",
    description = "El soporte con el ID especificado no fue encontrado.")

    @ApiResponse(responseCode = "500",
    description = "Error interno del servidor al buscar el soporte.")
    public ResponseEntity<Soporte> obtenerSoportePorId(@PathVariable Long id) {
        try {
            Soporte soporte = soporteService.getSoportePorId(id);
            return ResponseEntity.ok(soporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/usuario/{idusuario}")
    @Operation(summary = "Obtener soportes por ID de Usuario",
    description = "Busca y retorna una lista de registros de soporte asociados a un identificador de usuario específico.")

    @ApiResponse(responseCode = "200",
    description = "Soportes encontrados y devueltos.",
    content = @Content(schema = @Schema(implementation = Soporte.class)))

    @ApiResponse(responseCode = "404",
    description = "No se encontraron soportes para el ID de usuario especificado.")

    @ApiResponse(responseCode = "500",
    description = "Error interno del servidor al buscar los soportes.")
    public ResponseEntity<List<Soporte>> obtenerSoportePorUsuario(@PathVariable Long idusuario) {
        List<Soporte> soporte = soporteService.obtenerSoByUsuario(idusuario);
        if (soporte == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(soporte);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un registro de soporte",
    description = "Elimina un registro de soporte del sistema por su identificador único. Esta operación no retorna contenido.")

    @ApiResponse(responseCode = "204",
    description = "Soporte eliminado exitosamente")

    @ApiResponse(responseCode = "404",
    description = "El soporte con el ID especificado no fue encontrado para eliminar.")

    @ApiResponse(responseCode = "500",
    description = "Error interno del servidor al intentar eliminar el soporte.")
    public ResponseEntity<?> borrarSoportePorId(@PathVariable Long id) {
        try {
            // verificar si el soporte existe
            Soporte soporte = soporteService.getSoportePorId(id);
            soporteService.eliminarPorId(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // no existe el paciente
            return ResponseEntity.notFound().build();
        }

    }

    // metodo para actualizar un soporte por su id
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un registro de soporte existente",
    description = "Actualiza los detalles de un registro de soporte existente por su ID. El cuerpo de la solicitud debe contener los datos actualizados del soporte.")

    @ApiResponse(responseCode = "200", description = "Soporte actualizado exitosamente.",
    content = @Content(schema = @Schema(implementation = Soporte.class)))

    @ApiResponse(responseCode = "404",
    description = "El soporte con el ID especificado no fue encontrado para actualizar.")

    @ApiResponse(responseCode = "500",
    description = "Error interno del servidor al actualizar el soporte.")
    public ResponseEntity<Soporte> actualizarSoportePorId(@PathVariable Long id, @RequestBody Soporte sop) {
        try {
            // verifico si el soporte existe
            Soporte soporte2 = soporteService.getSoportePorId(id);
            // si existe modifico uno a uno sus valores
            soporte2.setDescripcion(sop.getDescripcion());
            soporte2.setIdEstado(sop.getIdEstado());

            // actualizar el soporte
            soporteService.saveSoporte(soporte2);
            return ResponseEntity.ok(soporte2);
        } catch (Exception e) {
            // si no encuentra el soporte
            return ResponseEntity.notFound().build();
        }
    }

    //http://localhost:8093/doc/swagger-ui/index.html#/Servicios/obtenerServiciosActivos
}
