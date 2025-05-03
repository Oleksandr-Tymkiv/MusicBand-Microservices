package com.musicband.merch.functions;

import com.musicband.merch.dto.OrderStatusMsgDto;
import com.musicband.merch.service.MerchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class MerchMsgFunctions {

    private static final Logger log = LoggerFactory.getLogger(MerchMsgFunctions.class);

    @Bean
    public Consumer<OrderStatusMsgDto> changeOrderStatus(MerchService merchService) {
        return status -> {
            log.info("new status : {}", status);
            merchService.changeStatus(status);
        };
    }
}
