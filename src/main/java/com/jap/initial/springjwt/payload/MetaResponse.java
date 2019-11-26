/* by JAPBuilder */
package com.jap.initial.springjwt.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

public class MetaResponse {
    @JsonIgnore
    private HttpStatus httpStatus;
    private boolean success;
    private Timestamp timestamp;
    private Integer http_status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object errors;
    private Object info;

    MetaResponse() {
        this(HttpStatus.OK);
    }

    public MetaResponse(Object errors) {
        this(HttpStatus.BAD_REQUEST, errors);
    }

    MetaResponse(HttpStatus httpStatus) {
        this(httpStatus, httpStatus.getReasonPhrase());
    }

    private MetaResponse(HttpStatus httpStatus, Object errors) {
        this(httpStatus, httpStatus.getReasonPhrase(), errors);
    }

    private MetaResponse(HttpStatus httpStatus, String message) {
        this(httpStatus, message, null);
    }

    MetaResponse(HttpStatus httpStatus, String message, Object errors) {
        this.httpStatus = httpStatus;
        this.http_status = httpStatus.value();
        this.message = message;
        this.setSuccess(httpStatus);
        this.errors = errors;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    private void setSuccess(HttpStatus httpStatus) {
        this.success = httpStatus.value() >= 200 && httpStatus.value() < 300;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
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
    public Timestamp getTimestamp() {
        return timestamp;
    }
    public Object getInfo() {
        return info;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
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
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    public void setInfo(Object o) {
        this.info = o;
    }
}
