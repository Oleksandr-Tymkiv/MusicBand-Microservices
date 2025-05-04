package com.musicband.notification.controller;

import com.musicband.notification.dto.NotificationDto;
import com.musicband.notification.service.TourEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NotificationController {

    private final TourEmailService tourEmailService;

    @PostMapping("tracking-tour")
    public ResponseEntity<String> trackingTour(@RequestBody NotificationDto notificationDto) {
        return tourEmailService.addEmailForNotification(notificationDto) ?
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Tracking tour created successfully"):
                ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Cannot created tracking tour");
    }

}
