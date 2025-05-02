package com.musicband.payment.dto;

import java.util.UUID;

public record OrderStatusMsgDto(
        UUID orderId,
        String status,
        String email
) { }
