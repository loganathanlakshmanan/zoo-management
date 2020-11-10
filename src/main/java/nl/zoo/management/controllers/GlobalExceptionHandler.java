package nl.zoo.management.controllers;

import nl.zoo.management.exceptions.AnimalNotFound;
import nl.zoo.management.exceptions.BadRequestException;
import nl.zoo.management.exceptions.ExceptionResponse;
import nl.zoo.management.exceptions.RoomNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RoomNotFound.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(RoomNotFound roomNotFound) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                                                     .message(roomNotFound.getMessage())
                                                     .status(HttpStatus.NOT_FOUND.getReasonPhrase())
                                                     .timestamp(LocalDateTime.now())
                                                     .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AnimalNotFound.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(AnimalNotFound animalNotFound) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                                                     .message(animalNotFound.getMessage())
                                                     .status(HttpStatus.NOT_FOUND.getReasonPhrase())
                                                     .timestamp(LocalDateTime.now())
                                                     .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(BadRequestException badRequestException) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                                                     .message(badRequestException.getMessage())
                                                     .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                                     .timestamp(LocalDateTime.now())
                                                     .build(), HttpStatus.BAD_REQUEST);
    }

}
