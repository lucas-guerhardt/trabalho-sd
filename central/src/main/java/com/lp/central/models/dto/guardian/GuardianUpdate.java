package com.lp.central.models.dto.guardian;

public class GuardianUpdate {
    private String name;
    private String email;

    public GuardianUpdate() {
    }

    public GuardianUpdate(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
