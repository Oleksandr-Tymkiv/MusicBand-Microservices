package com.musicband.message.functions;

import com.musicband.message.dto.tour.TourMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageTourFunctions {

    private static final Logger log = LoggerFactory.getLogger(MessageTourFunctions.class);

    @Bean
    public Function<TourMsgDto,Long> tourCreated(){
        return tourMsgDto -> {
            log.info("Tour created : {}", tourMsgDto);
            return tourMsgDto.tourId();
        };
    }

    @Bean
    public Function<TourMsgDto,TourMsgDto> tourUpdated(){
        return tourMsgDto -> {
            log.info("Tour updated : {}", tourMsgDto);
            return tourMsgDto;
        };
    }

    @Bean
    public Function<TourMsgDto,TourMsgDto> tourDeleted(){
        return tourMsgDto -> {
            log.info("Tour deleted : {}", tourMsgDto);
            return tourMsgDto;
        };
    }

}
