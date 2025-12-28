package com.movie.moviereview.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieApiException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public MovieApiException(HttpStatus status,String message){
        super(message);
        this.status=status;
        this.message=message;
    }

    public MovieApiException(HttpStatus status,String message,String details){
        super(message);
        this.status=status;
        this.message=message;
    }

}
