package com.jap.initial.springjwt.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jap.initial.springjwt.exceptions.AppException;
import com.jap.initial.springjwt.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Service
public class MapValidationErrorService {

    public ResponseEntity<?> mapValidationError(BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error: result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, errorMap), HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    public ResponseEntity<?> mapValidationError(Class clazz, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
//            try {
//                for (FieldError error: result.getFieldErrors()) {
//                    Field field = clazz.getDeclaredField(error.getField());
//                    field.setAccessible(true);
//                    JsonProperty jsonProperty = field.getDeclaredAnnotation(JsonProperty.class);
//                    String fieldName = (jsonProperty!=null) ?
//                            (!jsonProperty.value().isEmpty()) ?
//                                    jsonProperty.value() : error.getField() :
//                            error.getField();
//
//                    errorMap.put(fieldName, error.getDefaultMessage());
//                }
//            } catch (NoSuchFieldException e) {
//                e.printStackTrace();
//                throw new AppException(e.getMessage());
//            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, errorMap), HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    public ResponseEntity<?> passwordRequired(String field) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(field, "required");
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, errorMap), HttpStatus.BAD_REQUEST);
    }
}
