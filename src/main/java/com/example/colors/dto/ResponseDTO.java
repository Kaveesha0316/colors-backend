package com.example.colors.dto;

public class ResponseDTO {

    private boolean success;
    private Object content;
    private String message;

    public ResponseDTO() {
    }

    public ResponseDTO(boolean success, Object content, String message) {
        this.success = success;
        this.content = content;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
