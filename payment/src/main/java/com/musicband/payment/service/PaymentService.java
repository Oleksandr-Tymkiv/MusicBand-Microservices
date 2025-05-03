package com.musicband.payment.service;

import com.musicband.payment.dto.OrderDto;
import com.musicband.payment.dto.PaymentDto;
import com.musicband.payment.dto.OrderMsgDto;

import java.util.UUID;

public interface PaymentService {

    void orderTicketCreate(OrderMsgDto ticketOrderMsgDto);

    void orderMerchCreate(OrderMsgDto merchOrderMsgDto);

    OrderDto getOrderInfo(UUID orderId);

    boolean completePayment(PaymentDto paymentDto);
}
