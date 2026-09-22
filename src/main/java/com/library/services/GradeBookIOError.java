package com.library.services;

public class GradeBookIOError extends RuntimeException {
 
     public GradeBookIOError(String message) {
        super(message);
    }
    
    public GradeBookIOError(String message, Throwable cause) {
        super(message, cause);
    }
}

