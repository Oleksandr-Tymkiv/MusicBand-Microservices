package com.musicband.payment.service;

import com.musicband.payment.dto.OrderDto;
import com.musicband.payment.dto.PaymentDto;
import com.musicband.payment.dto.TicketOrderMsgDto;

import java.util.UUID;

public interface PaymentService {

    void orderTicketCreate(TicketOrderMsgDto ticketOrderMsgDto);

    OrderDto getOrderInfo(UUID orderId);

    boolean completePayment(PaymentDto paymentDto);
}
