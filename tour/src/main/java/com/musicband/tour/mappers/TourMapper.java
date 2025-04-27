package com.musicband.tour.mappers;

import com.musicband.tour.dto.TourDto;
import com.musicband.tour.entity.Tour;

public class TourMapper {

    public static TourDto tourToTourDto(Tour tour, TourDto tourDto) {
        tourDto.setTitle(tour.getTitle());
        tourDto.setTourDate(tour.getTourDate());
        tourDto.setCountry(tour.getCountry());
        tourDto.setArea(tour.getArea());
        return tourDto;
    }

    public static Tour tourDtoToTour(TourDto tourDto, Tour tour) {
        tour.setTitle(tourDto.getTitle());
        tour.setTourDate(tourDto.getTourDate());
        tour.setCountry(tourDto.getCountry());
        tour.setArea(tourDto.getArea());
        return tour;
    }
}
