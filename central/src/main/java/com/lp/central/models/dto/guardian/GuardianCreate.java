package com.lp.central.models.dto.guardian;

public class GuardianCreate {
    private String name;
    private String email;
    private String cpf;

    public GuardianCreate() {
    }

    public GuardianCreate(String nome, String email, String cpf) {
        this.name = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
