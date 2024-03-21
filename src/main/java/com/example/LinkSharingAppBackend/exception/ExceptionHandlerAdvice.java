package com.example.LinkSharingAppBackend.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.LinkSharingAppBackend.dto.ErrorDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception ex, HttpServletRequest request) {
        ProblemDetail errorDetail = null;
        if (ex instanceof BadCredentialsException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "Authentication Failure");
        }

        if (ex instanceof AccessDeniedException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "Not authorized!");

        }

        if (ex instanceof SignatureException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "JWT Signature not valid");
        }

        // && !request.getRequestURI().equals("auth/refresh") | .equals("auth/refresh2")
        // it works better if you hit the refresh route without a bearer token vs an
        // expired token.  An expired token results in null being sent back to the frontend. 
        if (ex instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "JWT Token expired");

            // Handle refresh token request here?
        }

        if (ex instanceof InvalidTokenException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "Refresh Token invalid");
        }

        /*
         * if (ex instanceof MethodArgumentNotValidException) {
         * errorDetail = ProblemDetail
         * .forStatusAndDetail(HttpStatusCode.valueOf(400), ex.getMessage());
         * errorDetail.setProperty("access_denied_reason", "Field is missing");
         * }
         */

        return errorDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {

        List<ErrorDetails> errors = new ArrayList<>();

        if (exception.getBindingResult().hasErrors()) {
            exception.getBindingResult().getFieldErrors().forEach(error -> {
                errors.add(new ErrorDetails(error.getField(), error.getDefaultMessage()));
            });
        }

        return ResponseEntity.unprocessableEntity().body(errors);
    }

}
