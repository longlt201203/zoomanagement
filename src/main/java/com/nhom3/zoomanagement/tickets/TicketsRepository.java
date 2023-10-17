package com.nhom3.zoomanagement.tickets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketsRepository extends JpaRepository<Ticket, Integer> {
    
    @Query("SELECT distinct t.name from Ticket t")
    List<String> findDistinctTicketNames(); 
}
