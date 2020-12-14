package com.defender.test.Exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(int id) {
        super("Could not find user " + id);
    }
}