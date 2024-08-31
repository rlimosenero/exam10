package com.exam10.exception;

/**
 * we can also use ControllerAdvise or depending on the requirements
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}


