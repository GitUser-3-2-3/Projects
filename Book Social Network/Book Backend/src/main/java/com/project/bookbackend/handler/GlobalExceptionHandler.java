package com.project.bookbackend.handler;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

import static com.project.bookbackend.handler.BusinessErrorCodes.*;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse> handleException(LockedException lkdExp) {

        return ResponseEntity.status(UNAUTHORIZED).body(
            ExceptionResponse.builder().errorCode(ACCOUNT_LOCKED.getErrorCode())
                .errDescription(ACCOUNT_LOCKED.getErrDescription())
                .errorBody(lkdExp.getMessage())
                .build()
        );
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponse> handleException(DisabledException dsbldExp) {

        return ResponseEntity.status(UNAUTHORIZED).body(
            ExceptionResponse.builder().errorCode(ACCOUNT_DISABLED.getErrorCode())
                .errDescription(ACCOUNT_DISABLED.getErrDescription())
                .errorBody(dsbldExp.getMessage())
                .build()
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleException() {

        return ResponseEntity.status(UNAUTHORIZED).body(
            ExceptionResponse.builder().errorCode(BAD_CREDENTIALS.getErrorCode())
                .errDescription(BAD_CREDENTIALS.getErrDescription())
                .errorBody("Login and/or Password is incorrect.")
                .build()
        );
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ExceptionResponse> handleException(MessagingException msgExp) {

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
            ExceptionResponse.builder().errorBody(msgExp.getMessage())
                .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException argInvalidExp) {
        Set<String> validationErrors = new HashSet<>();

        argInvalidExp.getBindingResult().getAllErrors()
            .forEach(error -> {
                var errorMessage = error.getDefaultMessage();
                validationErrors.add(errorMessage);
            });

        return ResponseEntity.status(BAD_REQUEST).body(
            ExceptionResponse.builder().validationErrors(validationErrors)
                .build()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exp) {
        log.error("Exception::{}", exp.getMessage());

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
            ExceptionResponse.builder()
                .errDescription("This is an internet error. Contact support.")
                .errorBody(exp.getMessage())
                .build()
        );
    }
}







