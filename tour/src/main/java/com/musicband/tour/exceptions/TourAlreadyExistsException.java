package com.musicband.tour.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TourAlreadyExistsException extends RuntimeException {

    public TourAlreadyExistsException(String message) {
        super(message);
    }

}
