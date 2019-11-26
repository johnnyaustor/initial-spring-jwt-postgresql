/* by JAPBuilder */
package com.jap.initial.springjwt.payload;

import org.springframework.http.HttpStatus;

public class ApiResponse {
    private MetaResponse meta;
    private Object data;

    public ApiResponse(Object data) {
        this.data = data;
        this.meta = new MetaResponse();
    }

    public ApiResponse(HttpStatus httpStatus) {
        this(httpStatus, httpStatus.getReasonPhrase());
    }

    public ApiResponse(HttpStatus httpStatus, Object object) {
        this(httpStatus, httpStatus.getReasonPhrase(), object);
    }

    public ApiResponse(HttpStatus httpStatus, String message) {
        this(httpStatus, message, null);
    }

    private ApiResponse(HttpStatus httpStatus, String message, Object object) {
        if (isResponseOk(httpStatus)) {
            this.data = object;
            this.meta = new MetaResponse(httpStatus);
        } else {
            this.data = null;
            this.meta = new MetaResponse(httpStatus, message, object);
        }
    }

    private boolean isResponseOk(HttpStatus httpStatus) {
        return httpStatus.value() >= 200 && httpStatus.value() < 300;
    }

    public ApiResponse setData(Object data) {
        this.data = data;
        return this;
    }

    public ApiResponse setMetaInfo(Object o) {
        this.meta.setInfo(o);
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
