package com.lp.auth.models.dto;

public class MessageDto {
    private String id;
    private String email;
    private String password;
    private String passwordHash;
    private String token;
    private String error;
    private Boolean validation;

    public MessageDto() {
        this.id = null;
        this.email = null;
        this.password = null;
        this.passwordHash = null;
        this.token = null;
        this.error = null;
        this.validation = null;
    }

    public MessageDto(String id, String email, String password, String passwordHash, String token, String error, Boolean validation) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.passwordHash = passwordHash;
        this.token = token;
        this.error = error;
        this.validation = validation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Boolean getValidation() {
        return validation;
    }

    public void setValidation(Boolean validation) {
        this.validation = validation;
    }
}