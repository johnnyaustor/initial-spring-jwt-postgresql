package com.jap.initial.springjwt.payload;

import org.springframework.http.HttpStatus;

public class ApiResponse {
    private MetaResponse meta;
    private Object data;

    public ApiResponse(Object data) {
        this.data = data;
        this.meta = new MetaResponse();
    }

    public ApiResponse(HttpStatus httpStatus, Object object) {
        this.setApiResponse(httpStatus.value(), httpStatus.getReasonPhrase(), object);
    }

    public ApiResponse(int http_status, String message) {
        this.setApiResponse(http_status, message, null);
    }

    public ApiResponse(int http_status, String message, boolean success) {
        this(http_status, message, success, null);
    }

    public ApiResponse(int http_status, String message, boolean success, Object errors) {
        this.meta = setMetaResponse(http_status, message, success, errors);
    }

    private void setApiResponse(int http_status, String message, Object object) {
        if (http_status >=200 && http_status < 300) {
            this.data = object;
            this.meta = new MetaResponse();
        } else {
            this.data = null;
            this.meta = setMetaResponse(http_status, message, false, object);
        }
    }

    private MetaResponse setMetaResponse(int http_status, String message, boolean success, Object errors) {
        return new MetaResponse(http_status, message, success, errors);
    }

    public ApiResponse setData(Object data) {
        this.data = data;
        return this;
    }

    public Object getData() {
        return data;
    }

    public MetaResponse getMeta() {
        return meta;
    }

    public void setMeta(MetaResponse meta) {
        this.meta = meta;
    }
}
