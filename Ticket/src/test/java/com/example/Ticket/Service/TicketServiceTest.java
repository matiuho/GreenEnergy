package com.example.Ticket.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Ticket.model.Ticket;
import com.example.Ticket.repository.TicketRepository;
import com.example.Ticket.service.TicketService;
import com.example.Ticket.webclient.SoporteClient;
import com.example.Ticket.webclient.UserClient;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private SoporteClient soporteClient;
    @Mock
    private UserClient userClient;
    @InjectMocks
    private TicketService ticketService;

    @Test
    void findAll_returnsListFromRepository() {
        List<Ticket> listaTickets = Arrays.asList(new Ticket(1L,"descripcion",null,null));;
        when(ticketRepository.findAll()).thenReturn(listaTickets);
        List<Ticket> result = ticketService.getTicekt();
        assertThat(result).isEqualTo(listaTickets);
    }
    @Test
    void save_returnsSavedTicket() {
        Ticket nuevoTicket = new Ticket(1L, "Soporte técnico", 1L, 1L);

        Map<String, Object> soporteMock = Map.of("idSoporte", 1L);
        Map<String, Object> usuarioMock = Map.of("idUsuario", 1L);

        when(soporteClient.getSoporteById(1L)).thenReturn(soporteMock);
        when(userClient.getUsuarioById(1L)).thenReturn(usuarioMock);
        when(ticketRepository.save(nuevoTicket)).thenReturn(nuevoTicket);

        Ticket resultado = ticketService.save(nuevoTicket);

        assertThat(resultado).isSameAs(nuevoTicket);
    }
    @Test
    void save_throwsExceptionWhenSoporteNotFound() {
        Ticket ticket = new Ticket(1L, "Soporte técnico", 1L, 1L);

        when(soporteClient.getSoporteById(1L)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> ticketService.save(ticket));
    }
    @Test
    void findById_returnsTicketById() {
        Long id = 1L;
        Ticket ticket = new Ticket(id, "Descripción", 1L, 1L);
        when(ticketRepository.findById(id)).thenReturn(Optional.of(ticket));

        Ticket result = ticketService.getTicketById(id);

        assertThat(result).isSameAs(ticket);
    }
    @Test
    void findByIdUsuario_returnsTicketList() {
        Long idUsuario = 1L;
        Ticket ticket = new Ticket(1L, "Ayuda técnica", 1L, idUsuario);
        List<Ticket> lista = Arrays.asList(ticket);

        when(ticketRepository.findByUsuario(idUsuario)).thenReturn(lista);

        List<Ticket> resultado = ticketService.obtenerTiByUsuario(idUsuario);

        assertThat(resultado).containsExactly(ticket);
    }
    @Test
    void findByIdSoporte_returnsTicketList() {
        Long idSoporte = 1L;
        Ticket ticket = new Ticket(1L, "Ayuda técnica", idSoporte, 1L);
        List<Ticket> lista = Arrays.asList(ticket);

        when(ticketRepository.findBySoporte(idSoporte)).thenReturn(lista);

        List<Ticket> resultado = ticketService.obtenerTiBySoporte(idSoporte);

        assertThat(resultado).containsExactly(ticket);
    }
    @Test
    void deleteById_removesTicket() {
        Long id = 1L;

        ticketService.eliminarPorId(id);

        verify(ticketRepository).deleteById(id);
    }
 
}
