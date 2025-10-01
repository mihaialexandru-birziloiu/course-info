package org.example.repository;

public class RepositoryException extends RuntimeException{
    public RepositoryException(String message, Exception e) {
        super(message, e);
    }
}
