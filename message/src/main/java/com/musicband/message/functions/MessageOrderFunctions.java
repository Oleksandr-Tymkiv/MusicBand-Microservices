package com.musicband.message.functions;

import com.musicband.message.dto.order.OrderStatusMsgDto;
import com.musicband.message.dto.order.TicketOrderMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageOrderFunctions {

    private static final Logger log = LoggerFactory.getLogger(MessageOrderFunctions.class);

    @Bean
    public Function<TicketOrderMsgDto, TicketOrderMsgDto> orderTicket() {
        return ticketOrderMsgDto -> {
            log.info("Ticket order sent : {}", ticketOrderMsgDto);
            return ticketOrderMsgDto;
        };
    }

    @Bean
    public Function<OrderStatusMsgDto, OrderStatusMsgDto> orderStatusCheck() {
        return orderStatusMsgDto -> {
            log.info("Order status check sent : {}", orderStatusMsgDto);
            return orderStatusMsgDto;
        };
    }
}
