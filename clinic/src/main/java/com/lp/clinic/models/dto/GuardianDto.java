package com.lp.clinic.models.dto;

import java.util.List;

public class GuardianDto {
    private Long id;

    private String name;

    private String cpf;

    private String email;

    private List<Long> petIds;

    public GuardianDto() {
    }

    public GuardianDto(Long id, String name, String cpf, String email, List<Long> petIds) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.petIds = petIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
    }
}
