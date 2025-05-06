package com.musicband.tour.service.impl;

import com.musicband.tour.dto.TourDto;
import com.musicband.tour.dto.TourMsgDto;
import com.musicband.tour.entity.Tour;
import com.musicband.tour.exceptions.ResourceNotFoundException;
import com.musicband.tour.exceptions.TourAlreadyExistsException;
import com.musicband.tour.mappers.TourMapper;
import com.musicband.tour.repository.TourRepository;
import com.musicband.tour.service.TourService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private static final Logger log = LoggerFactory.getLogger(TourServiceImpl.class);
    private final StreamBridge streamBridge;


    @Override
    public void addTour(TourDto tourDto) {
        if(tourRepository.findByTitle(tourDto.getTitle()).isPresent()){
            throw new TourAlreadyExistsException("Tour already exists");
        }

        Tour savedTour = tourRepository.save(TourMapper.tourDtoToTour(tourDto, new Tour()));
        tourCreatedEvents(savedTour);
        log.info("Tour added successfully : {}", savedTour);
    }

    private void tourCreatedEvents(Tour tour){
        TourMsgDto tourMsgDto = new TourMsgDto(
                tour.getTourId(),
                tour.getTitle(),
                tour.getTourDate(),
                tour.getCountry(),
                tour.getArea()
        );
        log.info("Tour created events: {}",tourMsgDto);
        boolean result = streamBridge.send("tourCreatedEvents-out-0", tourMsgDto);
        log.info("Result of creating : {}",result);
    }

    @Override
    public void updateTour(TourDto tourDto) {
        Tour fetchedTour = tourRepository.findByTitle(tourDto.getTitle()).orElseThrow(
                () -> new ResourceNotFoundException("Tour", "Title", tourDto.getTitle())
        );

        Tour savedTour = tourRepository.save(TourMapper.tourDtoToTour(tourDto, fetchedTour));
        tourUpdatedEvents(savedTour);
        log.info("Tour updated successfully : {}", savedTour);
    }

    private void tourUpdatedEvents(Tour tour){
        TourMsgDto tourMsgDto = new TourMsgDto(
                tour.getTourId(),
                tour.getTitle(),
                tour.getTourDate(),
                tour.getCountry(),
                tour.getArea()
        );
        log.info("Tour updated events: {}",tourMsgDto);
        boolean result = streamBridge.send("tourUpdatedEvents-out-0", tourMsgDto);
        log.info("Result of updating: {}",result);
    }

    @Override
    public void removeTour(String tourTitle) {
        Tour fetchedTour = tourRepository.findByTitle(tourTitle).orElseThrow(
                () -> new ResourceNotFoundException("Tour", "Title", tourTitle)
        );
        tourDeletedEvents(fetchedTour);
        tourRepository.delete(fetchedTour);
    }

    private void tourDeletedEvents(Tour tour){
        TourMsgDto tourMsgDto = new TourMsgDto(
                tour.getTourId(),
                tour.getTitle(),
                tour.getTourDate(),
                tour.getCountry(),
                tour.getArea()
        );
        log.info("Tour deleted events: {}",tourMsgDto);
        boolean result = streamBridge.send("tourDeletedEvents-out-0", tourMsgDto);
        log.info("Result of deleting: {}",result);
    }

    @Override
    public List<Tour> getTours() {
        return tourRepository.findAll();
    }

    @Override
    public TourDto getTour(String titleTour) {
        Tour fetchedTour = tourRepository.findByTitle(titleTour).orElseThrow(
                () -> new ResourceNotFoundException("Tour", "Title", titleTour)
        );
        return TourMapper.tourToTourDto(fetchedTour, new TourDto());
    }
}
