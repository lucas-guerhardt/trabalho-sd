package com.lp.users.models.dto;

import com.lp.users.models.enums.Occupation;
import com.lp.users.models.enums.Role;

public class UserGet {
    private String name;
    private String email;
    private String cpf;
    private Occupation occupation;
    private Role role;

    public UserGet() {
    }

    public UserGet(String name, String email, String cpf, Occupation occupation, Role role) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.occupation = occupation;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public Role getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    } 
    
    public void setRole(Role role) {
        this.role = role;
    }
}
