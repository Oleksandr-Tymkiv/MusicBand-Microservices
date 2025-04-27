package com.musicband.tour.service;

import com.musicband.tour.dto.TourDto;
import com.musicband.tour.entity.Tour;

import java.util.List;

public interface TourService {

    void addTour(TourDto tourDto);

    void updateTour(TourDto tourDto);

    boolean removeTour(String title);

    List<TourDto> getTours();

    TourDto getTour(String titleTour);

}
