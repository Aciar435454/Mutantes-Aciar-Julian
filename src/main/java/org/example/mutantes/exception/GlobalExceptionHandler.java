package org.example.mutantes.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        String msg = ex.getBindingResult().getFieldErrors().isEmpty()
                ? "Error de validación"
                : ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        ApiError error = new ApiError(
                LocalDateTime.now(),
                400,
                "Bad Request",
                msg,
                req.getRequestURI()
        );

        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleBadJson(HttpMessageNotReadableException ex, HttpServletRequest req) {
        ApiError error = new ApiError(
                LocalDateTime.now(),
                400,
                "Bad Request",
                "JSON inválido o mal formado",
                req.getRequestURI()
        );

        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest req) {
        ApiError error = new ApiError(
                LocalDateTime.now(),
                400,
                "Bad Request",
                ex.getMessage(),
                req.getRequestURI()
        );

        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneral(Exception ex, HttpServletRequest req) {

        ApiError error = new ApiError(
                LocalDateTime.now(),
                500,
                "Internal Server Error",
                ex.getMessage(),
                req.getRequestURI()
        );

        return ResponseEntity.status(500).body(error);
    }
}



