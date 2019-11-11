package com.jap.initial.springjwt.payload;

import org.springframework.http.HttpStatus;

public class MetaResponse {
    private boolean success;
    private Integer http_status;
    private String message;
    private Object errors;

    public MetaResponse() {
        this.http_status = HttpStatus.OK.value();
        this.message = HttpStatus.OK.getReasonPhrase();
        this.success = true;
    }

    public MetaResponse(Object errors) {
        this.errors = errors;
        this.http_status = HttpStatus.BAD_REQUEST.value();
        this.message = HttpStatus.BAD_REQUEST.getReasonPhrase();
        this.success = false;
    }

    public MetaResponse(HttpStatus httpStatus, Object errors) {
        this.errors = errors;
        this.http_status = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.success = false;
    }

    public MetaResponse(Integer http_status, String message, boolean success, Object errors) {
        this.http_status = http_status;
        this.message = message;
        this.success = success;
        this.errors = errors;
    }





    public Integer getHttp_status() {
        return http_status;
    }
    public String getMessage() {
        return message;
    }
    public boolean isSuccess() {
        return success;
    }
    public Object getErrors() {
        return errors;
    }

    public void setHttp_status(Integer http_status) {
        this.http_status = http_status;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public void setErrors(Object errors) {
        this.errors = errors;
    }
}
