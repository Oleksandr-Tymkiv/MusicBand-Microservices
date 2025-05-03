package com.musicband.merch.mappers;

import com.musicband.merch.dto.MerchOrderDto;
import com.musicband.merch.entity.MerchOrder;

import java.util.UUID;

public class MerchOrderMapper {

    public static MerchOrder merchOrderDtoToMerchOrder(MerchOrderDto merchOrderDto, MerchOrder merchOrder) {
        UUID orderId = UUID.randomUUID();
        merchOrder.setOrderId(orderId);
        merchOrder.setMerchId(merchOrderDto.getMerchId());
        merchOrder.setStatus("CREATED");
        merchOrder.setUserEmail(merchOrderDto.getUserEmail());
        return merchOrder;
    }

}
