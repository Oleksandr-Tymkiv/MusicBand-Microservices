package com.musicband.ticket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TicketAlreadyExistsException extends RuntimeException {

    public TicketAlreadyExistsException(String message) {
        super(message);
    }

}
