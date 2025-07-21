package com.musicband.merch.service;

import com.musicband.merch.dto.MerchDto;
import com.musicband.merch.dto.MerchOrderDto;
import com.musicband.merch.dto.OrderStatusMsgDto;
import com.musicband.merch.entity.Merch;

import java.util.List;
import java.util.UUID;

public interface MerchService {

    void addMerch(MerchDto merchDto);

    List<Merch> getAllMerch();

    void updateMerch(MerchDto merchDto);

    void deleteMerch(Long id);

    UUID orderMerch(MerchOrderDto merchOrderDto);

    void changeStatus(OrderStatusMsgDto orderStatusMsgDto);
}
