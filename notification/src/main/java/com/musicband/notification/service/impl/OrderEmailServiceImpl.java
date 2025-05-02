package com.musicband.notification.service.impl;

import com.musicband.notification.dto.OrderStatusMsgDto;
import com.musicband.notification.service.OrderEmailService;
import com.musicband.notification.service.impl.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEmailServiceImpl implements OrderEmailService {

    private final EmailService emailService;

    @Override
    public void sendStatusOrderEmail(OrderStatusMsgDto orderStatusMsgDto) {
        emailService.sendSimpleMail(orderStatusMsgDto.email(),"Order Status",infoStatus(orderStatusMsgDto.status()));
    }

    private String infoStatus(String status) {
        return status.equals("PAID")? "Your order completed" : "Your order not completed";
    }

}
