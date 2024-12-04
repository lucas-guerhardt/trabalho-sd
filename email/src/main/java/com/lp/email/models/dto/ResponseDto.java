package com.lp.email.models.dto;

public class ResponseDto {
    private String email;
    private String subject;
    private String text;
    private String error;

    public ResponseDto() {
    }

    public ResponseDto(String email, String subject, String text, String error) {
        this.email = email;
        this.subject = subject;
        this.text = text;
        this.error = error;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}