package com.brandon.animalapi.controllers;

import com.brandon.animalapi.data.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandlers {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataNotFoundException.class)
    public ErrorResponse handleDataException(DataNotFoundException exception, HttpServletRequest request){
        return new ErrorResponse(String.format("Attempting to read data from %s with id %d failed", exception.getKey(), exception.getIndex()));
    }

    public class ErrorResponse{
        private String error;
        public ErrorResponse(String message){
            this.error = message;
        }

        public String getError() {
            return this.error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
