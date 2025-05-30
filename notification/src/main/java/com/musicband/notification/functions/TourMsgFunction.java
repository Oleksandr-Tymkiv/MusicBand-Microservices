package com.musicband.notification.functions;

import com.musicband.notification.dto.OrderStatusMsgDto;
import com.musicband.notification.dto.TourMsgDto;
import com.musicband.notification.service.OrderEmailService;
import com.musicband.notification.service.TourEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class TourMsgFunction {

    private static final Logger log = LoggerFactory.getLogger(TourMsgFunction.class);

    @Bean
    public Consumer<TourMsgDto> tourUpdatedMessage(TourEmailService tourEmailService) {
        return tourUpdated -> {
            log.info("Updated tour sent : {}", tourUpdated);
            tourEmailService.tourUpdatedMessage(tourUpdated);
        };
    }

    @Bean
    public Consumer<TourMsgDto> tourDeletedMessage(TourEmailService tourEmailService) {
        return tourDeleted -> {
            log.info("Deleted tour sent : {}", tourDeleted);
            tourEmailService.tourDeletedMessage(tourDeleted);
        };
    }

    @Bean
    public Consumer<Long> ticketUpdatedMessage(TourEmailService tourEmailService) {
        return ticketUpdated -> {
            log.info("Updated ticket sent : {}", ticketUpdated);
            tourEmailService.ticketUpdatedMessage(ticketUpdated);
        };
    }

    @Bean
    public Consumer<OrderStatusMsgDto> orderTicketStatusMessage(OrderEmailService orderEmailService) {
        return orderTicketStatusMsg -> {
            log.info("Order ticket status sent : {}", orderTicketStatusMsg);
            orderEmailService.sendStatusOrderEmail(orderTicketStatusMsg);
        };
    }

    @Bean
    public Consumer<OrderStatusMsgDto> orderMerchStatusMessage(OrderEmailService orderEmailService) {
        return orderMerchStatusMsg -> {
            log.info("Order merch status sent : {}", orderMerchStatusMsg);
            orderEmailService.sendStatusOrderEmail(orderMerchStatusMsg);
        };
    }
}
