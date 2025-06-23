package com.example.Ticket.Controller;

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

import com.example.Ticket.model.Ticket;
import com.example.Ticket.service.TicketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/ticket")
@Tag(name = "Ticket", description = "Operaciones relacionadas con los Tickets de soporte")
public class TicketController {
    @Autowired
    private TicketService ticketService;


    
    // Endpoint para obtener todos los ticket
    @GetMapping
    @Operation(summary = "Obtener todos los tickets",
               description = "Retorna una lista de todos los tickets existentes en el sistema.")
    @ApiResponse(responseCode = "200", description = "Tickets encontrados y devueltos.",
                 content = @Content(schema = @Schema(implementation = Ticket.class)))
    @ApiResponse(responseCode = "204", description = "No hay tickets para devolver (lista vacía).")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al intentar obtener los tickets.")
    public ResponseEntity<List<Ticket>> obtenerTicket() {
        List<Ticket> ticket = ticketService.getTicekt();
        if (ticket.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ticket);
    }

    
    @PostMapping
    @Operation(summary = "Crear un nuevo ticket",
               description = "Registra un nuevo ticket en el sistema, aplicando validaciones de longitud para la descripción.")
    @ApiResponse(responseCode = "201", description = "Ticket creado exitosamente.",
                 content = @Content(schema = @Schema(implementation = Ticket.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida debido a validaciones de longitud fallidas.",
                 content = @Content(schema = @Schema(implementation = String.class, example = "La Descripccion debe Contener entre 5 y 100 Caracteres")))
    @ApiResponse(responseCode = "404", description = "Recurso asociado no encontrado durante la creación (ej. si hay dependencias no encontradas).",
                 content = @Content(schema = @Schema(implementation = String.class, example = "Error al guardar el ticket: [Mensaje del error]")))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al crear el ticket.")
    public ResponseEntity<?> crearTicket(@RequestBody Ticket nuevoTicket) {
        if (nuevoTicket.getDescripcion().length() < 5 || nuevoTicket.getDescripcion().length() > 100) {
            return ResponseEntity.badRequest().body("La Descripccion debe Contener entre 5 y 100 Caracteres");
        }
        try {
            Ticket Ticket = ticketService.save(nuevoTicket);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTicket);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } 
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un ticket por ID",
               description = "Busca y retorna un ticket específico mediante su identificador único.")
    @ApiResponse(responseCode = "200", description = "Ticket encontrado y devuelto.",
                 content = @Content(schema = @Schema(implementation = Ticket.class)))
    @ApiResponse(responseCode = "404", description = "El ticket con el ID especificado no fue encontrado.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al buscar el ticket.")
    public ResponseEntity<Ticket> obtenerTicketPorId(@PathVariable Long id) {
        try {
            Ticket ticket = ticketService.getTicketById(id);
            return ResponseEntity.ok(ticket);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/usuario/{idusuario}")
    @Operation(summary = "Obtener tickets por ID de Usuario",
               description = "Busca y retorna una lista de tickets asociados a un identificador de usuario específico.")
    @ApiResponse(responseCode = "200", description = "Tickets encontrados y devueltos.",
                 content = @Content(schema = @Schema(implementation = Ticket.class)))
    @ApiResponse(responseCode = "404", description = "No se encontraron tickets para el ID de usuario especificado.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al buscar los tickets.")
    public ResponseEntity<List<Ticket>> obtenerTicketPorUsuario(@PathVariable Long idusuario) {
        List<Ticket> ticket = ticketService.obtenerTiByUsuario(idusuario);
        if (ticket == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/soporte/{idsoporte}")
    @Operation(summary = "Obtener tickets por ID de Soporte",
               description = "Busca y retorna una lista de tickets asociados a un identificador de soporte específico.")
    @ApiResponse(responseCode = "200", description = "Tickets encontrados y devueltos.",
                 content = @Content(schema = @Schema(implementation = Ticket.class)))
    @ApiResponse(responseCode = "404", description = "No se encontraron tickets para el ID de soporte especificado.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al buscar los tickets.")
    public ResponseEntity<List<Ticket>> obtenerTicketPorSoporte(@PathVariable Long idsoporte) {
        List<Ticket> ticket = ticketService.obtenerTiBySoporte(idsoporte);
        if (ticket == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(ticket);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un ticket",
               description = "Elimina un ticket del sistema por su identificador único. Esta operación no retorna contenido.")
    @ApiResponse(responseCode = "204", description = "Ticket eliminado exitosamente (No Content).")
    @ApiResponse(responseCode = "404", description = "El ticket con el ID especificado no fue encontrado para eliminar.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al intentar eliminar el ticket.")
    public ResponseEntity<?> borrarTicketPorId(@PathVariable Long id) {
        try {
            // verificar si el soporte existe
            Ticket ticket = ticketService.getTicketById(id);
            ticketService.eliminarPorId(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // no existe el paciente
            return ResponseEntity.notFound().build();
        }

    }

    // metodo para actualizar un ticket por su id
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un ticket existente",
               description = "Actualiza los detalles de un ticket existente por su ID. El cuerpo de la solicitud debe contener los datos actualizados del ticket.")
    @ApiResponse(responseCode = "200", description = "Ticket actualizado exitosamente.",
                 content = @Content(schema = @Schema(implementation = Ticket.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida debido a validaciones de longitud fallidas.",
                 content = @Content(schema = @Schema(implementation = String.class, example = "La Descripccion debe Contener entre 5 y 100 Caracteres")))
    @ApiResponse(responseCode = "404", description = "El ticket con el ID especificado no fue encontrado para actualizar.")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor al actualizar el ticket.")
    public ResponseEntity<Ticket> actualizarTicketPorId(@PathVariable Long id, @RequestBody Ticket tic) {
        try {
            // verifico si el ticket existe
            Ticket ticket2 = ticketService.getTicketById(id);
            // si existe modifico uno a uno sus valores
            
            ticket2.setDescripcion(tic.getDescripcion());

            // actualizar el ticket
            ticketService.save(ticket2);
            return ResponseEntity.ok(ticket2);
        } catch (Exception e) {
            // si no encuentra el paciente
            return ResponseEntity.notFound().build();
        }
    }

    //http://localhost:8098/doc/swagger-ui/index.html#/Servicios/obtenerServiciosActivos


}
