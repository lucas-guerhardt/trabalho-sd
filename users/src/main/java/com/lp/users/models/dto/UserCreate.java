package com.lp.users.models.dto;

import com.lp.users.models.enums.Occupation;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserCreate {
    @NotNull
    private String name;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    @Column(unique = true)
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    @Size(min = 8)
    private String password;

    private Occupation occupation;

    public UserCreate() {
    }

    public UserCreate(String name, String email, String cpf, String password, Occupation occupation) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
        this.occupation = occupation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }
}
