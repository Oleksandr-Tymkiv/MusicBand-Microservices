package com.musicband.merch.dto;

import java.util.UUID;

public record MerchOrderMsgDto(
        UUID orderId,
        Double price,
        String userEmail
){}
