package com.example.Ticket.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Ticket.model.Ticket;
import com.example.Ticket.repository.TicketRepository;
import com.example.Ticket.webclient.SoporteClient;
import com.example.Ticket.webclient.UserClient;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private SoporteClient soporteClient;

   //metodo para consultar todos los Ticket
    public List<Ticket> getTicekt(){
        return ticketRepository.findAll();
    }

    public Ticket save(Ticket nuevotTicket) {

        //verificar si el soporte existe 
        Map<String, Object> soporte  = soporteClient.getSoporteById(nuevotTicket.getIdsoporte());
        //verifico si me trajo el Soporte o no
        if (soporte == null || soporte.isEmpty()) {
            throw new RuntimeException("Soporte no encontrado");
        }

        //verificar si el usuario existe consultando al microservicio de usuario
        Map<String, Object> usuario = userClient.getUsuarioById(nuevotTicket.getIdusuario());
        //verifico si me trajo el usuario o no
        if (usuario == null || usuario.isEmpty()) {
            throw new RuntimeException("Usuario no encontrada");
        }
        

        return ticketRepository.save(nuevotTicket);
    }



    public List<Ticket> obtenerTiByUsuario(Long idusuario) {
        List<Ticket> ticket = ticketRepository.findByUsuario(idusuario);
        if (ticket == null || ticket.isEmpty()) {
            throw new RuntimeException("No se encontraron Ticket para el usuario con ID: " + idusuario);
        }
        return ticket;
    }

    public List<Ticket> obtenerTiBySoporte(Long idsoporte) {
        List<Ticket> ticket = ticketRepository.findBySoporte(idsoporte);
        if (ticket == null || ticket.isEmpty()) {
            throw new RuntimeException("No se encontraron Ticket para el soporte con ID: " + idsoporte);
        }
        return ticket;
    }

    //eliminar un ticket
    public void eliminarPorId(Long id) {
        ticketRepository.deleteById(id);
    }

    // buscar ticket por id
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
    }


    


}
