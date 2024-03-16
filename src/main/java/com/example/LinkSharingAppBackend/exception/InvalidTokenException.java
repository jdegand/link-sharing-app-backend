package com.example.LinkSharingAppBackend.exception;

// could extend ExpiredJwtException from jsonwebtoken
public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
