package com.lp.users.models.dto;

import com.lp.users.models.UserModel;
import com.lp.users.models.enums.Occupation;
import com.lp.users.models.enums.Role;

public class UserGetAll extends UserModel {
    private Long id;

    public UserGetAll(Long id, String name, String email, String cpf, String password, Occupation occupation, Role role) {
        super(name, email, cpf, password, occupation, role);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
