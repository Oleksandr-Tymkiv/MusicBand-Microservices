package com.musicband.notification.service;

import com.musicband.notification.dto.OrderStatusMsgDto;

public interface OrderEmailService {

    void sendStatusOrderEmail(OrderStatusMsgDto orderStatusMsgDto);

}
