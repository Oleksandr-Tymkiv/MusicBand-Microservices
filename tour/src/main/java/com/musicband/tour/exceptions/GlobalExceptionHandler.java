package com.musicband.tour.exceptions;

import com.musicband.tour.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, Object> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach(error -> validationErrors.put(((FieldError)error).getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(
            Exception ex,
            WebRequest webRequest
    ){
        return new ResponseEntity<>(
                ErrorResponseDto.builder()
                        .apiPath(webRequest.getDescription(false))
                        .errorCode(HttpStatus.INTERNAL_SERVER_ERROR)
                        .errorMessage(ex.getMessage())
                        .errorTime(LocalDateTime.now())
                        .build()
                , HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(TourAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleTourAlreadyExistsException(
            TourAlreadyExistsException ex,
            WebRequest webRequest
    ){
        return new ResponseEntity<>(
                ErrorResponseDto.builder()
                        .apiPath(webRequest.getDescription(false))
                        .errorCode(HttpStatus.BAD_REQUEST)
                        .errorMessage(ex.getMessage())
                        .errorTime(LocalDateTime.now())
                        .build()
                , HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest webRequest
    ){
        return new ResponseEntity<>(
                ErrorResponseDto.builder()
                        .apiPath(webRequest.getDescription(false))
                        .errorCode(HttpStatus.NOT_FOUND)
                        .errorMessage(ex.getMessage())
                        .errorTime(LocalDateTime.now())
                        .build()
                , HttpStatus.NOT_FOUND
        );
    }
}
