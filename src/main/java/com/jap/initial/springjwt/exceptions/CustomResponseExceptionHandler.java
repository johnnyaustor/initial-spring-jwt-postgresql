package com.jap.initial.springjwt.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jap.initial.springjwt.payload.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
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
    public final ResponseEntity<?> handleException(EntityExeption ex) {
        return new ResponseEntity<>(apiResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler
    public final ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(apiResponse(HttpStatus.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errorMap = new HashMap<>();
        Class clazz = ex.getParameter().getParameterType();

        for (FieldError error: ex.getBindingResult().getFieldErrors()) {
            try {
                Field fieldError = clazz.getDeclaredField(error.getField());
                fieldError.setAccessible(true);

                JsonProperty jsonProperty = fieldError.getDeclaredAnnotation(JsonProperty.class);
                String fieldName = (jsonProperty!=null) ?
                        (!jsonProperty.value().isEmpty()) ?
                                jsonProperty.value() : error.getField() :
                        error.getField();

                errorMap.put(fieldName, error.getDefaultMessage());

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, errorMap), HttpStatus.BAD_REQUEST);
    }



    private ApiResponse apiResponse(HttpStatus httpStatus, String message) {
        return new ApiResponse(httpStatus, message);
    }
}
