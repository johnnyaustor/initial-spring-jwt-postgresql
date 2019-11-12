package com.jap.initial.springjwt.exceptions;

import com.jap.initial.springjwt.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<?> handleAppException(AppException ex) {
        return new ResponseEntity<>(apiResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<?> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(apiResponse(HttpStatus.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(apiResponse(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public final ResponseEntity<?> handleAuthException(AuthException ex) {
        return new ResponseEntity<>(apiResponse(HttpStatus.UNAUTHORIZED, ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public final ResponseEntity<?> handleException(EntityExeption ex, WebRequest request) {
        return new ResponseEntity<>(apiResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler
    public final ResponseEntity<?> handleArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(apiResponse(HttpStatus.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private ApiResponse apiResponse(HttpStatus httpStatus, String message) {
        return new ApiResponse(httpStatus, message);
    }

}
