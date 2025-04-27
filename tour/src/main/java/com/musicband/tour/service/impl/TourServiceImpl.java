package com.musicband.tour.service.impl;

import com.musicband.tour.dto.TourDto;
import com.musicband.tour.entity.Tour;
import com.musicband.tour.mappers.TourMapper;
import com.musicband.tour.repository.TourRepository;
import com.musicband.tour.service.TourService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private static final Logger log = LoggerFactory.getLogger(TourServiceImpl.class);

    @Override
    public void addTour(TourDto tourDto) {
        if(tourRepository.findByTitle(tourDto.getTitle()).isPresent()){
            throw new IllegalStateException("Tour already exists");
        }

        Tour savedTour = tourRepository.save(TourMapper.tourDtoToTour(tourDto, new Tour()));
        log.info("Tour added successfully : {}", savedTour);
    }

    @Override
    public void updateTour(TourDto tourDto) {
        Tour fetchedTour = tourRepository.findByTitle(tourDto.getTitle()).orElseThrow(
                () -> new IllegalStateException("Tour not found")
        );

        Tour savedTour = tourRepository.save(TourMapper.tourDtoToTour(tourDto, fetchedTour));
        log.info("Tour updated successfully : {}", savedTour);
    }

    @Override
    public boolean removeTour(String tourTitle) {
        Tour fetchedTour = tourRepository.findByTitle(tourTitle).orElseThrow(
                () -> new IllegalStateException("Tour not found")
        );
        tourRepository.delete(fetchedTour);
        return true;
    }

    @Override
    public List<TourDto> getTours() {
        return tourRepository.findAll().stream().map(tour -> TourMapper.tourToTourDto(tour,new TourDto())).toList();
    }

    @Override
    public TourDto getTour(String titleTour) {
        Tour fetchedTour = tourRepository.findByTitle(titleTour).orElseThrow(
                () -> new IllegalStateException("Tour not found")
        );
        return TourMapper.tourToTourDto(fetchedTour, new TourDto());
    }
}
