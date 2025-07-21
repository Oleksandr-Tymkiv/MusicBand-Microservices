package com.musicband.ticket.service;

import com.musicband.ticket.dto.OrderStatusMsgDto;
import com.musicband.ticket.dto.TicketDto;
import com.musicband.ticket.dto.TicketOrderDto;
import com.musicband.ticket.entity.Ticket;

import java.util.List;
import java.util.UUID;

public interface TicketService {

    void addTicket(TicketDto ticketDto);

    void addTicketsForTour(Long tourId);

    List<TicketDto> getTickets();

    List<Ticket> getAvailableTickets();

    void removeTicketsOfTour(Long tourId);

    UUID orderTicket(TicketOrderDto ticketOrderDto);

    void changeStatus(OrderStatusMsgDto orderStatusMsgDto);
}
