package com.musicband.tour.controller;

import com.musicband.tour.dto.TourDto;
import com.musicband.tour.entity.Tour;
import com.musicband.tour.service.TourService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TourController {

    private final TourService tourService;

    @PostMapping("/add-tour")
    public ResponseEntity<String> addTour(@Valid @RequestBody TourDto tourDto) {
        tourService.addTour(tourDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Successfully added Tour");
    }

    @PutMapping("/update-tour")
    public ResponseEntity<String> updateTour(@Valid @RequestBody TourDto tourDto) {
        tourService.updateTour(tourDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Successfully updated Tour");
    }

    @DeleteMapping("/remove-tour")
    public ResponseEntity<String> removeTour(@RequestParam("title")
                                                 @NotEmpty(message = "Title cannot be a null or empty")
                                                 String title) {
        tourService.removeTour(title);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Successfully deleted Tour");
    }

    @GetMapping("/find-tour")
    public ResponseEntity<TourDto> findTour(@RequestParam("title")
                                                @NotEmpty(message = "Title cannot be a null or empty")
                                                String title) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tourService.getTour(title));
    }

    @GetMapping("/find-all-tours")
    public ResponseEntity<List<Tour>> findAllTours() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tourService.getTours());
    }
}
