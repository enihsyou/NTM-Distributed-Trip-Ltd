package com.enihsyou.trip.bank.service.endpoint.controller;

import com.enihsyou.trip.bank.service.exception.BaseRestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseRestException.class)
    public ResponseEntity defaultErrorHandler(BaseRestException e) {
        Map<String, String> map = new HashMap<>();
        map.put("message", e.getMessage());
        return ResponseEntity.badRequest().body(map);
    }

}
