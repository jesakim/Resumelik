package com.example.resumlik.exeption;


import com.example.resumlik.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response<String>> handelIllegalArgumentException(Exception ex){
        var response = Response.<String>builder()
                .error(ex.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<String>> handelMethodArgumentNotValidException(MethodArgumentNotValidException ev){
        Map<String, String> FieldsValidation = new HashMap<>();
        ev.getBindingResult().getFieldErrors().forEach(
                e -> FieldsValidation.put(e.getField(), e.getDefaultMessage())
        );

        var response = Response.<String>builder()
                .error("Validation error")
                .message("Please check your input")
                .errorValidation(FieldsValidation)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<String>> handelException(Exception ex){
        var response = Response.<String>builder()
                .error(ex.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

//    @ExceptionHandler(ExpiredJwtException.class)
//    public ResponseEntity<Response<String>> handelExpiredJwtException(ExpiredJwtException ex){
//        var response = Response.<String>builder()
//                .error(ex.getMessage())
//                .build();
//        return ResponseEntity.badRequest().body(response);
//    }
}