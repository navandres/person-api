package com.example.demo.exceptions;

public class PersonDoesNotExistException extends Exception {

    public PersonDoesNotExistException(String message) {
        super(message);
    }
}
