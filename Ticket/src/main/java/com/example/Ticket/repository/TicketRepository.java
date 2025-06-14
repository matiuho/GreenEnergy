package com.example.Ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Ticket.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{

}
