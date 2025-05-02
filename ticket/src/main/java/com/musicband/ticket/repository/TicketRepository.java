package com.musicband.ticket.repository;


import com.musicband.ticket.entity.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findTicketByPlaceAndTourId(String place, Long tourId);

    @Modifying
    @Transactional
    void deleteTicketsByTourId(Long tourId);
}
