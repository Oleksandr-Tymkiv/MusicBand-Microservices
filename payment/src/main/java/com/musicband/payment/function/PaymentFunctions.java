package com.musicband.payment.function;

import com.musicband.payment.dto.OrderMsgDto;
import com.musicband.payment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class PaymentFunctions {

    private static final Logger log = LoggerFactory.getLogger(PaymentFunctions.class);

    @Bean
    public Consumer<OrderMsgDto> orderTicketPayment(PaymentService paymentService) {
        return orderTicketPayment->{
            log.info("Order Ticket Payment : {}", orderTicketPayment);
            paymentService.orderTicketCreate(orderTicketPayment);
        };
    }

    @Bean
    public Consumer<OrderMsgDto> orderMerchPayment(PaymentService paymentService) {
        return orderMerchPayment->{
            log.info("Order Merch Payment : {}", orderMerchPayment);
            paymentService.orderMerchCreate(orderMerchPayment);
        };
    }
}
