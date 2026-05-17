package com.smartbank.exception;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> ex(Exception e){
        return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> val(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest().body(Map.of("error",e.getBindingResult().getFieldError().getDefaultMessage()));
    }
}
