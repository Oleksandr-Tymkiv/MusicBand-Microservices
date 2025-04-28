package com.musicband.tour.service;

import com.musicband.tour.dto.TourDto;

import java.util.List;

public interface TourService {

    void addTour(TourDto tourDto);

    void updateTour(TourDto tourDto);

    void removeTour(String title);

    List<TourDto> getTours();

    TourDto getTour(String titleTour);

}
