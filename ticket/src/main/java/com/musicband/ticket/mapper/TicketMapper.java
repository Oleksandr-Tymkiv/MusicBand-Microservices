package com.musicband.ticket.mapper;

import com.musicband.ticket.dto.TicketDto;
import com.musicband.ticket.entity.Ticket;

public class TicketMapper {

    public static Ticket ticketDtoToTicket(TicketDto ticketDto, Ticket ticket) {
        ticket.setPrice(ticketDto.getPrice());
        ticket.setPlace(ticketDto.getPlace());
        ticket.setIsPurchase(ticketDto.getIsPurchase());
        ticket.setTourId(ticketDto.getTourId());
        return ticket;
    }

    public static TicketDto ticketToTicketDto(Ticket ticket, TicketDto ticketDto) {
        ticketDto.setPrice(ticket.getPrice());
        ticketDto.setPlace(ticket.getPlace());
        ticketDto.setIsPurchase(ticket.getIsPurchase());
        ticketDto.setTourId(ticket.getTourId());
        return ticketDto;
    }

}
