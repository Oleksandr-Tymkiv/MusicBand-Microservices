package com.musicband.payment.dto;

import java.util.UUID;

public record OrderMsgDto(
        UUID orderId,
        Double price,
        String userEmail
){}
