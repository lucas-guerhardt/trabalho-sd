package com.lp.central.models.dto.guardian;

import java.util.List;

import com.lp.central.models.GuardianModel;

public class GuardianGet extends GuardianModel {
    private Long id;
    private List<Long> petsIds;

    public GuardianGet() {
    }

    public GuardianGet(Long id, String nome, String email, String cpf, List<Long> petsIds) {
        super(nome, email, cpf);
        this.id = id;
        this.petsIds = petsIds;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getPetsIds() {
        return this.petsIds;
    }

    public void setPetsIds(List<Long> petsIds) {
        this.petsIds = petsIds;
    }

}
