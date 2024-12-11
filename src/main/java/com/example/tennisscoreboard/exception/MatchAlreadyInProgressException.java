package com.example.tennisscoreboard.exception;

public class MatchAlreadyInProgressException extends RuntimeException {

    public MatchAlreadyInProgressException(String message) {
        super(message);
    }
}
