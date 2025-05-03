package com.musicband.payment.mappers;

import com.musicband.payment.dto.OrderDto;
import com.musicband.payment.dto.OrderMsgDto;
import com.musicband.payment.entity.OrderType;
import com.musicband.payment.entity.Payment;

public class PaymentMapper {

    public static Payment ticketOrderDtoToPayment(OrderMsgDto orderMsgDto, Payment payment) {
        payment.setOrderId(orderMsgDto.orderId());
        payment.setPrice(orderMsgDto.price());
        payment.setOrderType(OrderType.TICKET);
        payment.setStatus("PENDING");
        payment.setEmail(orderMsgDto.userEmail());
        return payment;
    }

    public static Payment merchOrderDtoToPayment(OrderMsgDto orderMsgDto, Payment payment) {
        payment.setOrderId(orderMsgDto.orderId());
        payment.setPrice(orderMsgDto.price());
        payment.setOrderType(OrderType.MERCH);
        payment.setStatus("PENDING");
        payment.setEmail(orderMsgDto.userEmail());
        return payment;
    }

    public static OrderDto paymentToOrderDto(Payment payment, OrderDto orderDto) {
        orderDto.setPrice(payment.getPrice());
        return orderDto;
    }

}
