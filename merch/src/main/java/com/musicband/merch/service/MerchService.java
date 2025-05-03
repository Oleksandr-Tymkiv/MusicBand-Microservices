package com.musicband.merch.service;

import com.musicband.merch.dto.MerchDto;
import com.musicband.merch.dto.MerchOrderDto;
import com.musicband.merch.dto.OrderStatusMsgDto;

import java.util.List;

public interface MerchService {

    void addMerch(MerchDto merchDto);

    List<MerchDto> getAllMerch();

    void updateMerch(MerchDto merchDto);

    void deleteMerch(Long id);

    void orderMerch(MerchOrderDto merchOrderDto);

    void changeStatus(OrderStatusMsgDto orderStatusMsgDto);
}
