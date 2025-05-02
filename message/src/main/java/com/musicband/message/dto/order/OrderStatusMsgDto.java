package com.musicband.message.dto.order;

import java.util.UUID;

public record OrderStatusMsgDto(
        UUID orderId,
        String status,
        String email
) { }
