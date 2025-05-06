package com.musicband.tour.service;

import com.musicband.tour.dto.TourDto;
import com.musicband.tour.entity.Tour;

import java.util.List;

public interface TourService {

    void addTour(TourDto tourDto);

    void updateTour(TourDto tourDto);

    void removeTour(String title);

    List<Tour> getTours();

    TourDto getTour(String titleTour);

}
