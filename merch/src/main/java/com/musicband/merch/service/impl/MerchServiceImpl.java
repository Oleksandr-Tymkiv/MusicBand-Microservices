package com.musicband.merch.service.impl;

import com.musicband.merch.dto.MerchDto;
import com.musicband.merch.dto.MerchOrderDto;
import com.musicband.merch.dto.MerchOrderMsgDto;
import com.musicband.merch.dto.OrderStatusMsgDto;
import com.musicband.merch.entity.Merch;
import com.musicband.merch.entity.MerchOrder;
import com.musicband.merch.mappers.MerchMapper;
import com.musicband.merch.mappers.MerchOrderMapper;
import com.musicband.merch.repository.MerchOrderRepository;
import com.musicband.merch.repository.MerchRepository;
import com.musicband.merch.service.MerchService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchServiceImpl implements MerchService {

    private static final Logger log = LoggerFactory.getLogger(MerchServiceImpl.class);
    private final MerchRepository merchRepository;
    private final MerchOrderRepository merchOrderRepository;
    private final StreamBridge streamBridge;

    @Override
    public void addMerch(MerchDto merchDto) {
        if(merchRepository.findByTitle(merchDto.getTitle()).isPresent()) {
            throw new IllegalStateException("Merch already exists");
        }

        Merch savedMerch = merchRepository.save(MerchMapper.merchDtoToMerch(merchDto,new Merch()));
        log.info("Saved Merch: {}", savedMerch);
    }

    @Override
    public List<MerchDto> getAllMerch() {
        return merchRepository.findAll().stream().map(merch -> MerchMapper.merchToMerchDto(merch, new MerchDto())).toList();
    }

    @Override
    @Transactional
    public void updateMerch(MerchDto merchDto) {
        merchRepository.findByTitle(merchDto.getTitle()).map(
                merch -> {
                    MerchMapper.merchDtoToMerch(merchDto, merch);
                    log.info("Merch updated: {}", merch);
                    return merchRepository.save(merch);
                }
        ).orElseThrow(() -> new IllegalStateException("Merch not found"));
    }

    @Override
    public void deleteMerch(Long id) {
        merchRepository.findById(id).ifPresentOrElse(
                merch -> {
                    merchRepository.delete(merch);
                    log.info("Merch deleted: {}", merch);
                },
                () -> {
                    throw new IllegalStateException("Merch not found");
                }
        );
    }

    @Override
    public void orderMerch(MerchOrderDto merchOrderDto) {
        MerchOrder merchOrder = merchOrderRepository.save(MerchOrderMapper.merchOrderDtoToMerchOrder(merchOrderDto, new MerchOrder()));
        orderMerchEvents(merchOrder);
        log.info("Saved merch order : {}",merchOrder);
    }

    private void orderMerchEvents(MerchOrder merchOrder) {
        Double merchPrice = merchRepository.findById(merchOrder.getMerchId()).map(Merch::getPrice).orElseThrow(
                ()-> new IllegalStateException("Merch whit id "+merchOrder.getMerchId()+" does not exist")
        );
        MerchOrderMsgDto merchOrderMsgDto = new MerchOrderMsgDto(
                merchOrder.getOrderId(),
                merchPrice,
                merchOrder.getUserEmail()
        );
        log.info("Order Merch events: {}",merchOrderMsgDto);
        boolean result = streamBridge.send("orderMerchEvents-out-0", merchOrderMsgDto);
        log.info("Result of order merch: {}",result);
    }

    @Override
    @Transactional
    public void changeStatus(OrderStatusMsgDto orderStatusMsgDto) {
        merchOrderRepository.findByOrderId(orderStatusMsgDto.orderId()).map(
                order->{
                    order.setStatus(orderStatusMsgDto.status());
                    log.info("Merch order status changed : {}",order);
                    return merchOrderRepository.save(order);
                }
        ).orElseThrow(
                ()-> new IllegalStateException("Merch order status changed does not exist")
        );

    }

}
