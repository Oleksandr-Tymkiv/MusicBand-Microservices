package com.musicband.ticket.functions;

import com.musicband.ticket.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class TicketMsgFunction {

    private static final Logger log = LoggerFactory.getLogger(TicketMsgFunction.class);

    @Bean
    public Consumer<Long> createdTicketsForTour(TicketService ticketService) {
        return tour -> {
          log.info("tour sent : {}", tour);
          ticketService.addTicketsForTour(tour);
        };
    }
}
