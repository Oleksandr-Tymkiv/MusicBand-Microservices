package com.musicband.merch.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MerchAlreadyExistsException extends RuntimeException {

    public MerchAlreadyExistsException(String message) {
        super(message);
    }

}
