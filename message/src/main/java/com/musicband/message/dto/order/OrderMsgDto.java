package com.musicband.message.dto.order;

import java.util.UUID;

public record OrderMsgDto(
        UUID orderId,
        Double price,
        String userEmail
){}
