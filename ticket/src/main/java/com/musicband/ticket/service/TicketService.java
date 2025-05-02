package com.musicband.ticket.service;

import com.musicband.ticket.dto.OrderStatusMsgDto;
import com.musicband.ticket.dto.TicketDto;
import com.musicband.ticket.dto.TicketOrderDto;

import java.util.List;

public interface TicketService {

    void addTicket(TicketDto ticketDto);

    void addTicketsForTour(Long tourId);

    List<TicketDto> getTickets();

    void removeTicketsOfTour(Long tourId);

    void orderTicket(TicketOrderDto ticketOrderDto);

    void changeStatus(OrderStatusMsgDto orderStatusMsgDto);
}
