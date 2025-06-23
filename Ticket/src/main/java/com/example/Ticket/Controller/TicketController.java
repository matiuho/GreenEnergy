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


@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;


    
    // Endpoint para obtener todos los ticket
    @GetMapping
    public ResponseEntity<List<Ticket>> obtenerTicket() {
        List<Ticket> ticket = ticketService.getTicekt();
        if (ticket.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ticket);
    }

    
    @PostMapping
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
    public ResponseEntity<Ticket> obtenerTicketPorId(@PathVariable Long id) {
        try {
            Ticket ticket = ticketService.getTicketById(id);
            return ResponseEntity.ok(ticket);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/usuario/{idusuario}")
    public ResponseEntity<List<Ticket>> obtenerTicketPorUsuario(@PathVariable Long idusuario) {
        List<Ticket> ticket = ticketService.obtenerTiByUsuario(idusuario);
        if (ticket == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/soporte/{idsoporte}")
    public ResponseEntity<List<Ticket>> obtenerTicketPorSoporte(@PathVariable Long idsoporte) {
        List<Ticket> ticket = ticketService.obtenerTiBySoporte(idsoporte);
        if (ticket == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(ticket);
    }

    @DeleteMapping("/{id}")
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



}
