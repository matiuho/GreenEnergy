package com.example.Ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Ticket.model.Ticket;
import com.example.Ticket.repository.TicketRepository;
import com.example.Ticket.webclient.UserClient;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserClient userClient;

    public Ticket getTicketPorId(Long id){
        return ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
    }

        //metodo para buscar un soporte por ID USUARIO
    public List<Ticket> obtenerTicketPorUsuario(Long idusuario){
        return ticketRepository.obtenerTicketPorUsuario(idusuario);
        
    }
    


}
