package com.lp.users.models.dto;

import com.lp.users.models.enums.Occupation;

public class UserUpdate extends UserCreate {
    public UserUpdate() {
    }

    public UserUpdate(String name, String email, String cpf, String password, Occupation occupation) {
        super(name, email, cpf, password, occupation);
    }
}
