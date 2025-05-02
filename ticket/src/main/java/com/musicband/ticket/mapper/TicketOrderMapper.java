package com.musicband.ticket.mapper;


import com.musicband.ticket.dto.TicketOrderDto;
import com.musicband.ticket.entity.TicketOrder;

import java.util.UUID;

public class TicketOrderMapper {

    public static TicketOrder ticketOrderDtoToTicketOrder(TicketOrderDto ticketOrderDto, TicketOrder ticketOrder) {
        UUID orderId = UUID.randomUUID();
        ticketOrder.setOrderId(orderId);
        ticketOrder.setTicketId(ticketOrderDto.getTicketId());
        ticketOrder.setStatus("CREATED");
        ticketOrder.setUserEmail(ticketOrderDto.getUserEmail());
        return ticketOrder;
    }

}
