package com.livenow.springmvc.exceptions;

import com.livenow.springmvc.exceptions.exception.CustomException;
import com.livenow.springmvc.exceptions.exception.HelloException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exceptions")
public class ExceptionsController {

    @GetMapping("/hello")
    public ResponseEntity exceptionHandler() {
        throw new CustomException();
    }

    @GetMapping("/hi")
    public ResponseEntity exceptionHandler2() {
        throw new HelloException();
    }

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<String> handleCustomException() {
        return ResponseEntity.badRequest().body("CustomException");
    }

    @ExceptionHandler(value = HelloException.class)
    public ResponseEntity<String> handleHelloException() {
        return ResponseEntity.badRequest().body("HelloException");
    }
}