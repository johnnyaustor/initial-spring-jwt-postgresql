package com.jap.initial.springjwt.payload;

public class ApiResponse {
    private boolean success;
    private String message;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }
    public String getMessage() {
        return message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
