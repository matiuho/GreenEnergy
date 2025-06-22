package com.example.Ticket.Controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Arrays;
import java.util.List;

import com.example.Ticket.model.Ticket;
import com.example.Ticket.service.TicketService;
import com.example.Ticket.webclient.SoporteClient;
import com.example.Ticket.webclient.UserClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TicketController.class)
public class TicketControllerTest {

    @MockBean
    private TicketService ticketService;
    @MockBean
    private SoporteClient soporteClient;
    @MockBean
    private UserClient userClient;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllTickets_returnsOkAndJson() throws Exception {
        List<Ticket> listaTickets = Arrays.asList(new Ticket(1L, "Soporte técnico", 1L, 1L));

        when(ticketService.getTicekt()).thenReturn(listaTickets);

        mockMvc.perform(get("/api/ticket"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    void crearTicket_returnsCreatedAndJson() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setDescripcion("Problema de red");
        ticket.setIdsoporte(1L);
        ticket.setIdusuario(1L);

        when(ticketService.save(ticket)).thenReturn(ticket);

        mockMvc.perform(post("/api/ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.descripcion").value("Problema de red"))
                .andExpect(jsonPath("$.idsoporte").value(1L))
                .andExpect(jsonPath("$.idusuario").value(1L));
    }

    @Test
    void getTicketPorId_returnsOkAndJson() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setDescripcion("Problema urgente");
        ticket.setIdsoporte(1L);
        ticket.setIdusuario(1L);

        when(ticketService.getTicketById(1L)).thenReturn(ticket);

        try {
            mockMvc.perform(get("/api/ticket/{id}", 1L))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1L));
        } catch (Exception e) {
        }
    }

    @Test
    void getTicketsByUsuario_returnsOkAndJson() {
        List<Ticket> tickets = Arrays.asList(new Ticket(1L, "Red", 1L, 1L));

        when(ticketService.obtenerTicketPorUsuario(1L)).thenReturn(tickets);

        try {
            mockMvc.perform(get("/api/ticket/usuario/{idusuario}", 1L))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].idusuario").value(1L));
        } catch (Exception e) {
        }
    }

    @Test
    void getTicketsBySoporte_returnsOkAndJson() {
        List<Ticket> tickets = Arrays.asList(new Ticket(1L, "Soporte de red", 1L, 1L));

        when(ticketService.obtenerTicketPorSoporte(1L)).thenReturn(tickets);

        try {
            mockMvc.perform(get("/api/ticket/soporte/{idsoporte}", 1L))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].idsoporte").value(1L));
        } catch (Exception e) {
        }
    }
    @Test
    void eliminarTicket_returnsNoContent() throws Exception {
        Long id = 1L;

        doNothing().when(ticketService).eliminarPorId(id);

        mockMvc.perform(delete("/api/ticket/{id}", id))
                .andExpect(status().isNoContent());
    }

    

}