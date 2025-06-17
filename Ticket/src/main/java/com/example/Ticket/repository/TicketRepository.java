package com.example.Ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Ticket.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
     @Query("SELECT t FROM Ticket t WHERE t.idusuario = :idusuario")
    List<Ticket> findByUsuario(@Param("idusuario") Long idusuario);

    @Query("SELECT t FROM Ticket t WHERE t.idsoporte = :idsoporte")
    List<Ticket> findBySoporte(@Param("idsoporte") Long idsoporte);


}
