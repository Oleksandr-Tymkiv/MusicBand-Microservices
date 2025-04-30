package com.musicband.message.functions;

import com.musicband.message.dto.ticket.TicketMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageTicketFunctions {

    private static final Logger log = LoggerFactory.getLogger(MessageTicketFunctions.class);

    @Bean
    public Function<TicketMsgDto, Long> newTicketsForTour(){
        return ticketMsgDto-> {
            log.info("New tickets tour : {}", ticketMsgDto);
            return ticketMsgDto.tourId();
        };
    }

}
