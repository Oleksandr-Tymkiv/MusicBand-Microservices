package com.musicband.payment.mappers;

import com.musicband.payment.dto.OrderDto;
import com.musicband.payment.dto.PaymentDto;
import com.musicband.payment.dto.TicketOrderMsgDto;
import com.musicband.payment.entity.Payment;

public class PaymentMapper {

    public static Payment ticketOrderDtoToPayment(TicketOrderMsgDto ticketOrderMsgDto, Payment payment) {
        payment.setOrderId(ticketOrderMsgDto.orderId());
        payment.setPrice(ticketOrderMsgDto.price());
        payment.setStatus("PENDING");
        payment.setEmail(ticketOrderMsgDto.userEmail());
        return payment;
    }

    public static OrderDto paymentToOrderDto(Payment payment, OrderDto orderDto) {
        orderDto.setPrice(payment.getPrice());
        return orderDto;
    }

}
