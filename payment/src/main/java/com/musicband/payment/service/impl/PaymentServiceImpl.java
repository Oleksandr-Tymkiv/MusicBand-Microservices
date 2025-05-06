package com.musicband.payment.service.impl;

import com.musicband.payment.dto.OrderDto;
import com.musicband.payment.dto.OrderMsgDto;
import com.musicband.payment.dto.OrderStatusMsgDto;
import com.musicband.payment.dto.PaymentDto;
import com.musicband.payment.entity.Payment;
import com.musicband.payment.exceptions.PaymentAlreadyExistsException;
import com.musicband.payment.exceptions.ResourceNotFoundException;
import com.musicband.payment.mappers.PaymentMapper;
import com.musicband.payment.repository.PaymentRepository;
import com.musicband.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private final PaymentRepository paymentRepository;
    private final StreamBridge streamBridge;

    @Override
    public void orderTicketCreate(OrderMsgDto ticketOrderMsgDto) {
        if(paymentRepository.findByOrderId(ticketOrderMsgDto.orderId()).isPresent()) {
            throw new PaymentAlreadyExistsException("Payment already exists");
        }
        Payment savedPayment = PaymentMapper.ticketOrderDtoToPayment(ticketOrderMsgDto, new Payment());
        paymentRepository.save(savedPayment);
        log.info("Payment ticket saved : {}", savedPayment);
    }


    @Override
    public void orderMerchCreate(OrderMsgDto merchOrderMsgDto) {
        if(paymentRepository.findByOrderId(merchOrderMsgDto.orderId()).isPresent()) {
            throw new PaymentAlreadyExistsException("Merch Order already exists");
        }
        Payment savedPayment = PaymentMapper.merchOrderDtoToPayment(merchOrderMsgDto, new Payment());
        paymentRepository.save(savedPayment);
        log.info("Payment merch saved : {}", savedPayment);
    }

    @Override
    public OrderDto getOrderInfo(UUID orderId) {
        return paymentRepository.findByOrderId(orderId).map(payment -> PaymentMapper.paymentToOrderDto(payment,new OrderDto())).orElseThrow(
                ()-> new ResourceNotFoundException("Order Payment", "orderId", orderId.toString())
        );
    }

    @Override
    public boolean completePayment(PaymentDto paymentDto) {
        return paymentRepository.findByOrderId(paymentDto.getOrderId())
                .map(payment -> {
                    if(isValidPaymentData(paymentDto)) {
                        payment.setStatus("PAID");
                    } else {
                        payment.setStatus("FAILED");
                    }
                    paymentRepository.save(payment);
                    orderStatusEvents(payment);
                    return "PAID".equals(payment.getStatus());
                })
                .orElse(false);
    }

    private void orderStatusEvents(Payment payment) {
        OrderStatusMsgDto orderStatusMsgDto = new OrderStatusMsgDto(
                payment.getOrderId(),
                payment.getStatus(),
                payment.getEmail()
        );

        log.info("Order status events: {}",orderStatusMsgDto);

        String bindingName = switch (payment.getOrderType()){
            case TICKET -> "orderTicketStatusPayment-out-0";
            case MERCH -> "orderMerchStatusPayment-out-0";
        };

        boolean result = streamBridge.send(bindingName, orderStatusMsgDto);
        log.info("Result of order status: {}",result);
    }

    private boolean isValidPaymentData(PaymentDto dto) {
        return dto.getCardNumber() != null && dto.getCardNumber().matches("\\d{16}")
                && dto.getCvv() != null && dto.getCvv().matches("\\d{3}")
                && isValidExpiryDate(dto.getExpiryDate());
    }

    private boolean isValidExpiryDate(String expiryDate) {
        if(expiryDate == null || !expiryDate.matches("\\d{2}/\\d{2}")) return false;
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            YearMonth cardExpiry = YearMonth.parse(expiryDate, formatter);
            YearMonth currentMonth = YearMonth.now();
            return !cardExpiry.isBefore(currentMonth);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
