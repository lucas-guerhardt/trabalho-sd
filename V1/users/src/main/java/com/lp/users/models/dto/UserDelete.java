package com.lp.users.models.dto;

import jakarta.validation.constraints.NotNull;

public class UserDelete {
    @NotNull
    private String id;

    public UserDelete() {
    }

    public UserDelete(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
