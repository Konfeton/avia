package com.onkonfeton.avia.exceptions;

public class PersonAlreadyExistException extends RuntimeException{
    public PersonAlreadyExistException() {
    }

    public PersonAlreadyExistException(String message) {
        super(message);
    }
}
