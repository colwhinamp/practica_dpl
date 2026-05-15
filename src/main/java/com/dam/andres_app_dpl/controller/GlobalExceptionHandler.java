package com.dam.andres_app_dpl.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Devuelve un error 404 (Not Found) si buscamos un ID que no existe
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> manejarErrores(RuntimeException ex) {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("error", ex.getMessage());
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }
}