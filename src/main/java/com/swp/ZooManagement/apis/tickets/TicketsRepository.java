package com.swp.ZooManagement.apis.tickets;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketsRepository extends JpaRepository<Ticket, Integer> {
    Optional<Ticket> findByName(String name);
}
