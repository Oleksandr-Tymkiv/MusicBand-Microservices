package com.musicband.message.functions;

import com.musicband.message.dto.order.OrderMsgDto;
import com.musicband.message.dto.order.OrderStatusMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageOrderFunctions {

    private static final Logger log = LoggerFactory.getLogger(MessageOrderFunctions.class);

    @Bean
    public Function<OrderMsgDto, OrderMsgDto> orderTicket() {
        return ticketOrderMsgDto -> {
            log.info("Ticket order sent : {}", ticketOrderMsgDto);
            return ticketOrderMsgDto;
        };
    }

    @Bean
    public Function<OrderMsgDto, OrderMsgDto> orderMerch() {
        return merchOrderMsgDto -> {
            log.info("Merch order sent : {}", merchOrderMsgDto);
            return merchOrderMsgDto;
        };
    }

    @Bean
    public Function<OrderStatusMsgDto, OrderStatusMsgDto> orderTicketStatusCheck() {
        return orderTicketStatusMsgDto -> {
            log.info("Order ticket status check sent : {}", orderTicketStatusMsgDto);
            return orderTicketStatusMsgDto;
        };
    }

    @Bean
    public Function<OrderStatusMsgDto, OrderStatusMsgDto> orderMerchStatusCheck() {
        return orderMerchStatusMsgDto -> {
            log.info("Order merch status check sent : {}", orderMerchStatusMsgDto);
            return orderMerchStatusMsgDto;
        };
    }
}
