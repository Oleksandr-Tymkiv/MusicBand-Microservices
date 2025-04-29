package com.musicband.ticket.service;

import com.musicband.ticket.dto.TicketDto;

import java.util.List;

public interface TicketService {

    void addTicket(TicketDto ticketDto);

    boolean purchaseTicket(TicketDto ticketDto);

    void addTicketsForTour(Long tourId);

    List<TicketDto> getTickets();

    boolean returnTicket(TicketDto ticketDto);
}
