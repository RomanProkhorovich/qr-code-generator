package org.romanus.qrcodegenerator.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception ex) {
        return ResponseEntity.internalServerError().body("Something went wrong \n " + ex.getMessage());
    }
}
