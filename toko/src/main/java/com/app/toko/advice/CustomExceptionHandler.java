package com.app.toko.advice;

import com.app.toko.exception.BadRequestException;
import com.app.toko.exception.ConflictException;
import com.app.toko.exception.ResourceNotFoundException;
import com.app.toko.payload.response.ErrorMessageResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

  // Internal Server Error Handler
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorMessageResponse> handleException(Exception ex) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            ErrorMessageResponse
                .builder()
                .message("Internal Server Error")
                .statusCode(500)
                .timeStamp(LocalDateTime.now())
                .build());
  }

  // Internal Server Error Handler
  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ErrorMessageResponse> handleBadCredentialsExceptio(BadCredentialsException ex) {
    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(
            ErrorMessageResponse
                .builder()
                .message("Unauthorized")
                .statusCode(401)
                .timeStamp(LocalDateTime.now())
                .build());
  }

  // Bad Request Exception Handler
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorMessageResponse> handleBadRequestException(
      BadRequestException ex) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(
            ErrorMessageResponse
                .builder()
                .message(ex.getMessage())
                .statusCode(400)
                .timeStamp(LocalDateTime.now())
                .build());
  }

  // Conflict Exception Handler
  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<ErrorMessageResponse> handleConflictException(
      ConflictException ex) {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(
            ErrorMessageResponse
                .builder()
                .message(ex.getMessage())
                .statusCode(409)
                .timeStamp(LocalDateTime.now())
                .build());
  }

  // Resource Not Found Exception Handler
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorMessageResponse> handleResourceNotFoundException(
      ResourceNotFoundException ex) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(
            ErrorMessageResponse
                .builder()
                .message(ex.getMessage())
                .statusCode(404)
                .timeStamp(LocalDateTime.now())
                .build());
  }

  // Exception for Validation
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleConstraintViolationException(
      MethodArgumentNotValidException ex) {
    Map<String, String> errorMap = new HashMap<>();
    ex
        .getBindingResult()
        .getFieldErrors()
        .forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
    return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorMessageResponse> handleDataIntegrityViolationException(
      DataIntegrityViolationException ex) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(
            ErrorMessageResponse
                .builder()
                .message(ex.getMessage())
                .statusCode(400)
                .timeStamp(LocalDateTime.now())
                .build());
  }
}
